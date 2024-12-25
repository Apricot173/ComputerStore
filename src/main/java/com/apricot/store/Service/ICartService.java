package com.apricot.store.Service;

import com.apricot.store.Entity.Cart;
import com.apricot.store.Entity.dto.CartVo;

import java.util.List;

public interface ICartService {
    public void addToCart(Cart cart, String username);

    public Cart queryCartByUidAndPid(Integer uid, Integer pid);

    public List<CartVo> queryCartsByUid(Integer uid);

    public List<CartVo> queryCartsByCidList(List<Integer> cidList);

    public void deleteCartByCid(Integer cid, Integer uid);

    public void updateCartNum(Integer cid, Integer newNum, Integer uid, String modifiedBy);
}
