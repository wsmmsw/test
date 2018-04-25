package com.baicheng.fork.web.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.baicheng.fork.web.BaseSpringMvcTest;

/**
 * @author SongPengpeng
 * @date 2017/12/25.
 */
public class CrmPushControllerTest extends BaseSpringMvcTest {

	@Test
	public void testGetPushContentTypeList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/push/contentTypes"))
				.andReturn();
	}

}
