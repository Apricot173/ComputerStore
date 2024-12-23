package com.apricot.store.Mapper;

import com.apricot.store.Entity.Cart;
import com.apricot.store.Entity.dto.CartVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void testCart() {

        //Cart cart = new Cart(null, 1000, 10000017, 5000L, 1);
        //cartMapper.insertCart(cart);

        Cart res = cartMapper.queryCartByUidAndPid(1000, 10000017);
        System.out.println(res);

        res.setPrice(4999L);
        res.setNum(5);
        cartMapper.updateCart(res);
        res = cartMapper.queryCartByUidAndPid(1000, 10000017);
        System.out.println(res);
    }

    @Test
    public void testQueryCartByUid() {
        List<CartVo> carts = cartMapper.queryCartByUid(24);
        carts.forEach(System.out::println);
    }

    @Test
    public void testDelCartByCid() {
        cartMapper.deleteCartByCid(5);
    }

}
