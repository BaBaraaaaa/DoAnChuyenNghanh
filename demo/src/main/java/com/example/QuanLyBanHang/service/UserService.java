package com.example.QuanLyBanHang.service;


//import com.example.QuanLyBanHang.Dto.UserCreateForm;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateUser;
import com.example.QuanLyBanHang.entity.User;

import java.util.List;

public interface UserService {

	List<User> getAllUser();
	User createUser(FormCreateUser form);
	User saveUser(User user);

	User loginUserNameadnPassword(String  userName,String Password);
	User getUserById(int id);

	User getUserByUser_nameandRole(String name, String role);

	User updateUser(User user);

	void deleteUserById(int id);
	
	User GetUserByEmail(String email);

	List<User> getListUsernameAndRole(String user_Name,String role);

	List<User> findAll();    
}
