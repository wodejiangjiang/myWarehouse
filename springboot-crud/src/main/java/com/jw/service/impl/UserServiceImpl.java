package com.jw.service.impl;


import com.jw.bean.User;
import com.jw.dao.UserDao;
import com.jw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

   @Autowired
   private RedisTemplate<String, User> redisTemplate;




    @Override
    @Cacheable(value = "userlist")
    public Iterable<User> findAll() {
       /* //提供操作redis的方法
        ListOperations<String, User>  listOperations = redisTemplate.opsForList();
        List<User> userList = new ArrayList<>();
        //从redis缓存中去获取数据使用range方法取，key ，0,-1表示取集合里的全部数据
        userList = listOperations.range("Userlist",0,-1);
         //如果缓存中不存在数据，则去数据库中查询
        if(userList!=null && userList.isEmpty()){
            userList  = (List<User>) userDao.findAll();
            //然后把数据存放到redis缓存中
            listOperations.leftPushAll("Userlist",userList);*/

        return userDao.findAll();
                }
    @Transactional
    @Override
    @Caching(
            evict  = {
                    @CacheEvict(value = "userlist",allEntries=true),
                    @CacheEvict(value = "user",allEntries=true)
            }

    )
    public void save(User user) {
        userDao.save(user);
    }
    @Transactional
    @Override
    @Caching(
            evict  = {
                    @CacheEvict(value = "userlist",allEntries=true),
                    @CacheEvict(value = "user",allEntries=true)
            }

    )
    public void update(User user) {
        userDao.save(user);
    }
    @Transactional
    @Override
    // 清空userlist 缓存
    @Caching(
            evict  = {
                    @CacheEvict(value = "userlist",allEntries=true),
                    @CacheEvict(value = "user",allEntries=true)


            }

    )
    public void deleteById(Long id) {
        userDao.deleteById(id);

    }

    @Override
    //使用redis注解的方式来实现redis缓存
    @Cacheable(value = "user")
    public User findById(Long id) {
      /*  //提供操作redis的方法
        ValueOperations<String,User> valueOperations = redisTemplate.opsForValue();
        //从redis缓存中去获取数据
        User user  = valueOperations.get("User"+id);
      //如果缓存中不存在数据，则去数据库中查询
        if(user==null) {
            user = userDao.findById(id).get();
            //然后把数据存放到redis缓存中
            valueOperations.set("User" + id, user);
        }*/
        return userDao.findById(id).get();

    }
}
