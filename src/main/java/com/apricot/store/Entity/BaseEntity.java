package com.apricot.store.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


/**
 * 实体类基类
 * @author Apricot
 *
 *
 * */


@Data
public class BaseEntity implements Serializable {
    private String createdUser;
    // DATETIME 对应 java.util.Date
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;
}
