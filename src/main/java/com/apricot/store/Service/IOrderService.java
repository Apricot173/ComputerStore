package com.apricot.store.Service;

import com.apricot.store.Entity.OrderItem;

import java.util.List;

public interface IOrderService {

    /**
     * 增加一个订单
     * @param uid uid session获取
     * @param username username session获取
     * @param aid 用以查询订单保存的address相关信息
     * @param totalPrice 订单总价
     */
    public Integer insertOrder(Integer uid,
                            String username,
                            Integer aid,
                            Long totalPrice);

    public void insertOrderItem(String username,
                                Integer oid,
                                Integer cid);

    public List<OrderItem> queryOrderItemByOid(Integer oid);

    public Integer updateOrderStatus(Integer oid, Integer status, String modifiedBy);
}
