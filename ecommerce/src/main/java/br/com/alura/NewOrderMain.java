package br.com.alura;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class NewOrderMain {

	public static void main(String... args) throws InterruptedException, ExecutionException {
		
		KafkaProducer<String, String> producer = new KafkaProducer<>(properties());
		
		Callback callback = (data, ex) -> {
			if(ex != null) {
				ex.printStackTrace();
				return;
			}
			
			log.info("Sucesso enviando: " + data.topic() + " ::: " + data.partition() 
				+ " / " + data.offset() + " / " + data.timestamp());
			
		};
		
		String value = "2323,3232,13121";
		ProducerRecord<String, String> record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value , value);
		
		String email = "Thank you for your order! We are processing your order!";
		ProducerRecord<String, String> emailRecord = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", email , email);
		
		producer.send(record, callback).get();
		producer.send(emailRecord, callback).get();
	}

	private static Properties properties() {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		return properties;
	}
}
