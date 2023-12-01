package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.ProductDto;
import com.example.QuanLyBanHang.Dto.ProductImageDTO;
import com.example.QuanLyBanHang.FormCreateandUpdate.FormCreateProduct;
import com.example.QuanLyBanHang.entity.Category;
import com.example.QuanLyBanHang.entity.Product;
import com.example.QuanLyBanHang.entity.ProductImage;
import com.example.QuanLyBanHang.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Null;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/product")
public class ApiProductController {
    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;
    @Autowired
    HttpSession session;
    @Autowired
    FileUpload fileUpload;
    @Autowired
    ProductImageService productImageService;

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProductById(id);
            return new ResponseEntity<String>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<String>("nỗi", HttpStatus.BAD_REQUEST);

    }

        @GetMapping
    public ResponseEntity<?> getAllProduct()
    {
      List<Product> productList = productService.getAllProduct();
      ArrayList<ProductDto> productDtos = new ArrayList<>();
      for (Product product : productList)
      {
          Category category = categoryService.getCategoryById(product.getCategory().getId());
          System.out.println(category.getId() +"    "+ category.getCategory_Name() );
          List<ProductImage> productImage =  productImageService.PRODUCT_IMAGE_LIST(product.getId());
          List<ProductImageDTO> productImageDTOS = new ArrayList<>();
          for (ProductImage prodImage : productImage)
          {

              ProductImageDTO productImageDTO = new ProductImageDTO();
              productImageDTO.setId(prodImage.getId());
              productImageDTO.setUrl_Image(prodImage.getUrl_Image());
              productImageDTO.setProduct_id(prodImage.getProduct().getId());
              productImageDTOS.add(productImageDTO);

          }
          ProductDto productDto = new ProductDto();
          productDto.setId(product.getId());
          productDto.setProductName(product.getProduct_Name());
          productDto.setCategory_id(product.getCategory().getId());
          productDto.setDescription(product.getDescription());
          productDto.setCreatedAt(product.getCreated_At());
          productDto.setIsActive(product.getIs_Active());
          productDto.setPrice(product.getPrice());
          productDto.setQuantity(product.getQuantity());
          productDto.setProductImage(productImageDTOS);
          productDtos.add(productDto);
      }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        List<ProductImage> list = new ArrayList<>(product.getProductImage());
        List<ProductImageDTO> productImageDTOS = new ArrayList<>();
        for (ProductImage productImage : list) {
            ProductImageDTO productImageDTO = new ProductImageDTO();
            productImageDTO.setId(product.getId());
            productImageDTO.setUrl_Image(productImage.getUrl_Image());
            productImageDTO.setProduct_id(productImageDTO.getProduct_id());
            productImageDTOS.add(productImageDTO);
        }
        ProductDto productDto = new ProductDto(product.getId(), product.getProduct_Name(), product.getDescription(), product.getSold(),
                product.getIs_Active(), product.getIs_Selling(), product.getCreated_At(), product.getPrice(), product.getQuantity()
                , product.getCategory().getId(), productImageDTOS);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
@PostMapping(value = {"/CreateProduct"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<?> createProduct(@RequestPart("product") FormCreateProduct form,
                                            @RequestPart("imageFile") MultipartFile[] multipartFiles  ) {

    try {
        productService.CreateProduct(form);
        List<Product> listProducts = productService.getAllProduct();
       Product  newPro = listProducts.get(listProducts.size() - 1);
        for (MultipartFile y : multipartFiles) {
            String urlImg = fileUpload.uploadFile(y);
            ProductImage img = new ProductImage();
            img.setProduct(newPro);
            img.setUrl_Image(urlImg);
            productImageService.save(img);
        }

        return new ResponseEntity<>("Product created successfully", HttpStatus.OK);
    } catch (IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>("Failed to create product", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @PutMapping(value = {"/{id}"})
    ResponseEntity<?> UpdateProduct(@PathVariable int id, @RequestPart("product") FormCreateProduct form,
                                     @Nullable @RequestPart("imageFile")  MultipartFile[] multipartFiles) throws IOException {

        ProductDto productDto = new ProductDto();
        List<ProductImageDTO> productImageDTOS = new ArrayList<>();
            Product product = productService.getProductById(id);
            product.setProduct_Name(form.getProductName());
            Category category = categoryService.getCategoryById(form.getCategory_id());
            product.setCategory(category);
            product.setQuantity(form.getQuantity());
            product.setPrice(form.getPrice());
            product.setCreated_At(new Date());
            product.setDescription(form.getDescription());
                 if (multipartFiles !=null)
                 {
              for (MultipartFile y : multipartFiles) {
                String urlImg = fileUpload.uploadFile(y);
                ProductImage img = new ProductImage();
                img.setProduct(product);
                img.setUrl_Image(urlImg);
                productImageService.save(img);
               }
                  }
                 else
                 {
                     List<ProductImage> productImage = productImageService.PRODUCT_IMAGE_LIST(id);
                     for (ProductImage image : productImage) {
                         ProductImageDTO productImageDTO = new ProductImageDTO();
                         productImageDTO.setId(product.getId());
                         productImageDTO.setUrl_Image(image.getUrl_Image());
                         productImageDTO.setProduct_id(productImageDTO.getProduct_id());
                         productImageDTOS.add(productImageDTO);
                     }

                 }
            productService.UpdateProductById(id);
            productDto.setProductName(product.getProduct_Name());
            productDto.setQuantity(product.getQuantity());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCreatedAt(product.getCreated_At());
            productDto.setCategoryId(product.getCategory().getId());
            productDto.setProductImage(productImageDTOS);
         return  new ResponseEntity<>(productDto,HttpStatus.OK);
        }

}



