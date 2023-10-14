package com.example.QuanLyBanHang.repository;

import com.example.QuanLyBanHang.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
