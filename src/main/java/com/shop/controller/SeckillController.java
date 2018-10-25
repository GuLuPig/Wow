package com.shop.controller;

import com.shop.entity.Goods;
import com.shop.entity.SeckillOrder;
import com.shop.entity.SeckillUser;
import com.shop.entity.vo.GoodsVo;
import com.shop.entity.vo.SeckMessageVo;
import com.shop.redis.*;
import com.shop.result.CodeMsg;
import com.shop.result.Result;
import com.shop.service.GoodsService;
import com.shop.service.OrderService;
import com.shop.service.SeckillService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {
    @Resource
    private RabbitTemplate amqpTemplate;

    @Autowired
    RedisService redisService;
    @Autowired
    SeckillService seckillService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    private HashMap<Long, Boolean> localOverMap =  new HashMap<Long, Boolean>();
    private final String SECK_KEY="seckill_";

    //提前加载好秒杀新的数据，加载进redis
    public void afterPropertiesSet(){
        List<GoodsVo> goodsVoList=goodsService.getGoodsVo();
        if (goodsVoList==null){ return;
        }
        for (GoodsVo s:goodsVoList){
            String key=String.valueOf(s.getGoods().getId());
            String num= String.valueOf(s.getSeckillGoods().getStockCount());
            redisService.set(SECK_KEY+key,num);
            localOverMap.put(s.getGoods().getId(),false);
        }
    }

    @RequestMapping("/do_seckill")
    @ResponseBody
    public Result doSeckill(@RequestParam("id") long id,@RequestParam("goodsId")long goodsId, HttpSession session){
//        SeckillUser user= (SeckillUser) session.getAttribute("user");
//        if (user==null){//判断用户是否登陆
//            return Result.error(CodeMsg.LOGIN_ERROR);
//        }
        SeckillUser user=new SeckillUser();
        user.setId(id);
        //标记
        boolean over=localOverMap.get(goodsId);
        if (over){
            System.out.println("redis标记处over");
            return Result.error(CodeMsg.STOCK_ERROR);
        }
        //减库存
        long stock=redisService.decr(SECK_KEY+goodsId);
        System.out.println("剩余："+stock);
        if (stock<0){
            localOverMap.put(goodsId,true);
            System.out.println("stock<0");
            return Result.error(CodeMsg.STOCK_ERROR);
        }
        //判断是否已经秒杀到了
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            System.out.println("订单存在");
            return Result.success("订单已存在");
        }
        //发送队列
        SeckMessageVo vo=new SeckMessageVo();
        vo.setUser(user);
        vo.setGoodsId(goodsId);
        String amessage=redisService.beanToString(vo);
        System.out.println("（seckillcontroller）发送到queue中需要消费的内容："+amessage);
        amqpTemplate.convertAndSend("seckill_exchange", "seckill_key",amessage);
        return Result.success("秒杀成功");
    }



}
