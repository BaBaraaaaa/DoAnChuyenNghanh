package com.example.QuanLyBanHang.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity

@Table(name = "order_item")
public class Order_Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Order_Item() {
    }

    public Order_Item(int id, int count, Product product, Order order) {
        this.id = id;
        this.count = count;
        this.product = product;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Order_Item{" +
                "id=" + id +
                ", count=" + count +
                ", product=" + product +
                ", order=" + order +
                '}';
    }
}
