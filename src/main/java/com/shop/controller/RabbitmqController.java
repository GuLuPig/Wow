package com.shop.controller;

import com.shop.service.OrderService;
import com.shop.service.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/mq")
public class RabbitmqController {

    @Autowired
    UserService userService;

    @Resource
    private RabbitTemplate amqpTemplate;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/rmq")
    public void sendMsg(HttpServletResponse response) {

        try {
            for (int i = 0; i < 5; i++){
                amqpTemplate.convertAndSend("test_rmq_exchange", "test_queue_patt", "rmqProducer-sendMsg.......");
                System.out.println("One Msg sended.....");
            }
            response.getWriter().write("message sended....................");
        } catch (Exception e) {
        }
    }

    //批量添加用户
    @RequestMapping("/test")
    public String testAddUser(){

        orderService.testTranscational();

        return null;
    }


}
