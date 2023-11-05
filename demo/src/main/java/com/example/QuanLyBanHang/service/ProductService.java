package com.example.QuanLyBanHang.service;

import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateProduct;
import com.example.QuanLyBanHang.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
	List<Product> getAllProduct();
	
	void CreateProduct(FormCreateProduct product);

	public Product getProductById(int id);

	void deleteProductById(int id);
	void UpdateProductById(int id);
	List<Product> getAllProductInCartByUser_id(int user_id);


}
