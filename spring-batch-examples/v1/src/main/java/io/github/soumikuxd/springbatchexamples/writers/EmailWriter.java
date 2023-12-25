package io.github.soumikuxd.springbatchexamples.writers;

import io.github.soumikuxd.springbatchexamples.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class EmailWriter implements ItemWriter<Employee> {
    private static final Logger logger = LoggerFactory.getLogger(EmailWriter.class);

    @Override
    public void write(Chunk<? extends Employee> chunk) throws Exception {
        for (Employee e: chunk) {
            logger.info("Sending email to: "+ e.getEmail());
        }
    }
}
