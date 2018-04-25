package com.baicheng.fork.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baicheng.fork.domain.util.SMSChannel;
import com.baicheng.fork.domain.util.SMSChannelExample;

public interface SMSChannelMapper {

	long countByExample(SMSChannelExample example);

	int deleteByExample(SMSChannelExample example);

	int deleteByPrimaryKey(Long id);

	int insert(SMSChannel record);

	int insertSelective(SMSChannel record);

	List<SMSChannel> selectByExample(SMSChannelExample example);

	SMSChannel selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") SMSChannel record, @Param("example") SMSChannelExample example);

	int updateByExample(@Param("record") SMSChannel record, @Param("example") SMSChannelExample example);

	int updateByPrimaryKeySelective(SMSChannel record);

	int updateByPrimaryKey(SMSChannel record);

	List<SMSChannel> selectList(@Param("offset") long offset, @Param("count") int count);

	void updateChannel(@Param("id") long id, @Param("isEnabled") boolean isEnabled);

}