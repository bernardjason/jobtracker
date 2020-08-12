package com.example.jobtracker.service;


import com.example.jobtracker.entity.Job;

import java.util.List;

public interface JobService {
    List<Job> listJobs();

    Job addJob(Job job);
}
