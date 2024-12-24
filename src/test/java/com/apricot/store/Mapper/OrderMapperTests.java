package com.apricot.store.Mapper;

import com.apricot.store.Entity.Order;
import com.apricot.store.Entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testInsertAnOrder() {
        Order order = new Order();
        order.setUid(50);
        order.setRecvName("Bryan");
        orderMapper.insertAnOrder(order);
    }

    @Test
    public void testInsertAnOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(100);
        orderItem.setTitle("iPhone X");
        orderMapper.insertAnOrderItem(orderItem);
    }

}
