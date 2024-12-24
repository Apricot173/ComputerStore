package com.apricot.store.Controller;

import com.apricot.store.Service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{

    @Autowired
    private IOrderService orderService;



}
