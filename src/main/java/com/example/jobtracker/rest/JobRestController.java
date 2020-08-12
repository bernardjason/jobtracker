package com.example.jobtracker.rest;

import com.example.jobtracker.entity.Job;
import com.example.jobtracker.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobRestController {
    private JobService jobService;

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("jobs")
    public ResponseEntity<List<Job>> jobs( ) {
        List<Job> list = jobService.listJobs();

        return new ResponseEntity<List<Job>>(list, HttpStatus.OK);

    }
}
