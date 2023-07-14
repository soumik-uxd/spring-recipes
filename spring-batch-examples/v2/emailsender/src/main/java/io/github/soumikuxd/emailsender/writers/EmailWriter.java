package io.github.soumikuxd.emailsender.writers;

import io.github.soumikuxd.emailsender.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class EmailWriter implements ItemWriter<Employee> {
    @Override
    public void write(Chunk<? extends Employee> chunk) throws Exception {
        for (Employee e: chunk) {
            log.info("Sending email to: "+ e.getEmail());
        }
    }
}