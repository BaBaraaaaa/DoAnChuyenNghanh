package com.example.QuanLyBanHang.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data// hỗ trợ get các contrustor từ lombok
public class UserDTO {
    private int id;
    private String avatar;
    private String email;
    private String loginType;
    private String password;
    private String phoneNumber;
    private String role;
    private String userName;
    private String full_name;

}
