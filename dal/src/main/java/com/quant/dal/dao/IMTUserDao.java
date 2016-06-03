package com.quant.dal.dao;

import java.util.List;

import com.quant.dal.dataobject.MTUser;

public interface IMTUserDao {
	
    public int deleteById(Integer uid);

    public void insert(List<MTUser> users);

    public void insertSelective(List<MTUser> users);
    
    public int insertSingleSelective(MTUser user);

    public MTUser selectById(Integer uid);
    
    public MTUser selectByEmailPwd(MTUser user);
    
    public MTUser selectByNamePwd(MTUser user);

    public int updateByIdSelective(MTUser user);
    
    public int updateById(MTUser user);
    
}