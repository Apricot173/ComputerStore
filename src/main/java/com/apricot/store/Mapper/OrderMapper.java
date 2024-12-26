package com.apricot.store.Mapper;

import com.apricot.store.Entity.Order;
import com.apricot.store.Entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.Year;
import java.util.Date;
import java.util.List;

public interface OrderMapper {
    // Order 插入一条记录
    @Insert("insert into t_order(uid,recv_name,recv_phone,recv_province,recv_city,recv_area,recv_address," +
            "total_price,status,order_time,pay_time,created_user,created_time,modified_user,modified_time)" +
            "values (#{uid},#{recvName},#{recvPhone},#{recvProvince},#{recvCity},#{recvArea},#{recvAddress},\n" +
            "#{totalPrice},#{status},#{orderTime},#{payTime},#{createdUser},#{createdTime}," +
            "#{modifiedUser},#{modifiedTime})")
    @Options(useGeneratedKeys = true, keyProperty = "oid")
    Integer insertAnOrder(Order order);

    // OrderItem 插入一条记录
    @Insert("insert into t_order_item(oid,pid,title,image,price,num,created_user,created_time,modified_user,modified_time)" +
            "values (#{oid},#{pid},#{title},#{image},#{price},#{num},#{createdUser},#{createdTime},#{modifiedUser},#{modifiedTime})")
    Integer insertAnOrderItem(OrderItem orderItem);

    // 查找
    @Select("select * from t_order where oid = #{oid}")
    Order queryOrderByOid(Integer oid);

    @Select("select * from t_order_item where oid = #{oid}")
    List<OrderItem> queryOrderItemsByOid(Integer oid);

    // 更新支付状态
    @Update("update t_order set status = #{status}, pay_time = #{payTime}, " +
            "modified_user = #{modifiedUser}, modified_time = #{modifiedTime} where oid = #{oid}")
    Integer updateStatusByOid(Integer oid, Integer status, Date payTime, String modifiedUser, Date modifiedTime);
}
