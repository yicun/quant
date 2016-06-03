package com.quant.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quant.dal.dao.TradeInfoDAO;
import com.quant.dal.dataobject.TradeInfoDO;
import com.quant.web.response.QuantResponse;

@Controller
public class TradeInfoController {

    @Autowired
    private TradeInfoDAO tradeInfoDAO;

    @RequestMapping(value = "/trade/{tradeNo}", method = RequestMethod.GET)
    public @ResponseBody QuantResponse query(ModelMap modelMap, @PathVariable String tradeNo) {

        TradeInfoDO tradeInfoDO = tradeInfoDAO.queryTradeById(Integer.valueOf(tradeNo));
        modelMap.addAttribute("id", tradeInfoDO.getId());
        System.out.print(tradeInfoDO.getId());
        QuantResponse quantResponse = new QuantResponse();
        quantResponse.setResult(tradeInfoDO);
        return quantResponse;
    }
}
