package com.baicheng.fork.web.joint.linktour.consumer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

/**
 * @author SongPengpeng
 * @date 2018/4/24.
 */
public class ProdQueueConsumer implements ChannelAwareMessageListener {

	private static final Logger LOGGER = Logger.getLogger(ProdQueueConsumer.class);

	@Override
	public void onMessage(Message message, Channel channel) {
		try {
			String msg = new String(message.getBody());
			System.out.println(" [X] Subscribe: " + new String(message.getBody()));
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
