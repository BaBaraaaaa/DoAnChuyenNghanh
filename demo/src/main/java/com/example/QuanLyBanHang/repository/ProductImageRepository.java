package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
    @Query(value = "SELECT * FROM product_image WHERE product_id = :id " ,nativeQuery = true)
    List<ProductImage> findAllByProductId(@Param("id") int id);

    void deleteById(int id);
}
