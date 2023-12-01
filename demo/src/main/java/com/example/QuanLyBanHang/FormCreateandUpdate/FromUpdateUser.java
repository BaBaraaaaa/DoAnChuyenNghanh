package com.example.QuanLyBanHang.FormCreateandUpdate;

import lombok.Data;

@Data
public class FromUpdateUser {

    private String password;
    private String email;
    private String full_name;
    private String phone_number;
    private String avatar;
}
