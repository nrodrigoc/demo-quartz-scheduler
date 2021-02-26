package br.quartz.demo.job;

import br.quartz.demo.service.HelloService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


public class HelloJob implements Job {

    @Autowired
    private HelloService service;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        service.hello();
    }
}
