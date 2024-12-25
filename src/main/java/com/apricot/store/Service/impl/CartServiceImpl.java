package com.apricot.store.Service.impl;

import com.apricot.store.Entity.Cart;
import com.apricot.store.Entity.dto.CartVo;
import com.apricot.store.Mapper.CartMapper;
import com.apricot.store.Service.ICartService;
import com.apricot.store.Service.ex.CartNotFoundException;
import com.apricot.store.Service.ex.InsertException;
import com.apricot.store.Service.ex.ServiceException;
import com.apricot.store.Service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    /**
     * 某用户添加一条Cart记录
     * @param cart 要插入或更新的Cart记录，包含用户id、商品id、数量、该商品单价
     * @param username 用户名
     */
    @Override
    public void addToCart(Cart cart, String username) {
        // 先查询该用户是否有该商品的购物车记录
        Cart existCart = cartMapper.queryCartByUidAndPid(cart.getUid(), cart.getPid());
        if (existCart == null) {
            // 该用户没有该商品的购物车记录，直接插入
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            int rows = cartMapper.insertCart(cart);
            if (rows != 1) {
                throw new InsertException("新增购物车记录失败，请稍后再试！");
            }
        }
        // 该用户有该商品的购物车记录，更新数量和价格
        else {
            existCart.setNum(existCart.getNum() + cart.getNum());
            existCart.setModifiedUser(username);
            existCart.setModifiedTime(new Date());
            int rows = cartMapper.updateCart(existCart);
            if (rows != 1) {
                throw new UpdateException("更新购物车记录失败，请稍后再试！");
            }
        }
    }

    @Override
    public Cart queryCartByUidAndPid(Integer uid, Integer pid) {
        return cartMapper.queryCartByUidAndPid(uid, pid);
    }

    @Override
    public List<CartVo> queryCartsByUid(Integer uid) {
        return cartMapper.queryCartByUid(uid);
    }

    @Override
    public List<CartVo> queryCartsByCidList(List<Integer> cidList) {
        return cartMapper.queryCartByCidList(cidList);
    }

    @Override
    public void deleteCartByCid(Integer cid, Integer uid) {
        Cart target = cartMapper.queryCartByCid(cid);
        if (target == null)
            throw new UpdateException("删除购物车记录失败，该记录不存在！");
        if (!Objects.equals(target.getUid(), uid))
            throw new ServiceException("非本用户操作，删除失败！");
        int rows = cartMapper.deleteCartByCid(cid);
        if (rows != 1)
            throw new UpdateException("删除购物车记录失败，请稍后再试！");
    }

    @Override
    public void updateCartNum(Integer cid, Integer newNum, Integer uid, String modifiedBy) {
        // 查询该购物车记录是否存在
        Cart target = cartMapper.queryCartByCid(cid);
        if (target == null)
            throw new CartNotFoundException("更新购物车记录失败，该记录不存在！");
        // 查询该购物车记录是否属于该用户
        if (!Objects.equals(target.getUid(), uid))
            throw new ServiceException("非本用户操作，更新失败！");
        // 看 预计更新后的数量 是否合法 (超过库存 或 小于1)
        int resultNum = target.getNum() + newNum;
        if (resultNum < 1 || resultNum > 1000) // 库存上限为1000
            throw new UpdateException("更新购物车记录失败，库存不足或数量小于1！");
        // 更新数量
        target.setNum(resultNum);
        target.setModifiedUser(modifiedBy);
        target.setModifiedTime(new Date());
        int rows = cartMapper.updateCart(target);
        if (rows != 1)
            throw new UpdateException("更新购物车记录失败，请稍后再试！");
    }
}
