package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.UserDTO;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateUser;
import com.example.QuanLyBanHang.FormCreateandUpdate.FromUpdateUser;
import com.example.QuanLyBanHang.entity.User;
import com.example.QuanLyBanHang.repository.UserRepository;
import com.example.QuanLyBanHang.service.FileUpload;
import com.example.QuanLyBanHang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
public class APIUserController {
        @Autowired
    UserService userService;

    @Autowired
    FileUpload fileUpload;

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

            userDTO.setEmail(user.getEmail());
            userDTO.setFull_name(user.getFull_name());
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
    @PutMapping(value = "/{id}")
    ResponseEntity<?> UpdateUserById(@PathVariable int id,
    @RequestPart("user") FromUpdateUser form,
    @RequestPart("image")MultipartFile[] multipartFiles)
    {   User user = new User();
        try {
            for (MultipartFile y: multipartFiles)
            {
                String url = fileUpload.uploadFile(y);
                form.setAvatar(url);
                userService.updateUser(id, form);
               user =   userService.getUserById(id);
            }

        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }


        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
}
