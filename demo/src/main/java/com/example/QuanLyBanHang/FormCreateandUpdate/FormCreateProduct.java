package com.example.QuanLyBanHang.FormCreateandUpdate;

import com.example.QuanLyBanHang.Dto.ProductImageDTO;
import com.example.QuanLyBanHang.entity.Category;
import com.example.QuanLyBanHang.entity.ProductImage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@Getter
@Setter
public class FormCreateProduct {

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


}
