package com.apricot.store.Controller;

import com.apricot.store.Service.IOrderService;
import com.apricot.store.Utils.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

}
