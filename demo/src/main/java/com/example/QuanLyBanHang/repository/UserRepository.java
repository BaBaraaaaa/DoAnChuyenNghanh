package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query( value = "SELECT * FROM `user`  WHERE user_name = :userName AND `password` = :password" ,nativeQuery = true)
    User findByUsernameAndPassword(@Param("userName") String username, @Param("password") String password);
}
