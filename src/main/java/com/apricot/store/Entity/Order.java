package com.apricot.store.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单实体类
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Order extends BaseEntity {
    private Integer oid;
    private Integer uid;
    private String recvName;
    private String recvPhone;
    private String recvProvince;
    private String recvCity;
    private String recvArea;
    private String recvAddress;
    private Long totalPrice;
    private Integer status;
    private Date orderTime;
    private Date payTime;
}
