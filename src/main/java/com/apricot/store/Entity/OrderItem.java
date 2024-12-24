package com.apricot.store.Entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * OrderItem的实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class OrderItem extends BaseEntity {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}