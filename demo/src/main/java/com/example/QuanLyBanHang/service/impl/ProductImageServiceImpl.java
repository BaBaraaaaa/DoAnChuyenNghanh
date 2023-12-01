package com.example.QuanLyBanHang.service.impl;

import com.example.QuanLyBanHang.entity.ProductImage;
import com.example.QuanLyBanHang.repository.ProductImageRepository;
import com.example.QuanLyBanHang.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository;
    @Override
    public void save(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    @Override
    public List<ProductImage> PRODUCT_IMAGE_LIST(int id) {
        return productImageRepository.findAllByProductId(id);
    }

    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        productImageRepository.deleteById(id);

    }
}
