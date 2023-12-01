package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.UserDTO;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateUser;
import com.example.QuanLyBanHang.entity.Role;
import com.example.QuanLyBanHang.entity.User;
import com.example.QuanLyBanHang.repository.RoleRepository;
import com.example.QuanLyBanHang.repository.UserRepository;
import com.example.QuanLyBanHang.service.UserService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth/")
public class ApiLoginController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping(value = "login")
    ResponseEntity<?>  login(@RequestBody UserDTO login)
    {
        User user = new User();
        UserDTO userDTO = new UserDTO();
        try
        {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    login.getUserName(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (authentication.isAuthenticated())
            {user   = userRepository.findByUserName(login.getUserName());
                userDTO.setUserName(user.getUser_Name());
                userDTO.setId(user.getId());
                userDTO.setFull_name(user.getFull_name());
                userDTO.setPhoneNumber(user.getPhone_Number());
                userDTO.setEmail(user.getEmail());
                userDTO.setAvatar(user.getAvatar());
                userDTO.setRole(user.getRoles().stream().findFirst().get().getName());
            }
            return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
        }catch (AuthenticationException e)
        {
            return new ResponseEntity<String>("Sai thông tin đăng nhập",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping({"/registerUser"})
    public ResponseEntity<?> registerUser(@RequestBody FormCreateUser signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUser_name()).equals("1") ){
            System.out.println(userRepository.existsByUsername(signUpDto.getUser_name()));
            return new ResponseEntity<String>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail()).equals("1")){
            return new ResponseEntity<String>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }
        // create user object
        User user = new User();
        user.setFull_name(signUpDto.getFull_name());
        user.setUser_Name(signUpDto.getUser_name());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setAvatar("");
        user.setLogin_Type("");
        user.setPhone_Number("");
        Role roles = roleRepository.findByName("User").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}
