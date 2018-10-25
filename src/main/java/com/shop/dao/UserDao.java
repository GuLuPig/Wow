package com.shop.dao;

import com.shop.entity.SeckillUser;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    SeckillUser login(@Param("arg1") String name, @Param("arg2") String pass);

    void insertUser(SeckillUser user);
}
