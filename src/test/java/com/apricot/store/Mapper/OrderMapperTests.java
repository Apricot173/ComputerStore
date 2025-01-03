package com.apricot.store.Mapper;

import com.apricot.store.Entity.Order;
import com.apricot.store.Entity.OrderItem;
import com.apricot.store.Entity.dto.OrderVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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


    @Test
    public void testFindByOid() {
        Order order = orderMapper.queryOrderByOid(1);
        System.out.println(order);
    }

    @Test
    public void testUpdate() {
        Integer rows = orderMapper.updateStatusByOid(1, 4, new Date(), "admin", new Date());
        System.out.println(rows);
    }

    @Test
    public void testFindAllItemsByOid() {
        List<OrderItem> orderItems = orderMapper.queryOrderItemsByOid(1);
        orderItems.forEach(System.out::println);
    }

    @Test
    public void t2() {
        List<OrderVo> res = orderMapper.queryOrderVoByUid(26);
        res.forEach(System.out::println);
    }

    @Test
    public void t3() {
        List<OrderVo> res = orderMapper.queryOrderVoByOid(29);
        res.forEach(System.out::println);
    }

}
