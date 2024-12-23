package com.apricot.store.Entity.dto;

import com.apricot.store.Utils.CodeNameGenerator;
import lombok.Data;


/**
 * 前端传递的地址信息
 */
@Data
public class AddressDto {
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;

    private CodeNameGenerator cng = new CodeNameGenerator();

    public AddressDto() {
        this.provinceCode = cng.getP(this.provinceName);
        this.cityCode = cng.getC(this.cityName);
        this.areaCode = cng.getA(this.areaName);
    }
}
