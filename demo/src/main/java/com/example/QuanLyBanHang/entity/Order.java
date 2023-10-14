package com.example.QuanLyBanHang.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity

@Table(name = "`order`")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total")
    private int total;

    @Column(name = "booking_Date")
    private Date booking_Date;

    @Column(name = "payment_Method", columnDefinition = "nvarchar(1111)")
    private String payment_Method;

    @Column(name = "status", columnDefinition = "nvarchar(1111)")
    private String status;

    @Column(name = "fullname", columnDefinition = "nvarchar(1111)")
    private String fullname;

    @Column(name = "country", columnDefinition = "nvarchar(1111)")
    private String country;

    @Column(name = "address", columnDefinition = "nvarchar(1111)")
    private String address;

    @Column(name = "phone", columnDefinition = "nvarchar(1111)")
    private String phone;

    @Column(name = "email", columnDefinition = "nvarchar(1111)")
    private String email;

    @Column(name = "note", columnDefinition = "nvarchar(1111)")
    private String note;

    @OneToMany(mappedBy = "order")
    private List<Order_Item> order_Item;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(int id, int total, Date booking_Date, String payment_Method, String status, String fullname, String country, String address, String phone, String email, String note, List<Order_Item> order_Item, User user) {
        this.id = id;
        this.total = total;
        this.booking_Date = booking_Date;
        this.payment_Method = payment_Method;
        this.status = status;
        this.fullname = fullname;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.note = note;
        this.order_Item = order_Item;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getBooking_Date() {
        return booking_Date;
    }

    public void setBooking_Date(Date booking_Date) {
        this.booking_Date = booking_Date;
    }

    public String getPayment_Method() {
        return payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        this.payment_Method = payment_Method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Order_Item> getOrder_Item() {
        return order_Item;
    }

    public void setOrder_Item(List<Order_Item> order_Item) {
        this.order_Item = order_Item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
