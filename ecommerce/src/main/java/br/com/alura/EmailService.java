package br.com.alura;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class EmailService implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties());
		consumer.subscribe(Collections.singletonList("ECOMMERCE_SEND_EMAIL"));
		
		while(true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
			
			if(records.isEmpty()) {
				continue;
			}
			
			log.info("Encontrei {} registros", records.count());
			
			records.forEach(record -> {
				log.info("-----------------");
				log.info("Sending email, checking for fraud");
				log.info(record.key());
				log.info(record.value());
				log.info(record.partition() + "");
				log.info(record.offset() + "");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				log.info("Order processed");
			});
		}
	}

	private static Properties properties() {
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		
		//Obrigatório Passar Grupo que vai ouvir
		//Se tiver mais de um serviço "ouvindo" o mesmo grupo, as mensagens serão distribuidas entre os serviços do grupo
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, EmailService.class.getSimpleName());
		return properties;
	}
}
