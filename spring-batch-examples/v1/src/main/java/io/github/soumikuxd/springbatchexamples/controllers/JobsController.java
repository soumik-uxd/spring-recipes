package io.github.soumikuxd.springbatchexamples.controllers;

import io.github.soumikuxd.springbatchexamples.exceptions.ResourceNotFoundException;
import io.github.soumikuxd.springbatchexamples.services.JobHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobsController {
    private static final Logger logger = LoggerFactory.getLogger(JobsController.class);
    @Autowired
    private JobHandlerService jobHandlerService;

    @PostMapping("/job/{jobName}")
    @ResponseStatus(code = HttpStatus.OK)
    public void submit(@PathVariable(value = "jobName") String jobName) {
        try {
            this.jobHandlerService.submit(jobName);
        } catch (ResourceNotFoundException ignored) {

        }
    }
}
