package com.apricot.store.Mapper;

import com.apricot.store.Entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void query4ProductByPriority() {
        List<Product> res =  productMapper.query4ProductsByPriority();
        res.forEach(System.out::println);
    }

    @Test
    public void query4newProduct() {
        List<Product> res =  productMapper.query4NewArrivalProducts();
        res.forEach(System.out::println);
    }

    @Test
    public void queryPsByName() {
        List<Product> res =  productMapper.queryProductByTitle("小新");
        res.forEach(System.out::println);
    }

}
