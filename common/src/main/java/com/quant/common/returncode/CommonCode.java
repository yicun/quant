package com.quant.common.returncode;

public class CommonCode {
	
	public static final boolean TRUE = true;
	
	public static final boolean FALSE = false;
	
	/**
	 * 公用错误码用100000累加
	 * 模块错误码用200000累加
	 */
	
	/**
	 * 正常返回
	 */
	public static final int OK = 0;
	
	/**
	 * 未知错误
	 */
	public static final int UNKNOWN = -1;
	
	/**
	 * 缺少参数
	 */
	public static final int LACK_PARAM = 100000;

	/**
	 * 用户不存在
	 */
	public static final int USER_NOEXIST = 200000;
	
	
}
