package com.example.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RabbitmqApplication {

	@Bean
	TopicExchange Exchange() {
		return new TopicExchange("exchange");
	}

	@Bean
	public Queue Message() {
		return new Queue("message");
	}

	@Bean
	public Queue queueMessages() {
		return new Queue("queue.messages");
	}

	@Bean
	Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
		return BindingBuilder.bind(queueMessages).to(exchange).with("queue.#");
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);

	}
}
