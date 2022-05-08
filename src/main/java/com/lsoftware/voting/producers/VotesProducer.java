/*
 * Developed by: Luis Espinosa, be aware that this project
 * is part of my personal portfolio.
 */
package com.lsoftware.voting.producers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.lsoftware.voting.model.kafka.VoteKafka;


/**
 * The Class VotesProducer.
 * 
 * @author Luis Espinosa
 */
@Component
public final class VotesProducer {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(VotesProducer.class);
	
	/** The Constant VOTES_TOPIC. */
	private static final String VOTES_TOPIC = "votes";

	/** The kafka template. */
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	/**
	 * Instantiates a new votes producer.
	 *
	 * @param kafkaTemplate the kafka template
	 */
	public VotesProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}


	/**
	 * Send message.
	 *
	 * @param message the message
	 */
	public void sendMessage(VoteKafka vote) {
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(VOTES_TOPIC, vote);

		// This will check producer result asynchronously to avoid thread blocking
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			@Override
			public void onFailure(Throwable throwable) {
				logger.error("Failed to send message", throwable);
			}

			@Override
			public void onSuccess(SendResult<String, Object> stringStringSendResult) {
				logger.info("Sent message successfully");
			}
		});
	}

}
