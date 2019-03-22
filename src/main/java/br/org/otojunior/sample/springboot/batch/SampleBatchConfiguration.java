package br.org.otojunior.sample.springboot.batch;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class SampleBatchConfiguration extends DefaultBatchConfigurer {
	@Autowired private JobRegistry jobRegistry;
	@Autowired private JobLauncher jobLauncher;
	@Autowired private JobRepository jobRepository;
	@Autowired private JobExplorer jobExplorer;
    @Autowired private ApplicationContext applicationContext;
    
    /**
     * 
     * @return
     * @throws Exception
     */
    @Bean
	public JobRegistryBeanPostProcessor jobRegistrar() throws Exception {
		JobRegistryBeanPostProcessor registrar = new JobRegistryBeanPostProcessor();
		registrar.setJobRegistry(this.jobRegistry);
		registrar.setBeanFactory(this.applicationContext.getAutowireCapableBeanFactory());
		registrar.afterPropertiesSet();
		return registrar;
	}

    /**
     * 
     * @return
     * @throws Exception
     */
	@Bean
	public JobOperator jobOperator() throws Exception {
		SimpleJobOperator simpleJobOperator = new SimpleJobOperator();
		simpleJobOperator.setJobLauncher(this.jobLauncher);
		simpleJobOperator.setJobParametersConverter(new DefaultJobParametersConverter());
		simpleJobOperator.setJobRepository(this.jobRepository);
		simpleJobOperator.setJobExplorer(this.jobExplorer);
		simpleJobOperator.setJobRegistry(this.jobRegistry);
		simpleJobOperator.afterPropertiesSet();
		return simpleJobOperator;
	}
    
	/**
	 * {@inheritDoc}
	 */
	@Bean
	public JobLauncher getJobLauncher() {
		SimpleJobLauncher jobLauncher = null;
		try {
			TaskExecutor taskExecutor = new SyncTaskExecutor();
			//TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
			jobLauncher = new SimpleJobLauncher();
			jobLauncher.setJobRepository(this.jobRepository);
			jobLauncher.setTaskExecutor(taskExecutor);
			jobLauncher.afterPropertiesSet();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jobLauncher;
	}
}