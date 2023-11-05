package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.ProductDto;
import com.example.QuanLyBanHang.Dto.ProductImageDTO;
import com.example.QuanLyBanHang.entity.*;
import com.example.QuanLyBanHang.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart/")
public class ApiCartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @GetMapping(value = "{id}")
    ResponseEntity<?> getProductInCartByUser_id(@PathVariable int id)
    {
        User user = userService.getUserById(id);
        if (user == null)
        {
            return  new ResponseEntity<>("người dùng không tồn tại",HttpStatus.NOT_FOUND);
        }
        List<Product> list = productService.getAllProductInCartByUser_id(user.getId());
        if (list.isEmpty())
        {
            return  new ResponseEntity<>("bạn chưa thêm sản phẩm",HttpStatus.OK);

        }

        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for (Product product : list)
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
    @GetMapping(value = "{id}/{user_id}")
    ResponseEntity<?> addToCart(@PathVariable int id, @PathVariable int user_id)
    {
        User user  = userService.getUserById(user_id);
        if (user!=null)
        {
            List<Cart>  list = cartService.GetAllCartByUser_id(user.getId());
            Product product = productService.getProductById(id);
            int cartC = 0;
            for (Cart u :list)
            {
                if (u.getProduct().getId() == id)
                {
                    u.setCount(u.getCount()+ 1);
                    cartService.saveCart(u);
                    cartC++;
                }
            }
            if (cartC == 0) {
                Cart newCart = new Cart();
                newCart.setCount(cartC);
                newCart.setProduct(product);
                newCart.setUser(user);
                cartService.saveCart(newCart);
            }
            list = cartService.GetAllCartByUser_id(user.getId());
            return  new ResponseEntity<>("Thêm thành công vào giỏ hàng",HttpStatus.OK);
        }
        return  new ResponseEntity<>("Lỗi",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(value = "{id}/{user_id}")
    ResponseEntity<?> deleteProductInCart(@PathVariable int id,@PathVariable int user_id)
    {
        User user  = userService.getUserById(user_id);
        if (user==null)
        {
            return new ResponseEntity<>("Không có user này",HttpStatus.OK);
        }
        if (user!=null) {
            List<Cart> list = cartService.GetAllCartByUser_id(user.getId());
            Product product = productService.getProductById(id);
            int cartC = list.size();
            for (Cart u : list) {
                if (u.getProduct().getId() == id) {
                    u.setCount(u.getCount() - 1);
                    cartService.deleteById(u.getId());
                    cartC--;
                }
            }
            if (cartC == 0) {
               cartService.deleteById(id);
            }

            list = cartService.GetAllCartByUser_id(user.getId());
        }
        return new ResponseEntity<>("xóa sản phẩm khỏi giỏ thành công",HttpStatus.OK);

    }
}
