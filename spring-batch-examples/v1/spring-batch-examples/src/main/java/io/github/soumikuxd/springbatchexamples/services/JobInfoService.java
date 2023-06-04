package io.github.soumikuxd.springbatchexamples.services;

import io.github.soumikuxd.springbatchexamples.models.JobInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobInfoService {
    public List<JobInfo> getAllJobs() {
        List<JobInfo> jobInfos = new ArrayList<>();
        jobInfos.add(new JobInfo("csv2db"));
        jobInfos.add(new JobInfo("db2db"));
        jobInfos.add(new JobInfo("reaper"));
        return jobInfos;
    }
}
