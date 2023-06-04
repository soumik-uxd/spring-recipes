package io.github.soumikuxd.springbatchexamples.controllers;

import io.github.soumikuxd.springbatchexamples.exceptions.ResourceNotFoundException;
import io.github.soumikuxd.springbatchexamples.models.JobInfo;
import io.github.soumikuxd.springbatchexamples.services.JobHandlerService;
import io.github.soumikuxd.springbatchexamples.services.JobInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobsController {
    private static final Logger logger = LoggerFactory.getLogger(JobsController.class);
    @Autowired
    private JobInfoService jobInfoService;

    @Autowired
    private JobHandlerService jobHandlerService;

    @GetMapping("/jobs")
    public List<JobInfo> getAllJobs() {
        return jobInfoService.getAllJobs();
    }

    @PostMapping("/job/{jobName}")
    public ResponseEntity<JobInfo> submit(@PathVariable(value = "jobName") String jobName) {//@RequestBody JobExecutionParameters jobExecParameters) {
        try {
            this.jobHandlerService.submit(jobName);
        } catch (ResourceNotFoundException e) {
            //logger.error("Error submitting job: " + jobName);
        }
        return ResponseEntity.ok().body(new JobInfo(jobName));
    }
}
