package com.apricot.store.Entity;

import com.apricot.store.Entity.dto.AddressDto;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address extends BaseEntity implements Serializable {
    private Integer aid;
    private Integer uid;
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
    private Integer isDefault;

    public Address(AddressDto dto) {
        this.name = dto.getName();
        this.provinceName = dto.getProvinceName();
        this.provinceCode = dto.getProvinceCode();
        this.cityName = dto.getCityName();
        this.cityCode = dto.getCityCode();
        this.areaName = dto.getAreaName();
        this.areaCode = dto.getAreaCode();
        this.address = dto.getAddress();
        this.zip = dto.getZip();
        this.phone = dto.getPhone();
        this.tel = dto.getTel();
        this.tag = dto.getTag();
    }
}