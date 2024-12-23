package com.apricot.store.Entity;


import lombok.Data;

import java.time.Year;

/**
 * OrderItem的实体类
 */
@Data
public class OrderItem extends BaseEntity {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}