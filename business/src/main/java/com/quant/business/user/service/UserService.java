package com.quant.business.user.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.quant.business.cache.RedisManager;
import com.quant.business.cache.RedisConstant;
import com.quant.common.QIResponse;
import com.quant.common.returncode.CommonCode;
import com.quant.common.util.CodecUtil;
import com.quant.dal.dao.IMTUserDao;
import com.quant.dal.dataobject.MTUser;

@Service
public class UserService {
	
	@Autowired
	private IMTUserDao userDao;
	
	/**
	 * 用户登录
	 * @param user 
	 * 必选：email passwd 
	 * 可选：uid
	 * @return
	 */
	public QIResponse login(MTUser user){
		QIResponse res = new QIResponse();
		user.setPasswd(CodecUtil.encryptMD5(user.getPasswd()));
		
		//如果前端传值uid 则判断token是否失效  减少查询数据操作
		/*
		if(null != user.getUid()){
			String token = CodecUtil.encryptMD5(user.getUid() + user.getEmail());
			String redisKey = RedisConstant.TOKEN_KEYPRE + user.getUid();
			if(RedisManager.getInstance().exitsKey(redisKey)){
				
			}
		}
		*/
		
		//查询用户
		//MTUser result = userDao.selectByEmailPwd(user);
		MTUser result = userDao.selectByNamePwd(user);
		if(null == result){
			res.getMeta().setCode(CommonCode.USER_NOEXIST);
			res.getMeta().setSuccess(CommonCode.FALSE);
			res.getMeta().setMessage("用户不存在或用户名密码不正确！");
			return res;
		}
		
		//加密 uid+email作为token存储redis 1小时
		String token = CodecUtil.encryptMD5(result.getUid() + result.getEmail());
		String redisKey = RedisConstant.TOKEN_KEYPRE + result.getUid();
		RedisManager.getInstance().set(redisKey, token, RedisConstant.TOKEN_TIME);
		
		res.getMeta().setCode(CommonCode.OK);
		res.getMeta().setSuccess(CommonCode.TRUE);
		res.getMeta().setMessage("login success!");
		
		JSONObject obj = new JSONObject();
		obj.put("token", token);
		res.setData(obj);
		return res;
	}
	
	/**
	 * 用户注册
	 * @param user 
	 * 必选 email passwd tel
	 * @return
	 */
	public QIResponse register(MTUser user) {
		QIResponse res = new QIResponse();
		
		//字段不为空的先默认值
		user.setEmail("");
		user.setBankAccount("");
		user.setBankInfo("");
		user.setBankName("");
		user.setHeadimage("");
		user.setIdcard("");
		user.setMoney(BigDecimal.valueOf(0L));
		user.setMoneyFreeze(BigDecimal.valueOf(0L));
		user.setQq("qq");
		user.setType("A");
		user.setTel("");
		user.setRealName("");
		user.setStatus("1");
		user.setExtInfo("");
		//
		
		user.setPasswd(CodecUtil.encryptMD5(user.getPasswd()));
		
		//密码加密后插入数据
		int uid = userDao.insertSingleSelective(user);
		if(uid > 0){
			//判断插入成功后 根据主键查询
			MTUser regUser = userDao.selectById(uid);
			if(null != regUser){
				//加密 uid+email作为token存储redis 1小时
				String token = CodecUtil.encryptMD5(regUser.getUid() + regUser.getEmail());
				String redisKey = RedisConstant.TOKEN_KEYPRE + regUser.getUid();
				RedisManager.getInstance().set(redisKey, token, RedisConstant.TOKEN_TIME);
				
				res.getMeta().setCode(CommonCode.OK);
				res.getMeta().setSuccess(CommonCode.TRUE);
				res.getMeta().setMessage("register success!");
				
				JSONObject obj = new JSONObject();
				obj.put("token", token);
				res.setData(obj);
				return res;
			}
		}
		
		res.getMeta().setCode(CommonCode.UNKNOWN);
		res.getMeta().setSuccess(CommonCode.FALSE);
		res.getMeta().setMessage("未知错误，注册失败，请稍后再试！");
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(CodecUtil.encryptMD5("123456"));
		System.out.println("E10ADC3949BA59ABBE56E057F20F883E".toLowerCase());
	}

	
}
