package com.shop.service.imp;

import com.shop.dao.UserDao;
import com.shop.entity.SeckillUser;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public SeckillUser login(String name, String pass) {
        return userDao.login(name,pass);
    }

    @Override
    public void testaddUser(Integer num) {
        SeckillUser user=new SeckillUser();
        for (int i=0;i<num;i++) {
            user.setNickname("gulu"+i);
            user.setPassword("123456");

            userDao.insertUser(user);
            System.out.println("新建用户 "+i+" : "+user.getNickname()+"----密码： "+user);
        }
    }
}
