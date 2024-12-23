package com.apricot.store.Service;

import com.apricot.store.Entity.Address;
import com.apricot.store.Entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {

    @Autowired
    private IAddressService addressService;

    @Test
    public void testCreateAddress() {
        Address ad = new Address(null,null, "测试地址", "北京市", "110000",
                "北京市", "110100", "东城区", "110101",
                "100000", "测试地址", "12345678910", "010-12345678", "家", null);
        addressService.saveAddress(1000, "管理员", ad);
        System.out.println("Address created successfully.");
    }

    @Test
    public void testGetDistrictsByParent() {
        List<District> res = addressService.getDistrictsByParent("86");
        for (District d : res) {
            System.out.println(d);
        }
    }

    @Test
    public void testGetAddressByUId() {
        List<Address> ad = addressService.getAddressesByUid(1000);
        ad.forEach(System.out::println);
    }

    @Test
    public void testSetDefaultAddress() {
        addressService.setDefaultAddress(17,"admin", 3);
        System.out.println("Default address set successfully.");
    }

    @Test
    public void testDeleteAddress() {
        addressService.deleteAddress(17, 16);
        System.out.println("Address deleted successfully.");
    }

}
