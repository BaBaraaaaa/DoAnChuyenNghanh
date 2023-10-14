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
	public Product CreateProduct(FormCreateProduct product) {

		Category category = categoryService.getCategoryById(product.getCategoryId());

		List<ProductImage> productImages = new ArrayList<>();
		ProductImage productImage = new ProductImage();
		productImage.setProduct(productRepository.findById(product.getProductId()).get());
		productImage.setUrl_Image(product.getProductImages().getUrlImage());
		productImage.setId(product.getProductImages().getId());
		productImages.add(productImage);

		Product product1 = new Product(product.getProductId(),product.getProductName(),
				product.getDescription(),product.getSold(),
				product.getIsActive(),product.getIsSelling(), product.getCreatedAt()
				,product.getPrice(),product.getQuantity(),
				category,productImages);


		return productRepository.save(product1);
	}


	@Override
	public Product getProductById(int id) {

		return productRepository.findById(id).get();
	}

	@Override
	public Product updateProduct(Product product) {
		return null;
	}

	@Override
	public void deleteProductById(int id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findByProduct_NameContaining(String name) {
		return null;
	}

	@Override
	public List<Product> findTop12ProductBestSellers() {
		return productRepository.findTop12ProductBestSellers();
	}

	@Override
	public List<Product> findTop12ProductNewArrivals() {
		return productRepository.findTop12ProductNewArrivals();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Page<Product> findByProduct_NameContaining(String name, Pageable pageable) {
		return null;
	}

	@Override
	public Page<Product> findByProduct_NameAndCategory_idContaining(String name, int category_id, Pageable pageable) {
		return productRepository.findByProduct_NameAndCategory_idContaining(name,category_id,pageable);
	}

	@Override
	public List<Product> findTop4ProductByCategory_id(int name) {
		return null;
	}
}
