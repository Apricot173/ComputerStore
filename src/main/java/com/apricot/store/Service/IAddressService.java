package com.apricot.store.Service;

import com.apricot.store.Entity.Address;
import com.apricot.store.Entity.District;

import java.util.List;

public interface IAddressService {
    /**
     * 用户新增地址
     * @param uid 用户id
     * @param username 用户名
     * @param address 地址实体类，从控制层传入。
     */
    public void saveAddress(Integer uid, String username, Address address);

    /**
     * 通过parent获取子级行政区的列表
     * @param parent 父级行政区编号
     * @return 子级行政区的列表List<District>
     */
    public List<District> getDistrictsByParent(String parent);

    public String getDistrictNameByCode(String code);

    public List<Address> getAddressesByUid(Integer uid);

    public void setDefaultAddress(Integer uid, String username, Integer addressId);

    public void deleteAddress(Integer uid,Integer addressId);

}
