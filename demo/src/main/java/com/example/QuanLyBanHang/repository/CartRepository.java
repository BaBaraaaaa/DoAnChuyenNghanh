package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository  extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByUser_id(int user_id);
}
