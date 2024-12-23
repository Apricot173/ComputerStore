package com.apricot.store.Service;

import com.apricot.store.Entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {

    @Autowired
    private IProductService productService;

    @Test
    public void get4TopProducts() {
        List<Product> top5Products = productService.queryPriorityProduct();
        top5Products.forEach(System.out::println);
    }

    @Test
    public void getProductById() {
        Product product = productService.queryProductInfoById(10000021);
        System.out.println(product);
    }

}
