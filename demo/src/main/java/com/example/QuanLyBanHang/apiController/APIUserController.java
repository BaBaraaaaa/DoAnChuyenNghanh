package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.UserDTO;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateUser;
import com.example.QuanLyBanHang.entity.User;
import com.example.QuanLyBanHang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1/user")
public class APIUserController {
        @Autowired
    UserService userService;



    @GetMapping
    ResponseEntity<?> getAllUser()
    {
        List<User> userList = userService.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : userList)
        {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUser_Name());
            userDTO.setPassword(user.getPassword());
            userDTO.setAvatar(user.getAvatar());
            userDTO.setRole(user.getRole());
            userDTO.setEmail(user.getEmail());
            userDTO.setFullName(user.getFull_name());
            userDTO.setLoginType(user.getLogin_Type());
            userDTO.setPhoneNumber(user.getPhone_Number());
            userDTOS.add(userDTO);
        }
        return  new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    ResponseEntity<?> getUserById(@PathVariable int id)
    {
        User user = userService.getUserById(id);
        return  new ResponseEntity<User>(user,HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> CreateUser(@RequestBody FormCreateUser form)
    {
        User user = userService.createUser(form);
        return new ResponseEntity<User>(user ,HttpStatus.CREATED);
    }
}
