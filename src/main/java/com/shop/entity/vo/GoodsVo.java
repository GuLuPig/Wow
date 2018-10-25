package com.shop.entity.vo;

import com.shop.entity.Goods;
import com.shop.entity.SeckillGoods;

public class GoodsVo {
    private Goods goods;
    private SeckillGoods seckillGoods;

    public GoodsVo() {
    }

    public GoodsVo(Goods goods, SeckillGoods seckillGoods) {
        this.goods = goods;
        this.seckillGoods = seckillGoods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public SeckillGoods getSeckillGoods() {
        return seckillGoods;
    }

    public void setSeckillGoods(SeckillGoods seckillGoods) {
        this.seckillGoods = seckillGoods;
    }


}
