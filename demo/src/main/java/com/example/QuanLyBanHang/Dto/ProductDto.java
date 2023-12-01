package com.example.QuanLyBanHang.Dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@Data

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
    private int category_id;
     private List<ProductImageDTO> productImage;


    public ProductDto(int id, String productName, String description, int sold, int isActive, int isSelling, Date createdAt, int price, int quantity, int category_id, List<ProductImageDTO> productImage) {
        this.id = id;
        this.productName = productName;
        this.description = description;
        this.sold = sold;
        this.isActive = isActive;
        this.isSelling = isSelling;
        this.createdAt = createdAt;
        this.price = price;
        this.quantity = quantity;
        this.category_id = category_id;
        this.productImage = productImage;
    }

    public List<ProductImageDTO> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<ProductImageDTO> productImage) {
        this.productImage = productImage;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }



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

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int categoryName) {
        this.category_id = categoryName;
    }



    public ProductDto() {
    }
}
