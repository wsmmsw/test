package com.baicheng.fork.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.baicheng.fork.domain.util.EmailChannel;
import com.baicheng.fork.web.BaseSpringMvcTest;

/**
 * @author SongPengpeng
 * @date 2017/12/25.
 */
public class EmailChannelServiceTest extends BaseSpringMvcTest {

	@Resource
	private EmailChannelService emailChannelService;

	@Test
	public void testGetList() {
		List<EmailChannel> channels = emailChannelService.getList(-1, -1);
		for (EmailChannel channel : channels) {
			System.out.println(channel.getName());
		}
	}

	@Test
	@Rollback(value = false)
	public void testUpdateChannel() {
		emailChannelService.updateChannel(10);
	}
}
