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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin("*")
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

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.getUserName(), login.getPassword()));
        User user = new User();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if (authentication.isAuthenticated())
        {

            user   = userRepository.findByUserName(login.getUserName());

        }
//        return new ResponseEntity<String>("User signed-in successfully!.", HttpStatus.OK);
//        return new ResponseEntity<>(authentication,HttpStatus.OK);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody FormCreateUser signUpDto){

        // add check for username exists in a DB
        if(userRepository.existsByUsername(signUpDto.getUser_name()).equals("1") ){
            System.out.println(userRepository.existsByUsername(signUpDto.getUser_name()));
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail()).equals("1")){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setFull_name(signUpDto.getFull_name());
        user.setUser_Name(signUpDto.getUser_name());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("Admin").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
