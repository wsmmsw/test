package com.baicheng.fork.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baicheng.fork.core.util.BeanUtils;
import com.baicheng.fork.domain.util.SMSChannel;
import com.baicheng.fork.domain.util.SMSChannelExample;
import com.baicheng.fork.web.dao.SMSChannelMapper;
import com.baicheng.fork.web.service.SMSChannelService;

/**
 * @author SongPengpeng
 * @date 2017/8/1.
 */
@Service
public class SMSChannelServiceImpl implements SMSChannelService {

	@Resource
	private SMSChannelMapper smsChannelMapper;

	@Override
	public List<SMSChannel> getList(long offset, int count) {
		return smsChannelMapper.selectList(offset, count);
	}

	@Override
	@Transactional
	public void update(long channelId) {
		smsChannelMapper.updateChannel(-1, false);
		smsChannelMapper.updateChannel(channelId, true);
	}

	@Override
	public SMSChannel getCurrentChannel() {
		SMSChannelExample example = new SMSChannelExample();
		example.or().andIsEnabledEqualTo(true);
		List<SMSChannel> list = smsChannelMapper.selectByExample(example);
		return list == null || list.isEmpty() ? null : list.get(0);
	}

	/**
	 * @return
	 */
	public static SMSChannel currentChannel() {
		SMSChannelService service = BeanUtils.getInstance().getService(SMSChannelService.class);
		if (service != null) {
			return service.getCurrentChannel();
		}
		return null;
	}

}
