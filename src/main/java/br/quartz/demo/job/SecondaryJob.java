package br.quartz.demo.job;

import br.quartz.demo.service.SecondaryService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class SecondaryJob implements Job {

    @Autowired
    private SecondaryService service;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        service.hello();
    }
}