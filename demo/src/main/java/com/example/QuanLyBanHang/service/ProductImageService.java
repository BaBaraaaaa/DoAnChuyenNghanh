package com.example.QuanLyBanHang.service;


import com.example.QuanLyBanHang.entity.ProductImage;

import java.util.List;

public interface ProductImageService {

	void save(ProductImage productImage);

	void deleteById(int id);
	List<ProductImage> getAllbyId(int id);
}
