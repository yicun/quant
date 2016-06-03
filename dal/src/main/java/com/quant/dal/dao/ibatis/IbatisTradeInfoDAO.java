package com.quant.dal.dao.ibatis;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.quant.dal.dao.TradeInfoDAO;
import com.quant.dal.dataobject.TradeInfoDO;

public class IbatisTradeInfoDAO extends SqlSessionDaoSupport implements TradeInfoDAO {

    @Override
    public TradeInfoDO queryTradeById(int id) {
        return (TradeInfoDO) this.getSqlSession().selectOne(
            "com.quant.dal.dao.TradeInfoDAO.queryTradeById", id);
    }


}
