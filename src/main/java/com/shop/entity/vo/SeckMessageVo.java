package com.shop.entity.vo;

import com.shop.entity.Goods;
import com.shop.entity.SeckillGoods;
import com.shop.entity.SeckillUser;

public class SeckMessageVo {
    private SeckillUser user;
    private long GoodsId;

    public SeckMessageVo() {
    }

    public SeckillUser getUser() {
        return user;
    }

    public void setUser(SeckillUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return GoodsId;
    }

    public void setGoodsId(long goodsId) {
        GoodsId = goodsId;
    }

    public SeckMessageVo(SeckillUser user, long goodsId) {
        this.user = user;
        GoodsId = goodsId;
    }
}
