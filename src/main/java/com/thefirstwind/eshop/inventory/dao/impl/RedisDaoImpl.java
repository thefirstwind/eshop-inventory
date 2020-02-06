package com.thefirstwind.eshop.inventory.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.thefirstwind.eshop.inventory.dao.RedisDao;

import redis.clients.jedis.JedisCluster;

@Repository
public class RedisDaoImpl implements RedisDao {

	@Resource
	private JedisCluster jedisCluster;

	@Override
	public void set(String key, String value) {
		jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

}
