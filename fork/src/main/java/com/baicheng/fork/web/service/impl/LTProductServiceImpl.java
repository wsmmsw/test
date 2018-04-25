package com.baicheng.fork.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baicheng.fork.domain.joint.linktour.LTTicketProduct;
import com.baicheng.fork.web.joint.linktour.handler.LTTicketProductHandler;
import com.baicheng.fork.web.service.LTProductService;

@Service
public class LTProductServiceImpl implements LTProductService {

	private static final Logger LOGGER = Logger.getLogger(LTProductServiceImpl.class.getName());

	@Override
	public List<LTTicketProduct> getTicketProductList() {
		List<LTTicketProduct> ticketProducts = null;
		try {
			LTTicketProductHandler handler = new LTTicketProductHandler();
			// 调用API接口获取领拓门票数据
			ticketProducts = handler.getProductListByCategory();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return ticketProducts;
	}

	@Override
	public void incrementalUpdate(String param) {
		// TODO Auto-generated method stub

	}

}
