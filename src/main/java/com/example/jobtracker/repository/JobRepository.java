package com.example.jobtracker.repository;


import com.example.jobtracker.entity.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {
}

