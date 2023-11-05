package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.Dto.ProductDto;
import com.example.QuanLyBanHang.entity.Cart;
import com.example.QuanLyBanHang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository  extends JpaRepository<Cart,Integer> {
//    @Query("SELECT product_id FROM Cart c WHERE c.user_id = :user_id")
//    List<Cart> findAllByUser_id(int user_id);

}
