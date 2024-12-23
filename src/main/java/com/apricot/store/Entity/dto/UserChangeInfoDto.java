package com.apricot.store.Entity.dto;

import lombok.Data;

@Data
public class UserChangeInfoDto {
    private String username;
    private Integer gender;
    private String email;
    private String phone;
    private String avatar;
}
