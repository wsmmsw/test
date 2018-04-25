package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.fork.domain.util.SMSChannel;

/**
 * Created by SongPengpeng on 2017/8/1.
 */
public interface SMSChannelService extends CRMService {

	/**
	 * @param offset
	 * @param count
	 * @return
	 */
	List<SMSChannel> getList(long offset, int count);

	/**
	 * @param channelId
	 */
	void update(long channelId);

	/**
	 * @return
	 */
	SMSChannel getCurrentChannel();

}
