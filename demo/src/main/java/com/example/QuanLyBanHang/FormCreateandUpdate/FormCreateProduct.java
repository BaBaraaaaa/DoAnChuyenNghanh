package com.example.QuanLyBanHang.FormCreateandUpdate;

import com.example.QuanLyBanHang.Dto.ProductImageDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

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
    private List<ProductImageDTO> productImages;
    private int category_id;
    public FormCreateProduct() {
    }


}
