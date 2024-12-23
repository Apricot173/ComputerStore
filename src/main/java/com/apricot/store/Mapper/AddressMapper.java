package com.apricot.store.Mapper;

import com.apricot.store.Entity.Address;
import com.apricot.store.Entity.District;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    /**
     * 用户新建地址
     * @param address 地址实体
     * @return 受影响的行数
     */
//    @Insert("INSERT INTO " +
//            "t_address (uid, name, " +
//            "province_name, province_code, city_name, city_code, area_name, area_code, " +
//            "zip, address, phone, tel, tag, is_default, " +
//            "created_user, created_time, modified_user, modified_time)" +
//            "VALUES " +
//            "(#{uid}, #{name}, " +
//            "#{provinceName}, #{provinceCode}, #{cityName}, #{cityCode}, #{areaName}, #{areaCode}, " +
//            "#{zip}, #{address}, #{phone}, #{tel}, #{tag}, #{isDefault}, " +
//            "#{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime}"))
    @Insert("INSERT INTO t_address (uid, name, province_name, province_code, city_name, city_code, area_name, area_code, zip, address, phone, tel, tag, is_default, created_user, created_time, modified_user, modified_time) " +
            "VALUES (#{uid}, #{name}, #{provinceName}, #{provinceCode}, #{cityName}, #{cityCode}, #{areaName}, #{areaCode}, #{zip}, #{address}, #{phone}, #{tel}, #{tag}, #{isDefault}, #{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime})")
    Integer saveAddress(Address address);

    /**
     * 根据用户id查询地址数量
     * @param uid 用户id
     * @return 地址数量
     */
    @Select("SELECT COUNT(*) FROM t_address WHERE uid = #{uid}")
    Integer countAddressByUid(Integer uid);

    @Select("SELECT * FROM t_dict_district WHERE parent = #{parent} ORDER BY code")

    public List<District> getDistrictsByParent(String parent);

    @Select("SELECT name FROM t_dict_district WHERE code = #{code}")
    public String getNameByCode(String code);

    // 获取用户所有地址
    @Select("SELECT * FROM t_address WHERE uid = #{uid} ORDER BY is_default DESC, created_time DESC")
    public List<Address> getAddressesByUid(Integer uid);

    // 查找某一条地址记录
    @Select("SELECT * FROM t_address WHERE aid = #{aid}")
    public Address getAddressByAid(Integer aid);

    // 将所有地址设为非默认
    @Update("UPDATE t_address SET is_default = 0 WHERE uid = #{uid}")
    public Integer setAllNonDefault(Integer uid);

    // 设置某一条地址为默认
    @Update("UPDATE t_address " +
            "SET is_default = 1 , modified_user = #{modifiedUser}, modified_time = #{modifiedTime} " +
            "WHERE aid = #{aid}")
    public Integer setDefault(Integer aid, String modifiedUser, Date modifiedTime);

    // 修改更新地址详细信息
    @Update("UPDATE t_address " +
            "SET modified_user = #{mUser}, modified_time = #{mTime}, name = #{name} " +
            "WHERE aid = #{aid}")
    public Integer updateAddress(@Param("aid") Integer targetAid,
                                 @Param("name") String newReceiver,
                                 @Param("mUser") String modifiedUser,
                                 @Param("mTime") Date modifiedTime);

    // 删除某一条地址记录
    @Delete("DELETE FROM t_address WHERE aid = #{aid}")
    public Integer deleteAddress(Integer aid);
}
