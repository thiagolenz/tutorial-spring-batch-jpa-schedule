package org.exemplo.batchdemo.pessoa.job.config;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class PessoaBatchConfigSchedule {

    @Autowired
    private PessoaBatchConfigService configService;

    @Autowired
    private JobLauncher jobLauncher;

    @Scheduled(fixedDelay = 30000, initialDelay = 30000)
    public void startJob () throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        jobLauncher.run(configService.criarJob(), new JobParameters());
    }
}
