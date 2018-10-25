package com.shop.service.imp;

import com.shop.dao.OrderDao;
import com.shop.entity.OrderInfo;
import com.shop.entity.SeckillOrder;
import com.shop.entity.SeckillUser;
import com.shop.entity.vo.GoodsVo;
import com.shop.redis.RedisService;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class OrderServiceImp implements OrderService {
    private final String ORDER_KEY="orderkey";

    @Autowired
    RedisService redisService;

    @Autowired
    OrderDao orderDao;

    @Override
    public SeckillOrder getSeckillOrderByUserIdGoodsId(Long id, long goodsId) {
        //查询redis是否有该秒杀订单
        return redisService.get(ORDER_KEY+goodsId+"_"+id, SeckillOrder.class);
    }

    @Override
    @Transactional
    public void testTranscational() {
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setId(123456);
        orderInfo.setUserId(123456);
        orderDao.testTransactional(orderInfo);

        int i=1/0;

    }

    @Override
    @Transactional
    public OrderInfo  createOrder(SeckillUser user, GoodsVo goodsVo) {
        OrderInfo orderInfo=new OrderInfo();

        orderInfo.setId(new Date().getTime()+user.getId()+goodsVo.getGoods().getId());
        orderInfo.setGoodsId(goodsVo.getSeckillGoods().getGoodsId());
        orderInfo.setGoodsName(goodsVo.getGoods().getGoodsName());
        orderInfo.setUserId(user.getId());
        orderInfo.setGoodsPrice(goodsVo.getSeckillGoods().getSeckillPrice());
        SeckillOrder seckillOrder=new SeckillOrder();
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setGoodsId(goodsVo.getSeckillGoods().getGoodsId());
        seckillOrder.setUserId(user.getId());
        orderDao.insertSeckillOrder(seckillOrder);//写入
        orderDao.insertOrder(orderInfo);//写入

        //如果秒杀写入了则将秒杀信息写入redis，告诉其他地方已经有单子了
        redisService.set(ORDER_KEY+goodsVo.getGoods().getId()+"_"+user.getId(),seckillOrder);
        System.out.println("已生成订单："+orderInfo.getId());
        return orderInfo;
    }




}
