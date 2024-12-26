package com.apricot.store.Service.impl;

import com.apricot.store.Entity.*;
import com.apricot.store.Mapper.AddressMapper;
import com.apricot.store.Mapper.CartMapper;
import com.apricot.store.Mapper.OrderMapper;
import com.apricot.store.Mapper.ProductMapper;
import com.apricot.store.Service.ICartService;
import com.apricot.store.Service.IOrderService;
import com.apricot.store.Service.ex.InsertException;
import com.apricot.store.Service.ex.OrderNotFoundException;
import com.apricot.store.Service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Integer insertOrder(Integer uid, String username, Integer aid, Long totalPrice) {
        Order target = new Order();

        Address address = addressMapper.getAddressByAid(aid);
        target.setUid(uid).setRecvName(address.getName()).setRecvPhone(address.getPhone())
                .setRecvProvince(address.getProvinceName()).setRecvCity(address.getCityName())
                .setRecvArea(address.getAreaName()).setRecvAddress(address.getAddress())
                .setTotalPrice(totalPrice).setStatus(0);
        target.setOrderTime(new Date()).setPayTime(null);
        target.setCreatedUser(username);
        target.setCreatedTime(new Date());
        target.setModifiedTime(new Date());
        target.setModifiedUser(username);

        Integer result = orderMapper.insertAnOrder(target);
        if (result != 1) {
            throw new InsertException("订单添加失败！");
        }
        return target.getOid();
    }

    @Override
    public void insertOrderItem(String username, Integer oid, Integer cid) {
        OrderItem target = new OrderItem();

        Cart cart = cartMapper.queryCartByCid(cid);
        Product product = productMapper.queryProductById(cart.getPid());

        target.setOid(oid).setPid(cart.getPid()).setTitle(product.getTitle())
                .setNum(cart.getNum()).setPrice(cart.getPrice()).setImage(product.getImage());
        target.setCreatedUser(username);
        target.setCreatedTime(new Date());
        target.setModifiedTime(new Date());
        target.setModifiedUser(username);

        Integer result = orderMapper.insertAnOrderItem(target);
        if (result != 1) {
            throw new InsertException("订单添加内容失败！");
        }
    }

    @Override
    public List<OrderItem> queryOrderItemByOid(Integer oid) {
        Order target = orderMapper.queryOrderByOid(oid);
        if (target == null) {
            throw new OrderNotFoundException("订单不存在！");
        }
        List<OrderItem> orderItems = orderMapper.queryOrderItemsByOid(oid);
        if (orderItems.isEmpty()) {
            throw new OrderNotFoundException("订单内容不存在！");
        }
        return orderItems;
    }

    @Override
    public Order queryOrderByOid(Integer oid) {
        Order target = orderMapper.queryOrderByOid(oid);
        if (target == null) {
            throw new OrderNotFoundException("订单不存在！");
        }
        return target;
    }

    @Override
    public Integer updateOrderStatus(Integer oid, Integer status, String modifiedBy) {
        Order target = orderMapper.queryOrderByOid(oid);
        if (target == null) {
            throw new OrderNotFoundException("订单不存在！");
        }

        Integer res = -1;
        Integer prevStatus = target.getStatus();
        if (prevStatus == 0) { // 未支付
            Date payTime = new Date();
            res = orderMapper.updateStatusByOid(oid, status, payTime, modifiedBy, new Date());
            // todo 更改订单Modiefied字段

            // 删除订单对应的购物车记录
            List<OrderItem> items = orderMapper.queryOrderItemsByOid(oid);
            items.forEach(item -> {
                Integer cid = cartMapper.queryCidByUidAndPid(target.getUid(), item.getPid());
                Integer delRes = cartMapper.deleteCartByCid(cid);
                if (delRes!= 1) {
                    throw new UpdateException("订单删除购物车记录失败！");
                }
            });
        } else {
            res = orderMapper.updateStatusByOid(oid, status, target.getPayTime(), modifiedBy, new Date());
        }

        if (res == -1) {
            throw new UpdateException("订单状态更新失败！");
        }

        return status;
    }
}
