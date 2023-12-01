package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.ProductImageDTO;
import com.example.QuanLyBanHang.entity.ProductImage;
import com.example.QuanLyBanHang.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/productImage")
public class ApiProductImage {
    @Autowired
    ProductImageService productImageService ;

    @GetMapping(value = "/{id}")
    ResponseEntity<?> getAllImageInProduct(@PathVariable int id)
    {
        List<ProductImage> productImages = productImageService.PRODUCT_IMAGE_LIST(id);
        List<ProductImageDTO> productImageDTOS = new ArrayList<>();
        for (ProductImage productImage : productImages)
        {
            ProductImageDTO productImageDTO = new ProductImageDTO();
            productImageDTO.setProduct_id(productImage.getProduct().getId());
            productImageDTO.setUrl_Image(productImage.getUrl_Image());
            productImageDTO.setId(productImage.getId());
            productImageDTOS.add(productImageDTO);
        }
        if (productImages.isEmpty())
        {
            return new ResponseEntity<>("Empty", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(productImageDTOS, HttpStatus.OK);
    }
    @DeleteMapping (value = {"/{id}"})
    ResponseEntity<?> deleteProductImagebyproductId(@PathVariable int id)
    {
        productImageService.deleteById(id);
        return new ResponseEntity<>("đã xóa ảnh",HttpStatus.OK);
    }


}
