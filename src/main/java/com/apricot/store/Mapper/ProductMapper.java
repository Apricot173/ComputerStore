package com.apricot.store.Mapper;

import com.apricot.store.Entity.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductMapper {

    /**
     * 拿到4条商品信息，按照优先级排序
     * 包含id,title,price,image前端展示的信息
     * @return List<Product>
     */
    @Select("SELECT id,title,price,image FROM t_product  where status = 1 AND category_id = 163 ORDER BY priority DESC LIMIT 0,4" )
    public List<Product> query4ProductsByPriority();

    // 查询4条 新到货商品信息
    @Select("SELECT id,title,price,image FROM t_product  where status = 1 AND category_id = 163 ORDER BY created_time DESC LIMIT 0,4" )
    public List<Product> query4NewArrivalProducts();

    // 根据id查询商品
    @Select("SELECT * FROM t_product WHERE id = #{id}")
    public Product queryProductById(Integer id);

    // 模糊搜索商品
    @Select("SELECT id,title,sell_point,price,image FROM t_product WHERE status = 1 AND title LIKE CONCAT('%',#{title},'%') ORDER BY priority DESC")
    public List<Product> queryProductByTitle(String title);

}
