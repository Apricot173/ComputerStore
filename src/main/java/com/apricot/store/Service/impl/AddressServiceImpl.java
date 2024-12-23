package com.apricot.store.Service.impl;

import com.apricot.store.Entity.Address;
import com.apricot.store.Entity.District;
import com.apricot.store.Mapper.AddressMapper;
import com.apricot.store.Service.IAddressService;
import com.apricot.store.Service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxAddressNum;

    @Override
    public void saveAddress(Integer uid, String username, Address address) {
        Integer addNum = addressMapper.countAddressByUid(uid);
        if (addNum >= maxAddressNum) {
            throw new TooManyAddressesException("地址数量已达到上限，请删除部分地址后再添加");
        }
        address.setUid(uid);
        addNum = (addNum == 0) ? 1 : 0;
        address.setIsDefault(addNum);
        // 其他字段来自控制层

        //补全日志
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.saveAddress(address);
        if (rows != 1) {
            throw new InsertException("保存地址失败，发生未知错误");
        }
    }

    @Override
    public List<District> getDistrictsByParent(String parent) {
        List<District> result = addressMapper.getDistrictsByParent(parent);
        if (result == null || result.isEmpty()) {
            throw new RecordNotFoundException("没有找到对应的行政区划相关信息");
        }
        // 过滤District中的 id 和 parent 字段
        result.forEach(district -> {
            district.setId(null);
            district.setParent(null);
        });
        return result;
    }

    @Override
    public String getDistrictNameByCode(String code) {
        return addressMapper.getNameByCode(code);
    }

    @Override
    public List<Address> getAddressesByUid(Integer uid) {
        return addressMapper.getAddressesByUid(uid);
    }

    @Override
    public void setDefaultAddress(Integer uid, String username, Integer addressId) {
        Address target = addressMapper.getAddressByAid(addressId);
        if (target == null) {
            throw new RecordNotFoundException("没有找到对应的地址信息");
        }
        if (!uid.equals(target.getUid())) {
            throw new InvalidPasswordException("非法操作，你不能操作其他用户的地址");
        }
        Integer rows = addressMapper.setAllNonDefault(target.getUid());
        if (!Objects.equals(rows, addressMapper.countAddressByUid(target.getUid()))) {
            throw new UpdateException("本异常应该不会出现，请联系管理员");
        }
        rows = addressMapper.setDefault(addressId, username, new Date());
        if (rows != 1) {
            throw new UpdateException("设置默认地址失败，发生未知错误");
        }
    }

    @Override
    public void deleteAddress(Integer uid,Integer addressId) {
        Address target = addressMapper.getAddressByAid(addressId);
        if (target == null) {
            throw new RecordNotFoundException("没有找到对应的地址信息");
        }
        if (!uid.equals(target.getUid())) {
            throw new InvalidPasswordException("非法操作，你不能操作其他用户的地址");
        }
        Integer rows = addressMapper.deleteAddress(addressId);
        if (rows != 1) {
            throw new ServiceException("删除地址失败，发生未知错误");
        }
    }

}
