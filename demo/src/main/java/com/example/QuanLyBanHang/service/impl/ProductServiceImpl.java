package com.example.QuanLyBanHang.service.impl;


import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateProduct;
import com.example.QuanLyBanHang.entity.Category;
import com.example.QuanLyBanHang.entity.Product;
import com.example.QuanLyBanHang.entity.ProductImage;
import com.example.QuanLyBanHang.repository.CategoryRepository;
import com.example.QuanLyBanHang.repository.ProdcutImageRepository;
import com.example.QuanLyBanHang.repository.ProductRepository;
import com.example.QuanLyBanHang.service.CategoryService;
import com.example.QuanLyBanHang.service.ProductImageService;
import com.example.QuanLyBanHang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import  java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	ProdcutImageRepository productImageRepository;

	@Autowired
	CategoryService categoryService;
	@Override
	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public void CreateProduct(FormCreateProduct form) {

		Category category = categoryService.getCategoryById(form.getCategoryId());
		Product product = new Product();
		product.setProduct_Name(form.getProductName());
		product.setDescription(form.getDescription());
		product.setSold(form.getSold());
		product.setIs_Active(form.getIsActive());
		product.setIs_Selling(form.getIsSelling());
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


	}








}
