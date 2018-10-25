package com.shop.service;

import com.shop.entity.SeckillUser;

public interface UserService {
    SeckillUser login(String name, String pass);

    void testaddUser(Integer num);
}
