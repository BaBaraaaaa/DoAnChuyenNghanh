package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.UserDTO;
import com.example.QuanLyBanHang.entity.User;
import com.example.QuanLyBanHang.service.UserService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/login")
public class ApiLoginController {
    @Autowired
    UserService userService;
    @PostMapping
    ResponseEntity<?>  login(@RequestBody UserDTO login)
    {
         User user = userService.loginUserNameadnPassword(login.getUserName() ,login.getPassword());
         try
         {
             if (user !=null)
             {
                 User user1 = userService.getUserById(user.getId());
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user1.getId());
                 userDTO.setUserName(user1.getUser_Name());
                 userDTO.setAvatar(user1.getAvatar());
                 userDTO.setEmail(user1.getEmail());
                 userDTO.setFullName(user1.getFull_name());
                 userDTO.setPhoneNumber(user1.getPhone_Number());
                 userDTO.setPassword(user1.getPassword());

                 return new ResponseEntity<>(userDTO ,HttpStatus.OK);

             }
         }catch (Exception e)
         {
             return new ResponseEntity<Exception>( e,HttpStatus.BAD_REQUEST);
         }


        return new ResponseEntity<>("đăng nhập không thành công",HttpStatus.BAD_REQUEST);

    }
}
