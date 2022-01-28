package com.course.kafka.consumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.FixedBackOff;

import com.course.kafka.consumer.entity.CarLocation;
import com.course.kafka.consumer.error.handler.GlobalErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class KafkaConfig {

	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean
	public ConsumerFactory<Object, Object> consumerFactory() {
		var properties = kafkaProperties.buildConsumerProperties();

		properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

		return new DefaultKafkaConsumerFactory<>(properties);
	}

	@Bean(name = "farLocationContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactory());

		factory.setRecordFilterStrategy(obj -> {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				var carlocation = objectMapper.readValue(obj.value().toString(), CarLocation.class);
				if (carlocation.getDistance() <= 100)
					return true;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		});

		return factory;

	}
	
	
	@Bean(value = "kafkaListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactory());
		factory.setErrorHandler(new GlobalErrorHandler());
		return factory;
	}
	
	private RetryTemplate createRetryTemplate() {
		var retryTemplate = new RetryTemplate();
		var retryPolicy = new SimpleRetryPolicy(3);
		retryTemplate.setRetryPolicy(retryPolicy);
		var backoffpolicy = new FixedBackOffPolicy();
		backoffpolicy.setBackOffPeriod(10_000);
		retryTemplate.setBackOffPolicy(backoffpolicy);
		return retryTemplate;
	}
	
	@Bean(value = "imageRetryContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactory());
		factory.setErrorHandler(new GlobalErrorHandler());
		factory.setRetryTemplate(createRetryTemplate());
		return factory;
	}
	
	@Bean(value = "invoiceDltContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltContainerFactory(
			ConcurrentKafkaListenerContainerFactoryConfigurer configurer, KafkaOperations<Object, Object> kafkaTemplate) {
		var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
		configurer.configure(factory, consumerFactory());
		
		var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
		        (record, ex) -> new TopicPartition("t_invoice_dlt", record.partition()));
		
		var errorHandler = new SeekToCurrentErrorHandler(recoverer, new FixedBackOff(10_000, 5));
		
		factory.setErrorHandler(errorHandler);
		
		return factory;
	}

}
