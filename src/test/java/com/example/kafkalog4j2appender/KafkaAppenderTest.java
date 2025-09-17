package com.example.kafkalog4j2appender;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KafkaAppenderTest {

    @Test
    void shouldSendMessageToKafka() {
        MockProducer<String, String> mockProducer = new MockProducer<>(true, new StringSerializer(), new StringSerializer());
        KafkaAppender appender = new KafkaAppender("KafkaAppender", null, null, true, mockProducer, "logs");

        LogEvent event = Log4jLogEvent.newBuilder()
                .setLoggerName("TestLogger")
                .setLevel(Level.INFO)
                .setMessage(new SimpleMessage("hello test"))
                .build();

        appender.append(event);

        assertThat(mockProducer.history()).hasSize(1);
        assertThat(mockProducer.history().get(0).value()).isEqualTo("hello test");
    }
}