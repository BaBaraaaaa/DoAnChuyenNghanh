package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.ProductDto;
import com.example.QuanLyBanHang.Dto.ProductImageDTO;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateProduct;
import com.example.QuanLyBanHang.entity.Category;
import com.example.QuanLyBanHang.entity.Product;
import com.example.QuanLyBanHang.entity.ProductImage;
import com.example.QuanLyBanHang.repository.ProductRepository;
import com.example.QuanLyBanHang.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "api/v1/product")
public class ApiProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;
    @Autowired
    HttpSession session;

    @GetMapping
    public ResponseEntity<?> getAllProduct()
    {
      List<Product> productList = productService.getAllProduct();
      ArrayList<ProductDto> productDtos = new ArrayList<>();
      for (Product product : productList)
      {
          Category category = categoryService.getCategoryById(product.getCategory().getId());
          System.out.println(category.getId() +"    "+ category.getCategory_Name() );
          List<ProductImage> productImages = productImageService.getAllbyId(product.getId());
          ArrayList<ProductImageDTO> productImageDTOS = new ArrayList<>();
          for (ProductImage productImage : productImages)
          {
              ProductImageDTO productImageDTO = new ProductImageDTO();
              productImageDTO.setId(productImage.getId());
              productImageDTO.setUrlImage(productImage.getUrl_Image());
              productImageDTO.setProductId(productImage.getProduct().getId());
              productImageDTOS.add(productImageDTO);
          }

          ProductDto productDto = new ProductDto(product.getId(),product.getProduct_Name(),product.getDescription(),product.getSold()
                  ,product.getIs_Active(),product.getIs_Selling(),product.getCreated_At(),product.getPrice(),product.getQuantity()
          ,category.getCategory_Name(),productImageDTOS);
          productDtos.add(productDto);
      }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    @GetMapping(value ="/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id)
    {
        Product product = productService.getProductById(id);
        List<ProductImage> productImages = productImageService.getAllbyId(product.getId());
        ArrayList<ProductImageDTO> productImageDTOS = new ArrayList<>();
        for (ProductImage productImage : productImages)
        {
            ProductImageDTO productImageDTO = new ProductImageDTO();
            productImageDTO.setId(productImage.getId());
            productImageDTO.setUrlImage(productImage.getUrl_Image());
            productImageDTO.setProductId(productImage.getProduct().getId());
            productImageDTOS.add(productImageDTO);
        }
        ProductDto productDto = new ProductDto(product.getId(),product.getProduct_Name(),product.getDescription(),product.getSold(),
                product.getIs_Active(),product.getIs_Selling(),product.getCreated_At(),product.getPrice(),product.getQuantity()
                ,product.getCategory().getCategory_Name(),productImageDTOS);
            return  new ResponseEntity<>(productDto,HttpStatus.OK);
    }
    @PostMapping
    public  ResponseEntity<?> createProduct(@RequestBody FormCreateProduct formCreateProduct )
    {
         productService.CreateProduct(formCreateProduct);
        return  new ResponseEntity<>("Create Product Successfully",HttpStatus.OK);
    }

//    @GetMapping
//    public  ResponseEntity<?> getProductBestSeller()
//    {
//        List<Product> productList = productService.getAllProduct();
//        ArrayList<ProductDto> productDtos = new ArrayList<>();
//        for (Product product : productList)
//        {
//
//            Category category = categoryService.getCategoryById(product.getCategory().getId());
//            System.out.println(category.getId() +"    "+ category.getCategory_Name() );
//            List<ProductImage> productImages = productImageService.getAllbyId(product.getId());
//            ArrayList<ProductImageDTO> productImageDTOS = new ArrayList<>();
//            for (ProductImage productImage : productImages)
//            {
//                ProductImageDTO productImageDTO = new ProductImageDTO();
//                productImageDTO.setId(productImage.getId());
//                productImageDTO.setUrlImage(productImage.getUrl_Image());
//                productImageDTO.setProductId(productImage.getProduct().getId());
//                productImageDTOS.add(productImageDTO);
//            }
//
//            ProductDto productDto = new ProductDto(product.getId(),product.getProduct_Name(),product.getDescription(),product.getSold()
//                    ,product.getIs_Active(),product.getIs_Selling(),product.getCreated_At(),product.getPrice(),product.getQuantity()
//                    ,category.getCategory_Name(),productImageDTOS);
//            productDtos.add(productDto);
//        }
//return null;
//    }


}
