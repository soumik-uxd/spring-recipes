package io.github.soumikuxd.springbatchexamples.tasklets;

import io.github.soumikuxd.springbatchexamples.models.Employee;
import io.github.soumikuxd.springbatchexamples.repositories.EmployeeRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EmployeeAgeAggregator implements Tasklet {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeAgeAggregator.class);

    private EmployeeRepository employeeRepository;

    @Override
    public RepeatStatus execute(@Nonnull StepContribution contribution, @Nonnull ChunkContext chunkContext) throws Exception {
        Map<Integer, Long> ageGroups = this.employeeRepository.findAll()
                .stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));

        for (Map.Entry<Integer, Long> ageGroup: ageGroups.entrySet())
            logger.info("{} employees have the age of {}", ageGroup.getValue(), ageGroup.getKey());
        return RepeatStatus.FINISHED;
    }
}
