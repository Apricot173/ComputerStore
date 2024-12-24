package com.apricot.store.Service;

public interface IOrderService {

    /**
     * 增加一个订单
     * @param uid uid session获取
     * @param username username session获取
     * @param aid 用以查询订单保存的address相关信息
     * @param totalPrice 订单总价
     */
    public void insertOrder(Integer uid,
                            String username,
                            Integer aid,
                            Long totalPrice);

    public void insertOrderItem(String username,
                                Integer oid,
                                Integer cid);
}
