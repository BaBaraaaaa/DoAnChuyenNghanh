package com.example.QuanLyBanHang.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data // lombok giúp generate các hàm constructor, get, set v.v.
@Getter
@Setter

@Table(name = "`user`")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login_Type", columnDefinition = "nvarchar(250)")
    private String login_Type;


    @Column(name = "password",columnDefinition = "nvarchar(250)")
    private String password;

    @Column(name = "user_Name", columnDefinition = "nvarchar(250)")
    private String user_Name;

    @Column(name = "avatar", columnDefinition = "nvarchar(250)")
    private String avatar;

    @Column(name = "email", columnDefinition = "nvarchar(250)")
    private String email;

    @Column(name = "phone_Number", columnDefinition = "nvarchar(250)")
    private String phone_Number;

    @Column(name = "full_name" ,columnDefinition = "nvarchar(250)" , nullable = false)
    private  String full_name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> order;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Cart> cart;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login_Type='" + login_Type + '\'' +
                ", password='" + password + '\'' +
                ", user_Name='" + user_Name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", phone_Number='" + phone_Number + '\'' +
                ", full_name='" + full_name + '\'' +
                ", order=" + order +
                ", cart=" + cart +
                ", roles=" + roles +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin_Type() {
        return login_Type;
    }

    public void setLogin_Type(String login_Type) {
        this.login_Type = login_Type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_Number() {
        return phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        this.phone_Number = phone_Number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }
}
