package com.example.kafkalog4j2appender;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.util.Properties;

@Plugin(name = "KafkaAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class KafkaAppender extends AbstractAppender {

    private final Producer<String, String> producer;
    private final String topic;

    protected  KafkaAppender(String name, Filter filter, Layout<? extends Serializable> layout,
                             boolean ignoreExceptions, Producer<String, String> producer, String topic) {
        super(name, filter, layout, ignoreExceptions);
        this.producer = producer;
        this.topic = topic;
    }

    @PluginFactory
    public static KafkaAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginAttribute("topic") String topic,
            @PluginAttribute("bootstrapServers") String bootstrapServers,
            @PluginAttribute("Layout") Layout<? extends Serializable> layout,
            @PluginAttribute("Filter") Filter filter){
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new KafkaAppender(name, filter, layout, true, producer, topic);
    }

    @Override
    public void append(LogEvent event) {
        try {
            String message = event.getMessage().getFormattedMessage();
            producer.send(new ProducerRecord<>(topic, message));
        } catch (Exception e) {
            if (!ignoreExceptions()) {
                throw new AppenderLoggingException("Error sending log message to Kafka", e);
            }
        }
    }

    @Override
    public void stop() {
        super.stop();
        producer.close();
    }
}
