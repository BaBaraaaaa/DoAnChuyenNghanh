package com.example.QuanLyBanHang.Dto;

import com.example.QuanLyBanHang.entity.ProductImage;

import java.util.Date;
import java.util.List;

public class ProductDto {
    private int id;
    private String productName;
    private String description;
    private int sold;
    private int isActive;
    private int isSelling;
    private Date createdAt;
    private int price;
    private int quantity;
    private String categoryName;
    private List<ProductImageDTO> productImages;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCategoryId() {
        return categoryName;
    }

    public void setCategoryId(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ProductImageDTO> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImageDTO> productImages) {
        this.productImages = productImages;
    }

    public ProductDto(int id, String productName, String description, int sold, int isActive, int isSelling, Date createdAt, int price, int quantity, String categoryName, List<ProductImageDTO> productImages) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.sold = sold;
        this.isActive = isActive;
        this.isSelling = isSelling;
        this.createdAt = createdAt;
        this.price = price;
        this.quantity = quantity;
        this.categoryName = categoryName;
        this.productImages = productImages;
    }

    public ProductDto() {
    }
}