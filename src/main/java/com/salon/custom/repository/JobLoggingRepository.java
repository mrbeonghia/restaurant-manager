package com.salon.custom.repository;

import com.salon.custom.entities.JobLogging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobLoggingRepository extends JpaRepository<JobLogging, Long> {
    JobLogging findByJobName(String jobName);

}
