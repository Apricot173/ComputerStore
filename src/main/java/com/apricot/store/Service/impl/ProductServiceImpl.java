package com.apricot.store.Service.impl;

import com.apricot.store.Entity.Product;
import com.apricot.store.Mapper.ProductMapper;
import com.apricot.store.Service.IProductService;
import com.apricot.store.Service.ex.ProductNotFoundException;
import com.apricot.store.Service.ex.ProductUnavailableException;
import com.apricot.store.Service.ex.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> queryPriorityProduct() {
        List<Product> topPriority4Products = productMapper.query4ProductsByPriority();
        if (topPriority4Products.isEmpty()) {
            throw new ServiceException("没有优先商品，这个异常通常出自于数据库无数据");
        }
        return topPriority4Products;
    }

    @Override
    public List<Product> queryNewProduct() {
        List<Product> topNew4Products = productMapper.query4NewArrivalProducts();
        if (topNew4Products.isEmpty()) {
            throw new ServiceException("没有新增商品，这个异常通常出自于数据库无数据");
        }
        return topNew4Products;
    }

    @Override
    public Product queryProductInfoById(Integer id) {
        Product product = productMapper.queryProductById(id);
        if (product == null) {
            throw new ProductNotFoundException("没有商品信息");
        }
        if (product.getStatus() != 1) { // status 1-上架 2-下架 3-删除
            throw new ProductUnavailableException("商品状态异常，不可用");
        }
        // 需要title,sell_point,price,image信息于product.html页面展示
        Product res = new Product();
        res.setId(product.getId());
        res.setTitle(product.getTitle());
        res.setSellPoint(product.getSellPoint());
        res.setPrice(product.getPrice());
        res.setImage(product.getImage());
        return res;
    }

    @Override
    public List<Product> queryProductByTitle(String title) {
        //调用持久层方法进行查询
        return productMapper.queryProductByTitle(title);
    }


}
