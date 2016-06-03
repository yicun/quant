package com.quant.business.follow.schedule.impl;

import java.util.Date;

import com.quant.business.follow.schedule.ScheduleJob;

public class Mt4TradeQueryScheduleJob implements ScheduleJob {

    public void doScheduleJob() {
        System.out.println("B" + new Date());
    }
}
