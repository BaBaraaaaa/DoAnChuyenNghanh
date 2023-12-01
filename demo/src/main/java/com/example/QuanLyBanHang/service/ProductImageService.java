package com.example.QuanLyBanHang.service;

import com.example.QuanLyBanHang.entity.ProductImage;

import java.util.List;

public interface ProductImageService {

    void save(ProductImage productImage);
    List<ProductImage> PRODUCT_IMAGE_LIST(int id);
    List<ProductImage> findAll();

    void deleteById(int id);
}
