package com.example.QuanLyBanHang.Dto;

public class CartDTO {
    private int id;
    private int count;
    private String user_id;
    private String product_id;

    public CartDTO() {
    }

    public CartDTO(int id, int count, String user_id, String product_id) {
        this.id = id;
        this.count = count;
        this.user_id = user_id;
        this.product_id = product_id;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
