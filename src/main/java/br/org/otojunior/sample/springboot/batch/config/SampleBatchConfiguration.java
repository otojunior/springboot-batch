package br.org.otojunior.sample.springboot.batch.config;

import java.io.InputStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import br.org.otojunior.sample.springboot.batch.item.CustomItemProcessor;
import br.org.otojunior.sample.springboot.batch.item.CustomItemReader;
import br.org.otojunior.sample.springboot.batch.item.CustomItemWriter;

@Configuration
@EnableBatchProcessing
public class SampleBatchConfiguration extends DefaultBatchConfigurer {
	public static final String SAMPLE_JOB_NAME = "SampleJob";
	public static final String SAMPLE_STEP_NAME = "SampleStep";
	
	@Autowired private JobRegistry jobRegistry;
	@Autowired private JobLauncher jobLauncher;
	@Autowired private JobRepository jobRepository;
	@Autowired private JobExplorer jobExplorer;
	@Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
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
	
	/*
	 * ----------------------- Job UpperCaseItemNameJob ------------------------
	 */
	
	/**
     * 
     * @param listener
     * @param step1
     * @return
     */
    @Bean
    public Job samplejob(JobExecutionListener listener, Step step) {
        return jobBuilderFactory.get(SAMPLE_JOB_NAME).
        	listener(listener).
        	incrementer(new RunIdIncrementer()).
            start(step).
            build();
    }

    /**
     * 
     * @param writer
     * @return
     */
	@Bean
	public Step samplestep(StepExecutionListener listener,
			CustomItemReader reader,
			CustomItemProcessor processor,
			CustomItemWriter writer) {
		return stepBuilderFactory.get(SAMPLE_STEP_NAME).
			listener(listener).
			<Resource, InputStream>chunk(1).
			reader(reader).
			processor(processor).
			writer(writer).
			build();
	}
}