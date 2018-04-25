package com.baicheng.fork.web.service;

import java.util.List;

import com.baicheng.fork.domain.util.EmailChannel;

/**
 * email通道service.
 * 
 * @author SongPengpeng
 * @date 2017/5/15.
 */
public interface EmailChannelService extends CRMService {

	/**
	 * 查询全部通道.
	 *
	 * @return
	 */
	List<EmailChannel> getList(long offset, int count);

	/**
	 * 更新通道.
	 *
	 * @param channelId
	 */
	void updateChannel(long channelId);

	/**
	 * 查询启用的通道.
	 *
	 * @return
	 */
	EmailChannel getCurrentChannel();

}
