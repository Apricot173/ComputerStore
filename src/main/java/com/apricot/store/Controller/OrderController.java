package com.apricot.store.Controller;

import com.apricot.store.Entity.Order;
import com.apricot.store.Service.IOrderService;
import com.apricot.store.Utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    @PostMapping("/createOrder")
    public JsonResult createOrder(Integer aid, Long totalPrice ,HttpSession session){
        //System.out.println("aid: " + aid + " totalPrice: " + totalPrice);
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        Integer oid = orderService.insertOrder(uid, username, aid, totalPrice);
        //System.out.println("oid: " + oid);
        return new JsonResult(SUCCESS_CODE, "创建订单成功", oid);
    }

    @PostMapping("/createOrderItem")
    public JsonResult createOrderItem(Integer oid, Integer cid, HttpSession session) {
        String username = getUsernameFromSession(session);
        orderService.insertOrderItem(username, oid, cid);
        return new JsonResult(SUCCESS_CODE, "创建订单项成功",null);
    }

    @GetMapping("/getOrderDetail/{oid}")
    public JsonResult getOrderDetail(@PathVariable Integer oid, HttpSession session) {
        Order target =  orderService.queryOrderByOid(oid);
        if (!Objects.equals(target.getUid(), getUserIdFromSession(session))) {
            return new JsonResult(498, "无权限查看该订单", null);
        }
        return new JsonResult(SUCCESS_CODE, "获取订单详情成功", target);
    }

    @PostMapping("/updateOrderStatus/{oid}/{status}")
    public JsonResult updateOrderStatus(@PathVariable Integer oid,
                                        @PathVariable Integer status,
                                        HttpSession session) {
        Integer uid = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        Integer newStatus = orderService.updateOrderStatus(oid, status, username);
        return new JsonResult(SUCCESS_CODE, "更新订单状态成功", newStatus);
    }
}
