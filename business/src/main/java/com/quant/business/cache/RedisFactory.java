package com.quant.business.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("unused")
public class RedisFactory {

	private static Logger logger = LoggerFactory.getLogger(RedisFactory.class);

	private static final String ip = "127.0.0.1";
	private static final int port = 6379;
	private static final int maxAct = 100;
	private static final int maxIdle = 55;
	private static final int maxWaitMillis = 15000;
	private static final int timeOut = 2000;
	private static JedisPool mastJedisPool;
	
	static {
		JedisPoolConfig jedisConfig = new JedisPoolConfig();
		jedisConfig.setMaxActive(maxAct);
		jedisConfig.setMaxIdle(maxIdle);
		jedisConfig.setMaxWait(maxWaitMillis);
		mastJedisPool = new JedisPool(jedisConfig, ip, port);
	}

	public static Jedis getJedis() {
		try {
			return mastJedisPool.getResource();
		} catch (Exception e) {
			logger.error("获取master-cache连接失败Ip:" + ip + ";prot:" + port
					+ "，原因：" + e.getMessage(), e);
		}
		return null;
	}

	public static void setJedis(Jedis jedis) {
		try{
			mastJedisPool.returnResource(jedis);
		}catch(Exception ex){
			mastJedisPool.returnBrokenResource(jedis);
		}
	}

	public static void main(String[] args) {
		System.out.println(ip);
	}
}
