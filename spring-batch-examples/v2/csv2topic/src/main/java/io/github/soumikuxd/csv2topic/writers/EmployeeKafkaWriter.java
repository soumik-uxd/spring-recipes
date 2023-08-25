package io.github.soumikuxd.csv2topic.writers;

import io.github.soumikuxd.csv2topic.models.Employee;
import io.github.soumikuxd.csv2topic.services.TopicWriter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeKafkaWriter implements ItemWriter<Employee> {
    @Autowired
    private TopicWriter topicWriter;

    @Override
    public void write(Chunk<? extends Employee> chunk) throws Exception {
        chunk.getItems().forEach(employee -> {
            topicWriter.write(employee);
        });
    }
}
