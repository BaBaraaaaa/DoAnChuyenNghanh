package com.example.QuanLyBanHang.FormCreateandUpdate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data // lombok giúp generate các hàm constructor, get, set v.v.
@Getter
@Setter
public class FormCreateUser {
    private String user_name;
    private String password;
    private String email;
    private String full_name;
    private String role;
    private String phone_number;
    private String login_type;
    private String avatar;
}
