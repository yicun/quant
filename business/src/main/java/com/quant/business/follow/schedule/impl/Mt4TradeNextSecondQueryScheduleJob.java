package com.quant.business.follow.schedule.impl;

import java.util.Date;

import com.quant.business.follow.schedule.ScheduleJob;

public class Mt4TradeNextSecondQueryScheduleJob implements ScheduleJob {

    public void doScheduleJob() {
        System.out.println("A" + new Date());

    }
}
