package com.baicheng.fork.web.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.baicheng.fork.domain.joint.linktour.LTTicketProduct;
import com.baicheng.fork.web.service.LTProductService;

@Component("linkTourTask")
@Lazy(false)
public class LinkTourJointTask {
	private static final Logger LOGGER = Logger.getLogger(LinkTourJointTask.class.getName());

	@Resource
	private LTProductService ltProductService;

	public void productHandler() {
		try {
			List<LTTicketProduct> ltTicketProducts = ltProductService.getTicketProductList();
			if (ltTicketProducts == null || ltTicketProducts.size() <= 0) {
				LOGGER.error("########## 从领拓获取的门票类产品为空");
			}

			/**
			 * TODO 转换成自己的产品结构
			 */

			/**
			 * TODO 入库 ，也可以以上三个方法放到一个方法里面
			 */
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}
