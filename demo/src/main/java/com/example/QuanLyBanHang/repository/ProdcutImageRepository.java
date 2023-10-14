package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProdcutImageRepository extends JpaRepository<ProductImage,Integer> {
}
