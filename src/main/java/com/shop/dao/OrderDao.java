package com.shop.dao;

import com.shop.entity.OrderInfo;
import com.shop.entity.SeckillOrder;

public interface OrderDao {


    void insertOrder(OrderInfo orderInfo);

    void insertSeckillOrder(SeckillOrder seckillOrder);

    void testTransactional(OrderInfo orderInfo);
}
