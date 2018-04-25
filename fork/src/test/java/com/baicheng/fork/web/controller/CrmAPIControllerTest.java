package com.baicheng.fork.web.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.baicheng.fork.web.BaseSpringMvcTest;

/**
 * @author SongPengpeng
 * @date 2017/12/25.
 */
public class CrmAPIControllerTest extends BaseSpringMvcTest {

	@Test
	public void testGetProdAmountStatisticsList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/list/deltaTime")
				.param("startTime", "2017-09-05 00:00:00")
				.param("endTime", "2017-09-05 23:59:59"))
				.andReturn();
	}
}
