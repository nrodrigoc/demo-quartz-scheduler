package br.quartz.demo.config;

import br.quartz.demo.component.AutowiringSpringBeanJobFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class SchedulerConfig {

    @Autowired
    private QuartzProperties quartzProperties;

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext,
                                 // injecting SpringLiquibase to ensure liquibase is already initialized and created the quartz tables:
                                 SpringLiquibase springLiquibase)
    {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean scheduler(DataSource dataSource, JobFactory jobFactory, Trigger... triggers) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();

        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        schedulerFactory.setOverwriteExistingJobs(true);
        schedulerFactory.setAutoStartup(true);
        schedulerFactory.setQuartzProperties(properties);
        schedulerFactory.setDataSource(dataSource);
        schedulerFactory.setJobFactory(jobFactory);
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);

        if (triggers.length != 0) {
            schedulerFactory.setTriggers(triggers);
        }

        return schedulerFactory;
    }
}

