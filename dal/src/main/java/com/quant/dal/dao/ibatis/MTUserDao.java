package com.quant.dal.dao.ibatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quant.dal.dao.IMTUserDao;
import com.quant.dal.dataobject.MTUser;

public class MTUserDao extends SqlSessionDaoSupport implements IMTUserDao{

	private static final Logger logger = LoggerFactory.getLogger(MTUserDao.class);
	
	@Override
	public int deleteById(Integer uid) {
		return 0;
	}

	@Override
	public void insert(List<MTUser> users) {
		this.getSqlSession().insert("com.quant.dal.dao.IMTUserDao.insert", users);
	}

	@Override
	public void insertSelective(List<MTUser> users) {
		this.getSqlSession().insert("com.quant.dal.dao.IMTUserDao.insertSelective", users);
	}
	
	@Override
	public int insertSingleSelective(MTUser user) {
		logger.info("insertSingleSelective:{}", user);
		return this.getSqlSession().insert("com.quant.dal.dao.IMTUserDao.insertSingleSelective", user);
	}

	@Override
	public MTUser selectById(Integer uid) {
		return (MTUser)this.getSqlSession().selectOne("com.quant.dal.dao.IMTUserDao.selectById", uid);
	}
	
	@Override
	public MTUser selectByEmailPwd(MTUser user) {
		return (MTUser) this.getSqlSession().selectOne("com.quant.dal.dao.IMTUserDao.selectByEmailPwd", user);
	}
	
	@Override
	public MTUser selectByNamePwd(MTUser user) {
		return (MTUser) this.getSqlSession().selectOne("com.quant.dal.dao.IMTUserDao.selectByNamePwd", user);
	}
	
	@Override
	public int updateByIdSelective(MTUser user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateById(MTUser user) {
		// TODO Auto-generated method stub
		return 0;
	}


}
