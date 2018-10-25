package com.shop.controller;

import com.shop.entity.Goods;
import com.shop.entity.SeckillUser;
import com.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/tolist")
    @ResponseBody
    public ModelAndView toList(){
        ModelAndView mv=new ModelAndView();
        List<Goods> list=goodsService.listGoods();
        mv.addObject("goodsList",list);
        mv.setView(new InternalResourceView("/WEB-INF/shop/goodslist.jsp"));
        return mv;
    }

    @RequestMapping(value = "/to_detail")
    public String toDetail(@RequestParam("id")long goodsId, Model model, SeckillUser seckillUser){
        model.addAttribute("user",seckillUser);

        Goods goods=goodsService.getGoodsByGid(goodsId);
        model.addAttribute("goods",goods);


        long startAt = goods.getSeckillGoods().getStartDate().getTime();
        long endAt = goods.getSeckillGoods().getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;

        if (now<startAt){
            seckillStatus=0;
            remainSeconds= (int) ((startAt-now)/1000);
        }else if (now>endAt){
            seckillStatus=2;
            remainSeconds=-1;
        }else {
            seckillStatus=1;
            remainSeconds=0;
        }
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "shop/goodsdetail";
    }
}
