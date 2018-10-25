package com.shop.service.imp;

import com.shop.dao.GoodsDao;
import com.shop.entity.Goods;
import com.shop.entity.SeckillGoods;
import com.shop.entity.vo.GoodsVo;
import com.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> listGoods() {
        return goodsDao.listGoods();
    }

    @Override
    public Goods getGoodsByGid(long goodsId) {
        SeckillGoods seckillGoods=goodsDao.getSeckillGoodsByGid(goodsId);
        Goods goods=goodsDao.getGoodsByGid(goodsId);
        goods.setSeckillGoods(seckillGoods);
        return goods;
    }

    @Override
    public Integer getSeckillStock(long goodsId) {
        return goodsDao.getSeckillStock(goodsId);
    }



    @Override
    public List<GoodsVo> getGoodsVo() {
        List<SeckillGoods> slist=goodsDao.getSeckillGoodsList();
        List<GoodsVo> list=new ArrayList<>();
        for (SeckillGoods s:slist){
            Goods goods=goodsDao.getGoodsByGid(s.getGoodsId());
            GoodsVo vo=new GoodsVo();
            vo.setGoods(goods);
            vo.setSeckillGoods(s);

            list.add(vo);
        }
        return list;
    }

    @Override
    public boolean reduceStock(GoodsVo goods) {
        goodsDao.reduceStock(goods.getGoods().getId());
        int ret=goodsDao.getSeckillStock(goods.getGoods().getId());
        return ret>0;
    }

    @Override
    public void resetStock(List<GoodsVo> goodsList) {

    }

    @Override
    public GoodsVo getGoodsVobyId(long goodsId) {
        GoodsVo vo=new GoodsVo();
        Goods goods=goodsDao.getGoodsByGid(goodsId);
        SeckillGoods sgoods=goodsDao.getSeckillGoodsByGid(goodsId);
        vo.setSeckillGoods(sgoods);
        vo.setGoods(goods);
        return vo;
    }
}
