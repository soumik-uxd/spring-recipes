package io.github.soumikuxd.springbatchexamples.writers;

import io.github.soumikuxd.springbatchexamples.models.Employee;
import io.github.soumikuxd.springbatchexamples.services.TopicWriter;
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
