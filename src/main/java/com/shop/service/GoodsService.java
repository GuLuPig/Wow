package com.shop.service;

import com.shop.entity.Goods;
import com.shop.entity.vo.GoodsVo;

import java.util.List;

public interface GoodsService {
    List<Goods> listGoods();

    Goods getGoodsByGid(long goodsId);

    Integer getSeckillStock(long goodsId);

    List<GoodsVo> getGoodsVo();

    boolean reduceStock(GoodsVo goods);
    void resetStock(List<GoodsVo> goodsList);

    GoodsVo getGoodsVobyId(long goodsId);
}
