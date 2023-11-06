package com.example.QuanLyBanHang.service.impl;

import com.example.QuanLyBanHang.Dto.ProductDto;
import com.example.QuanLyBanHang.entity.Cart;
import com.example.QuanLyBanHang.entity.Product;
import com.example.QuanLyBanHang.entity.User;
import com.example.QuanLyBanHang.repository.CartRepository;
import com.example.QuanLyBanHang.repository.ProductRepository;
import com.example.QuanLyBanHang.repository.UserRepository;
import com.example.QuanLyBanHang.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public void deleteById(int id) {
        cartRepository.deleteById(id);

    }

    @Override
    public List<Cart> GetAllCartByUser_id(int user_id) {
        User user = userRepository.findById(user_id).get();
        List<Cart> list = cartRepository.findAll();
        List<Cart> carts = new ArrayList<>();
        for (Cart cart : list)
        {
            if (cart.getUser().getId() == user_id)
            {
                carts.add(cart);
            }

        }
        return carts;
    }


    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);

    }

    @Override
    public List<Cart> findAllByUser_id(int user_id) {
        return cartRepository.findAllByUser_id(user_id);
    }


}
