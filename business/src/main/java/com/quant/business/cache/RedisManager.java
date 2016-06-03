package com.quant.business.cache;

import redis.clients.jedis.Jedis;

public class RedisManager {
	
	private static RedisManager redisCacheManager;

	public static RedisManager getInstance() {
		if (redisCacheManager == null) {
			synchronized (Object.class) {
				if (redisCacheManager == null) {
					redisCacheManager = new RedisManager();
				}
			}
		}
		return redisCacheManager;
	}

	public String get(String key) {
		Jedis jedis = RedisFactory.getJedis();
		if (jedis == null) {
			return null;
		}
		String value = jedis.get(key);
		RedisFactory.setJedis(jedis);
		return value;
	}

	public void set(String key, String objStr, Integer timeOut) {
		Jedis jedis = RedisFactory.getJedis();
		if (jedis == null) {
			return;
		}

		if (timeOut > 0) {
			jedis.setex(key, timeOut, objStr);
		} else {
			jedis.set(key, objStr);
		}
		RedisFactory.setJedis(jedis);
	}

	public void set(String key, String objStr) {
		set(key, objStr, 0);
	}
	
	public void remove(String cacheKey) {
		Jedis jedis = RedisFactory.getJedis();
		if (jedis == null)
			return;

		jedis.del(cacheKey);
		RedisFactory.setJedis(jedis);
	}
	
	public void expirekey(String key, Integer timeOut){
		Jedis jedis = RedisFactory.getJedis();
		byte[] keyArr = key.getBytes();
		jedis.expire(keyArr, timeOut);
	}
	
	public boolean exits(String key){
		Jedis jedis = RedisFactory.getJedis();
		return jedis.exists(key);
	}
	
	public boolean exitsKey(String key){
		String result = get(key);
		if(result == null || "".equals(result)){
			return false;
		}
		return true;
	}
	
	
}
