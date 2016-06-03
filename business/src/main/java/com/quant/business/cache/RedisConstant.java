package com.quant.business.cache;

/**
 * redis 失效时间 单位 秒
 */
public final class RedisConstant {
	
	//token 生效 1 小时
	public static final Integer TOKEN_TIME = 3600;
	
	//token redis key 前缀
	public static final String TOKEN_KEYPRE = "token_";
	
}
