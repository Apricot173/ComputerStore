package com.apricot.store.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {

    @Autowired
    private IOrderService orderService;

    @Test
    public void testInsertAnOrder() {
        try {
            orderService.insertOrder(1000, "admin", 1, 1000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertAnOrderItem() {
        try {
            orderService.insertOrderItem("admin_test", 1, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
