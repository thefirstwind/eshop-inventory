package com.thefirstwind.eshop.inventory.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.thefirstwind.eshop.inventory.dao.RedisDao;
import com.thefirstwind.eshop.inventory.mapper.UserMapper;
import com.thefirstwind.eshop.inventory.model.User;
import com.thefirstwind.eshop.inventory.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private RedisDao redisDao;

	@Override
	public User findUserInfo() {
		return userMapper.findUserInfo();
	}

	@Override
	public User getCachedUserInfo() {

		redisDao.set("cached_user_lisi", "{\"name\":\"lisi\", \"age\":28 }");

		String userJson = redisDao.get("cached_user_lisi");
		JSONObject userJsonObject = JSONObject.parseObject(userJson);

		User user = new User();
		user.setName(userJsonObject.getString("name"));
		user.setAge(userJsonObject.getInteger("age"));

		return user;
	}

}
