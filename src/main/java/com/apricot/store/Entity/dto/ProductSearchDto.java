package com.apricot.store.Entity.dto;

import lombok.Data;

@Data
public class ProductSearchDto {
    private Integer id;
    private String image;
    private String title;
    private String sellPoint;
    private Long price;
}
