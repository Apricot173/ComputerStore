package com.apricot.store.Entity;

import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;

}
