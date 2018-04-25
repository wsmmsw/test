package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.fork.domain.util.EmailChannel;
import com.baicheng.fork.domain.util.EmailChannelExample;

public interface EmailChannelMapper {

	long countByExample(EmailChannelExample example);

	int deleteByExample(EmailChannelExample example);

	int deleteByPrimaryKey(Long id);

	int insert(EmailChannel record);

	int insertSelective(EmailChannel record);

	List<EmailChannel> selectByExample(EmailChannelExample example);

	EmailChannel selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") EmailChannel record, @Param("example") EmailChannelExample example);

	int updateByExample(@Param("record") EmailChannel record, @Param("example") EmailChannelExample example);

	int updateByPrimaryKeySelective(EmailChannel record);

	int updateByPrimaryKey(EmailChannel record);

	List<EmailChannel> selectList(@Param("offset") long offset, @Param("count") int count);

	/**
	 * 更新字段isEnabled.
	 *
	 * @param id 如果id=-1，更新全部记录
	 * @param isEnabled
	 */
	void updateChannel(@Param(value = "id") long id, @Param(value = "isEnabled") boolean isEnabled);

	EmailChannel getCurrentChannel();
}