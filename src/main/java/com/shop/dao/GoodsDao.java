package com.shop.dao;

import com.shop.entity.Goods;
import com.shop.entity.SeckillGoods;

import java.util.List;

public interface GoodsDao {
    List<Goods> listGoods();
    List<SeckillGoods> getSeckillGoodsList();

    Integer getSeckillStock(long goodsId);

    Goods getGoodsByGid(long goodsId);
    SeckillGoods getSeckillGoodsByGid(long goodsId);


    void reduceStock(long id);
}
