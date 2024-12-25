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
    public JsonResult createOrder(@RequestBody List<Integer> cidList, HttpSession session){
        return null;
    }



}
