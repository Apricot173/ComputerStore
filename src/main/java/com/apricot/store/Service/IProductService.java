package com.apricot.store.Service;

import com.apricot.store.Entity.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IProductService {
    public List<Product> queryPriorityProduct();

    public List<Product> queryNewProduct();

    public Product queryProductInfoById(Integer id);

    //根据名称进行模糊查询的抽象方法
    List<Product> queryProductByTitle(String title);
}
