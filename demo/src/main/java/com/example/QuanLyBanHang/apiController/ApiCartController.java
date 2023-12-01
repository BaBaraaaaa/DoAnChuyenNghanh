package com.example.QuanLyBanHang.apiController;

import com.example.QuanLyBanHang.Dto.CartDTO;
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

    @GetMapping(value = "/user/{id}")
    ResponseEntity<?> getProductInCartByUser_id(@PathVariable int id)
    {
        User user = userService.getUserById(id);
        if (user == null)
        {
            return  new ResponseEntity<String>("người dùng không tồn tại",HttpStatus.NOT_FOUND);
        }
        List<Product> list = productService.getAllProductInCartByUser_id(user.getId());

        if (list.isEmpty())
        {
            return  new ResponseEntity<String>("bạn chưa thêm sản phẩm",HttpStatus.OK);

        }

        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for (Product product : list)
        {
            Category category = categoryService.getCategoryById(product.getCategory().getId());
            System.out.println(category.getId() +"    "+ category.getCategory_Name() );
            List<ProductImage> productImages = productImageService.PRODUCT_IMAGE_LIST(product.getId());
            ArrayList<ProductImageDTO> productImageDTOS = new ArrayList<>();
            for (ProductImage productImage : productImages)
            {
                ProductImageDTO productImageDTO = new ProductImageDTO();
                productImageDTO.setProduct_id(productImage.getId());
                productImageDTO.setUrl_Image(productImage.getUrl_Image());
                productImageDTO.setProduct_id(productImage.getProduct().getId());
                productImageDTOS.add(productImageDTO);
            }
            ProductDto productDto = new ProductDto(product.getId(),product.getProduct_Name(),product.getDescription(),product.getSold()
                    ,product.getIs_Active(),product.getIs_Selling(),product.getCreated_At(),product.getPrice(),product.getQuantity()
                    ,category.getId(),productImageDTOS);
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
                newCart.setCount(cartC+1);
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
    ResponseEntity<?> deleteProductInCart(@PathVariable("id") int id,@PathVariable("user_id") int user_id)
    {
//        User user  = userService.getUserById(user_id);
//        if (user==null)
//        {
//            return new ResponseEntity<>("Không có user này",HttpStatus.OK);
//        }
//            List<Cart> list = cartService.findAllByUser_id(user.getId());
//            Product product = productService.getProductById(id);
//            int cartC = list.size();
//            for (Cart u : list) {
//                if (u.getProduct().getId() == id) {
//                    u.setCount(u.getCount());
//                    cartService.deleteById(u.getId());
//                    cartC--;
//                }
//            }
//            if (cartC == 0) {
//               cartService.deleteById(id);
//            }
//
//            list = cartService.findAllByUser_id(user.getId());
        List<Cart> list = cartService.findAllByUser_id(user_id);
        int cartC = list.size();
        for (Cart cart :list)
        {
            if (cart.getProduct().getId() == id)
            {
                cart.setCount(cart.getCount());
                cartService.deleteById(cart.getId());
                cartC--;

            }
        }
        if (cartC == 0) {
               cartService.deleteById(id);
            }
        return new ResponseEntity<>("xóa sản phẩm khỏi giỏ thành công",HttpStatus.OK);
    }
    @GetMapping("{id}")
    ResponseEntity<List<CartDTO>> getAllCartByUserId(@PathVariable int id)
    {
        List<Cart> list =  cartService.findAllByUser_id(id);
        List<CartDTO> dtoList = new ArrayList<>();
        for (Cart cart : list)
        {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setId(cart.getId());
            cartDTO.setCount(cart.getCount());
            cartDTO.setUser_id(String.valueOf(cart.getUser().getId()));
            cartDTO.setProduct_id(String.valueOf(cart.getProduct().getId()));
            dtoList.add(cartDTO);
        }
         return new ResponseEntity<List<CartDTO>>(dtoList,HttpStatus.OK);

    }
}
