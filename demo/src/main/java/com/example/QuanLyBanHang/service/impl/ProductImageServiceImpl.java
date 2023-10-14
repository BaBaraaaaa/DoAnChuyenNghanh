package com.example.QuanLyBanHang.service.impl;


import com.example.QuanLyBanHang.entity.ProductImage;
import com.example.QuanLyBanHang.repository.ProdcutImageRepository;
import com.example.QuanLyBanHang.service.ProductImageService;
import com.example.QuanLyBanHang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {
	@Autowired
	ProdcutImageRepository prodcutImageRepository;

	@Override
	public void save(ProductImage productImage) {
	prodcutImageRepository.save(productImage);
	}

	@Override
	public void deleteById(int id) {
		prodcutImageRepository.deleteById(id);

	}

	@Override
	public List<ProductImage> getAllbyId(int id) {
		List<ProductImage> productImages = prodcutImageRepository.findAll();
		List<ProductImage>productImages1 = new ArrayList<>();
		for (ProductImage productImage : productImages)
		{
			if (productImage.getProduct().getId() == id )
			{
				ProductImage productImage1 = productImage;
				productImages1.add(productImage1);
			}

		}
		return productImages1;
	}
}
