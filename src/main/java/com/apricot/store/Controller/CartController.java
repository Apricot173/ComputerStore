package com.apricot.store.Controller;

import com.apricot.store.Entity.Cart;
import com.apricot.store.Entity.dto.CartVo;
import com.apricot.store.Entity.dto.ListedCartsDto;
import com.apricot.store.Service.ICartService;
import com.apricot.store.Utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

    @Autowired
    private ICartService cartService;

    // 添加商品到购物车 以及 更新购物车商品数量 都用这个接口
    @PostMapping("/addToCart")
    public JsonResult addToCart(@RequestBody Cart cart, HttpSession session) {
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        cart.setUid(uid);
        cartService.addToCart(cart, username);
        return new JsonResult(SUCCESS_CODE, "加入购物车成功", null);
    }

    @GetMapping("/queryCarts")
    public JsonResult queryCarts(HttpSession session) {
        Integer uid = getUserIdFromSession(session);
        List<CartVo> carts = cartService.queryCartsByUid(uid);
        if (carts.isEmpty()) {
            return new JsonResult(499, "购物车为空", null);
        }
        return new JsonResult(SUCCESS_CODE, "查询购物车成功", carts);
    }

    @PostMapping("/queryListedCarts")
    public JsonResult queryListedCarts(@RequestBody ListedCartsDto dto) {
        List<CartVo> carts = cartService.queryCartsByCidList(dto.getCids());
        if (carts.isEmpty()) {
            return new JsonResult(499, "待结算的数据不能为空", null);
        }
        return new JsonResult(SUCCESS_CODE, "查询待结算数据成功", carts);

    }

    @DeleteMapping("/deleteCart/{cid}")
    public JsonResult deleteCart(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer uid = getUserIdFromSession(session);
        cartService.deleteCartByCid(cid, uid);
        return new JsonResult(SUCCESS_CODE, "删除购物车成功", null);
    }

    @PutMapping("/updateCart/{cid}/{num}")
    public JsonResult updateCart(@PathVariable("cid") Integer cid,
                                 @PathVariable("num") Integer num,
                                 HttpSession session) {
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.updateCartNum(cid, num, uid, username);
        return new JsonResult(SUCCESS_CODE, "更新购物车成功", null);
    }
}
