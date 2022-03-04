package com.salon.custom.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_logging")
@Getter
@Setter
public class JobLogging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name", unique = true)
    private String jobName;

    @Column(name = "last_run", nullable = true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastRun;

    @Column(name = "source_ip")
    private String sourceIp;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "status")
    private String status;

}
