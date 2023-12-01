package com.example.QuanLyBanHang.service.impl;


import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateProduct;
import com.example.QuanLyBanHang.entity.Category;
import com.example.QuanLyBanHang.entity.Product;
import com.example.QuanLyBanHang.entity.ProductImage;
import com.example.QuanLyBanHang.entity.User;
import com.example.QuanLyBanHang.repository.CategoryRepository;
import com.example.QuanLyBanHang.repository.ProductImageRepository;
import com.example.QuanLyBanHang.repository.ProductRepository;
import com.example.QuanLyBanHang.repository.UserRepository;
import com.example.QuanLyBanHang.service.CategoryService;
import com.example.QuanLyBanHang.service.FileUpload;
import com.example.QuanLyBanHang.service.ProductImageService;
import com.example.QuanLyBanHang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.Collections;
import  java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	FileUpload fileUpload;
	@Autowired
	ProductImageService productImageService;

	@Autowired
	CategoryService categoryService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	FileUpload cloudinaryService;

	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public void CreateProduct(FormCreateProduct form) throws IOException {

		Category category = categoryService.getCategoryById(form.getCategory_id());
		Product product = new Product();
		product.setProduct_Name(form.getProductName());
		product.setDescription(form.getDescription());
		form.setSold(0);
		product.setSold(form.getSold());
		form.setIsActive(1);
		product.setIs_Active(form.getIsActive());
		form.setIsSelling(0);
		product.setIs_Selling(form.getIsSelling());
		Date date = new Date();
		form.setCreatedAt(date);
		product.setCreated_At(form.getCreatedAt());
		product.setPrice(form.getPrice());
		product.setQuantity(form.getQuantity());
		product.setCategory(category);
		 productRepository.save(product);
	}


	@Override
	public Product getProductById(int id) {

		return productRepository.findById(id).get();
	}



	@Override
	public void deleteProductById(int id) {
		productRepository.deleteById(id);
	}

	@Override
	public void UpdateProductById(int id) {
		Product product = productRepository.findById(id).get();
		productRepository.save(product);


	}
	@Override
	public List<Product> getAllProductInCartByUser_id(int user_id) {
		User user = userRepository.findById(user_id).orElse(null);
		if (user == null)
		{
			return Collections.emptyList();
		}
//		List<Product> productList =


		return productRepository.findProductIncarts(user_id);
	}




}
