package com.example.QuanLyBanHang.service;



import com.example.QuanLyBanHang.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface OrderService {

	public void saveOrder(Order order);
	
	List<Order> getAllOrderByUser_Id(String id);


	Page<Order> findAll(Pageable pageable);

	void deleteById(int id);


}
