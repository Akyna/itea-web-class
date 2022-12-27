package com.itea.vtlionkafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.itea.vtlionkafka.dto.PudgeDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {

	@KafkaListener(topics = {
			"${spring.kafka.consumer.topic.quickstart.events}" }, containerFactory = "quickstartEventsListenerContainerFactory")
	public void consumePudgeDTO(final ConsumerRecord<String, PudgeDTO> message) {
		log.info("Consumed message from kafka: {}", message.value());
		final PudgeDTO dto = message.value();
		
	}

}
