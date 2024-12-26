package com.apricot.store.Mapper;

import com.apricot.store.Entity.Cart;
import com.apricot.store.Entity.dto.CartVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper {

    // 根据uid和pid查询某条cid的购物车记录
    @Select("SELECT * FROM t_cart WHERE uid = #{uid} AND pid = #{pid}")
    public Cart queryCartByUidAndPid(Integer uid, Integer pid);

    // 根据uid和pid查询cid
    @Select("SELECT cid FROM t_cart WHERE uid = #{uid} AND pid = #{pid}")
    public Integer queryCidByUidAndPid(Integer uid, Integer pid);

    // 根据cid查询购物车记录
    @Select("SELECT * FROM t_cart WHERE cid = #{cid}")
    public Cart queryCartByCid(Integer cid);

    //更新某条购物车记录
    @Update("UPDATE t_cart " +
            "SET num = #{num}, price = #{price}, modified_user = #{modifiedUser}, modified_time = #{modifiedTime} " +
            "WHERE cid = #{cid}")
    public Integer updateCart(Cart cart);

    // 插入一条购物车记录
    @Insert("INSERT INTO t_cart (uid, pid, num, price, created_user, created_time, modified_user, modified_time) " +
            "VALUES (#{uid}, #{pid}, #{num}, #{price}, #{createdUser}, #{createdTime}, #{modifiedUser}, #{modifiedTime})")
    public Integer insertCart(Cart cart);

    // 根据uid获取所有购物车记录
    @Select("SELECT c.cid,c.pid,c.`uid`,c.price,c.num,p.title,p.image,p.`price`" +
            "AS real_price FROM t_cart c LEFT JOIN t_product p ON c.pid = p.`id`" +
            "WHERE c.`uid` = #{uid} ORDER BY c.created_time DESC")
    public List<CartVo> queryCartByUid(Integer uid);

    // 根据cid列表获取所有购物车记录
    @Select({"<script>",
            "SELECT c.cid,c.pid,c.`uid`,c.price,c.num,p.title,p.image,p.`price`" +
            "AS real_price FROM t_cart c LEFT JOIN t_product p ON c.pid = p.`id`" +
            "WHERE c.cid IN (" +
                    "<foreach collection='cidList' item='cid' separator=','>#{cid}</foreach>" +
                    ") ORDER BY c.created_time DESC",
            "</script>"})
    public List<CartVo> queryCartByCidList(List<Integer> cidList);

    // 根据cid删除购物车记录
    @Delete("DELETE FROM t_cart WHERE cid = #{cid}")
    public Integer deleteCartByCid(Integer cid);
}
