package com.baicheng.fork.web.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baicheng.fork.core.util.BeanUtils;
import com.baicheng.fork.domain.util.EmailChannel;
import com.baicheng.fork.web.dao.EmailChannelMapper;
import com.baicheng.fork.web.service.EmailChannelService;

/**
 * Created by SongPengpeng on 2017/5/15.
 */
@Service
public class EmailChannelServiceImpl implements EmailChannelService {

	public static final Logger LOGGER = Logger.getLogger(EmailChannelService.class);

	@Autowired
	private EmailChannelMapper emailChannelMapper;

	/**
	 * @param offset
	 * @param count
	 * @return
	 */
	@Override
	public List<EmailChannel> getList(long offset, int count) {
		return emailChannelMapper.selectList(offset, count);
	}

	/**
	 * @param channelId
	 */
	@Override
	@Transactional
	public void updateChannel(long channelId) {
		emailChannelMapper.updateChannel(-1, false);
		emailChannelMapper.updateChannel(channelId, true);
	}

	/**
	 * @return
	 */
	@Override
	public EmailChannel getCurrentChannel() {
		return emailChannelMapper.getCurrentChannel();
	}

	/**
	 * 获取当前渠道信息
	 *
	 * @return
	 */
	public static EmailChannel currentChannel() {
		EmailChannelService service = BeanUtils.getInstance().getService(EmailChannelService.class);
		if (service != null) {
			return service.getCurrentChannel();
		}
		return null;
	}
}
