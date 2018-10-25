package com.shop.service;

import com.shop.entity.OrderInfo;
import com.shop.entity.SeckillOrder;
import com.shop.entity.SeckillUser;
import com.shop.entity.vo.GoodsVo;

public interface OrderService {
    OrderInfo createOrder(SeckillUser user, GoodsVo goodsVo);



    SeckillOrder getSeckillOrderByUserIdGoodsId(Long id, long goodsId);

    void testTranscational();


}
