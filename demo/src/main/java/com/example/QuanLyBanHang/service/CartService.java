package com.example.QuanLyBanHang.service;

import com.example.QuanLyBanHang.Dto.ProductDto;
import com.example.QuanLyBanHang.entity.Cart;
import com.example.QuanLyBanHang.entity.Product;

import java.util.List;

public interface CartService {
    void deleteById(int id);
    List<Cart> GetAllCartByUser_id(int user_id);

    void saveCart(Cart cart);

}
