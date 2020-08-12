package com.example.jobtracker.service;

import com.example.jobtracker.entity.Job;
import com.example.jobtracker.repository.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JobServiceImpl implements  JobService {

    Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    @Autowired
    private JobRepository jobRepository;


    @Override
    public List<Job> listJobs() {

        return (List<Job>) jobRepository.findAll();
    }

    @Override
    public Job addJob(Job job) {
        logger.info("Add new job {}",job);
        jobRepository.save(job);
        return job;
    }


}
