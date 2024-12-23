package com.apricot.store.Service;

import com.apricot.store.Entity.Cart;
import com.apricot.store.Entity.dto.CartVo;
import com.apricot.store.Service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {

    @Autowired
    private ICartService cartService;

    @Test
    public void testAddProductToCart() {
        Cart newCart = new Cart();
        newCart.setUid(1000);
        newCart.setPid(10000018);
        newCart.setNum(2);
        newCart.setPrice(5000L);

        cartService.addToCart(newCart, "admin");
        Cart res = cartService.queryCartByUidAndPid(1000, 10000018);
        System.out.println(res);
    }

    @Test
    public void testUpdateCart() {

        try {
            cartService.updateCartNum(7, 2 ,24,  "admin");

            List<CartVo> carts  = cartService.queryCartsByUid(24);
            carts.forEach(System.out::println);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
