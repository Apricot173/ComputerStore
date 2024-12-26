package com.apricot.store.Service;

import com.apricot.store.Entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Test
    public void testUpdateOrderStatus_0() {
        try {
            orderService.updateOrderStatus(3, 1, "admin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryOrder() {
        try {
            List<OrderItem> items = orderService.queryOrderItemByOid(10);
            items.forEach(item -> System.out.println(item.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
