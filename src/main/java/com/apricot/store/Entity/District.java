package com.apricot.store.Entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class District {
    private Integer id;
    private String parent;
    private String name;
    private String code;

}
