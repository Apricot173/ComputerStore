package com.apricot.store.Mapper;

import com.apricot.store.Entity.Address;
import com.apricot.store.Entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;


//    private Integer aid;
//    private Integer uid;
//    private String name;
//    private String provinceName;
//    private String provinceCode;
//    private String cityName;
//    private String cityCode;
//    private String areaName;
//    private String areaCode;
//    private String zip;
//    private String address;
//    private String phone;
//    private String tel;
//    private String tag;
//    private Integer isDefault;
    @Test
    public void saveAddressTest() {
        Address address = new Address(null,17, "测试地址", "北京市", "110000",
                "北京市", "110100", "东城区", "110101",
                "100000", "测试地址", "12345678910", "010-12345678", "家", 0);
        Integer res = addressMapper.saveAddress(address);
        System.out.println(res  + "保存成功");
    }

    @Test
    public void countAddressTest() {
        Integer res = addressMapper.countAddressByUid(17);
        System.out.println(res  + "地址数量");
    }

    @Test
    public void getDistrictByParentTest() {
        List<District> res = addressMapper.getDistrictsByParent("110100");
        for (District d : res) {
            System.out.println(d);
        }
    }

    @Test
    public void getNameByCodeTest() {
        String res = addressMapper.getNameByCode("110100");
        System.out.println(res);
    }

    @Test
    public void getAddressByUidTest() {
        List<Address> res = addressMapper.getAddressesByUid(24);
        for (Address a : res) {
            System.out.println(a);
        }
    }

    @Test
    public void getAddressByAidTest() {
        Address res = addressMapper.getAddressByAid(7);
        System.out.println(res);
    }

    @Test
    public void setallnondefaultTest() {
        addressMapper.setAllNonDefault(24);
        System.out.println("设置成功");
    }

    @Test
    public void setDefaultTest() {
        addressMapper.setDefault(9, "admin", new Date());
        System.out.println("设置成功");
    }

    @Test
    public void updateAddressTest() {
        Address res = addressMapper.getAddressByAid(16);
        System.out.println(res);
        res.setName("update");
        addressMapper.updateAddress(16, "blackwing","admin", new Date());
        res = addressMapper.getAddressByAid(16);
        System.out.println(res);
    }

    @Test
    public void deleteAddressTest() {
        addressMapper.deleteAddress(15);
        System.out.println("删除成功");
    }
}
