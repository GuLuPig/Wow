package com.shop.service;

import com.shop.entity.OrderInfo;
import com.shop.entity.SeckillUser;

public interface SeckillService {
    OrderInfo seckill(SeckillUser user, long goodsId);

}
