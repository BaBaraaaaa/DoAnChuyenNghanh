package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query( value = "SELECT * FROM `user`  WHERE user_name = :userName AND `password` = :password" ,nativeQuery = true)
    User findByUsernameAndPassword(@Param("userName") String username, @Param("password") String password);

    @Query( value = "SELECT u.* FROM `user` u WHERE user_name = :userName" ,nativeQuery = true)
    User findByUserName(@Param("userName") String userName);


@Query(value = "SELECT COUNT(*) > 0 FROM `user` WHERE user_name = :userName", nativeQuery = true)
    String existsByUsername(@Param("userName") String user_name);
    @Query(value = "SELECT COUNT(*) > 0 FROM `user` WHERE email = :email", nativeQuery = true)
    String existsByEmail(@Param("email") String email);
}
