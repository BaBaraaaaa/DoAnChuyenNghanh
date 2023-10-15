package com.example.QuanLyBanHang.service.impl;



//import com.example.QuanLyBanHang.Dto.UserCreateForm;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateUser;
import com.example.QuanLyBanHang.entity.User;
import com.example.QuanLyBanHang.repository.UserRepository;
import com.example.QuanLyBanHang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return null;
	}

	@Override
	public void deleteUserById(int id) {

	}

	@Override
	public User GetUserByEmail(String email) {
		return null;
	}

	@Override
	public List<User>  getListUsernameAndRole(String user_Name,String role) {
		List<User> adminUsers = new ArrayList<>();
		List<User> allUsers = userRepository.findAll();
		for (User user : allUsers) {
			if (user.getUser_Name().equals(user_Name) && user.getRole().equals(role) ) {
				adminUsers.add(user);
			}
		}
		return  adminUsers;
	}
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User getUserByUser_nameandRole(String name, String role) {
		List<User> user = userRepository.findAll();
		User users = new User();
		for (User user1 : user)
		{
			if (user1.getUser_Name().equals(name) && user1.getRole().equals("user"))
			{
				users = user1;
			}
		}
		return users;
	}

	@Override
	public User createUser(FormCreateUser form) {
		User user = new User();
		user.setUser_Name(form.getUser_name());
		user.setAvatar(form.getAvatar());
		user.setEmail(form.getEmail());
		user.setLogin_Type(form.getLogin_type());
		user.setPassword(form.getPassword());
		user.setPhone_Number(form.getPhone_number());
		user.setRole(form.getRole());
		user.setFull_name(form.getFull_name());
		return userRepository.save(user);
	}
}
