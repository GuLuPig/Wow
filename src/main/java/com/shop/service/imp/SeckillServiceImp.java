package com.shop.service.imp;

import com.shop.entity.OrderInfo;
import com.shop.entity.SeckillOrder;
import com.shop.entity.SeckillUser;
import com.shop.entity.vo.GoodsVo;
import com.shop.redis.RedisService;
import com.shop.service.GoodsService;
import com.shop.service.OrderService;
import com.shop.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeckillServiceImp implements SeckillService {
    private final String SECK_KEY="seckill_";
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;

    @Override
    @Transactional
    public OrderInfo seckill(SeckillUser user, long goodsId) {
        //减库存 下订单 写入秒杀订单
        GoodsVo goodsVo=goodsService.getGoodsVobyId(goodsId);
        boolean success=goodsService.reduceStock(goodsVo);
        if (success){
            return orderService.createOrder(user,goodsVo);
        }else {
            setGoodsOver(goodsVo.getSeckillGoods().getGoodsId());
            return null;
        }
    }




    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(SECK_KEY+goodsId);
    }


    private void setGoodsOver(long id) {
        redisService.set(SECK_KEY+id,true);
    }
}
