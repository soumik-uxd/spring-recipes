package io.github.soumikuxd.agesummary.tasklets;

import io.github.soumikuxd.agesummary.models.Employee;
import io.github.soumikuxd.agesummary.repositories.EmployeeRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
public class EmployeeAgeAggregator implements Tasklet {

    private EmployeeRepository employeeRepository;

    @Override
    public RepeatStatus execute(@Nonnull StepContribution contribution, @Nonnull ChunkContext chunkContext) throws Exception {
        Map<Integer, Long> ageGroups = this.employeeRepository.findAll()
                .stream().collect(Collectors.groupingBy(Employee::getAge, Collectors.counting()));

        for (Map.Entry<Integer, Long> ageGroup: ageGroups.entrySet())
            log.info("{} employees have the age of {}", ageGroup.getValue(), ageGroup.getKey());
        return RepeatStatus.FINISHED;
    }
}