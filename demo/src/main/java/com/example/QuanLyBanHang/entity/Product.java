package com.example.QuanLyBanHang.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@Getter
@Setter

@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_Name", columnDefinition = "nvarchar(1111)")
    private String product_Name;

    @Column(name = "description", columnDefinition = "nvarchar(11111)")
    private String description;

    @Column(name = "sold")
    private int sold;

    @Column(name = "is_Active")
    private int is_Active;

    @Column(name = "is_Selling")
    private int is_Selling;

    @Column(name = "created_At")
    private Date created_At;

    @Column(name = "price")
    private int price;

    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> productImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order_Item> order_Item;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> cart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getIs_Active() {
        return is_Active;
    }

    public void setIs_Active(int is_Active) {
        this.is_Active = is_Active;
    }

    public int getIs_Selling() {
        return is_Selling;
    }

    public void setIs_Selling(int is_Selling) {
        this.is_Selling = is_Selling;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductImage> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<ProductImage> productImage) {
        this.productImage = productImage;
    }

    public List<Order_Item> getOrder_Item() {
        return order_Item;
    }

    public void setOrder_Item(List<Order_Item> order_Item) {
        this.order_Item = order_Item;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public Product(int id, String product_Name, String description, int sold, int is_Active, int is_Selling, Date created_At, int price, int quantity, Category category, List<ProductImage> productImage) {
        this.id = id;
        this.product_Name = product_Name;
        this.description = description;
        this.sold = sold;
        this.is_Active = is_Active;
        this.is_Selling = is_Selling;
        this.created_At = created_At;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.productImage = productImage;

    }

    public Product() {
    }
}
