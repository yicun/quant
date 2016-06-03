package com.quant.dal.dataobject;

import java.util.Date;

public class TradeInfoDO {

    private int    id;

    private int    mt4TradeId;

    private String opUid;

    private String tradeType;

    private String status;

    private Date   createTime;

    private Date   updateTime;

    private Date   deleteTime;

    private String extInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMt4TradeId() {
        return mt4TradeId;
    }

    public void setMt4TradeId(int mt4TradeId) {
        this.mt4TradeId = mt4TradeId;
    }

    public String getOpUid() {
        return opUid;
    }

    public void setOpUid(String opUid) {
        this.opUid = opUid;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}
