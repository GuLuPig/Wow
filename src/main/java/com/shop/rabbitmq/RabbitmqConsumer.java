package com.shop.rabbitmq;

import com.shop.entity.SeckillOrder;
import com.shop.entity.SeckillUser;
import com.shop.entity.vo.SeckMessageVo;
import com.shop.redis.RedisService;
import com.shop.service.GoodsService;
import com.shop.service.OrderService;
import com.shop.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class RabbitmqConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(RabbitmqConsumer.class);

    @Autowired
    GoodsService goodsService;
    @Autowired
    SeckillService seckillService;
    @Autowired
    OrderService orderService;
    @Autowired
    RedisService redisService;

    @Override
    public void onMessage(Message message) {

        String remsg=new String(message.getBody());
        System.out.println("消息监听收到的订单详情:"+remsg);
        //处理订单
        SeckMessageVo vo=redisService.stringToBean(remsg,SeckMessageVo.class);
        //查询一下库存情况  判断并处理
        int stock=goodsService.getSeckillStock(vo.getGoodsId());
        if (stock<=0){
            System.out.println("查询后台的stock:"+stock);
            return;
        }
        //减库存 下订单 写入订单
        seckillService.seckill(vo.getUser(),vo.getGoodsId());

    }


}
