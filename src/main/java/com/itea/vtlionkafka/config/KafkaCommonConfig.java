package com.itea.vtlionkafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Configuration
@Data
@EnableKafka
public class KafkaCommonConfig {
	@Value("${spring.kafka.bootstrap.servers}")
	private String servers;
	@Value("${spring.kafka.group.id}")
	private String groupId;
	@Value("${spring.kafka.group.unique.prefix}")
	private String uniqueGroupPrefix;
	@Autowired
	private ObjectMapper mapper;

	@Bean
	public Map<String, Object> consumerConfig() {
		final Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return config;
	}
}
