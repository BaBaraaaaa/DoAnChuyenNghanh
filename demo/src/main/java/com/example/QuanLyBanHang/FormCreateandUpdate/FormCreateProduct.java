package com.example.QuanLyBanHang.FormCreateandUpdate;

import com.example.QuanLyBanHang.Dto.ProductImageDTO;
import com.example.QuanLyBanHang.entity.Category;
import com.example.QuanLyBanHang.entity.ProductImage;

import java.time.LocalDateTime;
import java.util.Date;

public class FormCreateProduct {
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    private String productName;
    private String description;
    private int sold;
    private int isActive;
    private int isSelling;
    private Date createdAt;
    private int price;
    private int quantity;
    private ProductImageDTO productImages;
    private int categoryId;

    public FormCreateProduct() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsSelling() {
        return isSelling;
    }

    public void setIsSelling(int isSelling) {
        this.isSelling = isSelling;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductImageDTO getProductImages() {
        return productImages;
    }

    public void setProductImages(ProductImageDTO productImages) {
        this.productImages = productImages;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
