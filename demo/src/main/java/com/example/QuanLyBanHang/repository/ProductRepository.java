package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT product.* FROM product  JOIN cart ON product.id = cart.product_id WHERE  cart.user_id = :user_id AND cart.product_id = product_id" ,nativeQuery = true)
    List<Product> findProductIncarts(@Param("user_id") int user_id);
}
