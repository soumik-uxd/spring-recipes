package io.github.soumikuxd.springbatchexamples.services;

import io.github.soumikuxd.springbatchexamples.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TopicWriter {
    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    @Value("${app.kafka.producer.topic}")
    private String topic;

    public void write(Employee employee) {
        kafkaTemplate.send(topic, employee);
    }
}
