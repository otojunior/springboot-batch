/**
 * 
 */
package br.org.otojunior.sample.springbootbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import br.org.otojunior.sample.springbootbatch.item.SampleItemProcessor;
import br.org.otojunior.sample.springbootbatch.item.SampleItemWriter;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
@EnableBatchProcessing
public class SampleConfigJob {
	@Autowired public JobBuilderFactory jobBuilderFactory;
    @Autowired public StepBuilderFactory stepBuilderFactory;

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
//			TaskExecutor taskExecutor = new SyncTaskExecutor();
			TaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
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
    
    /**
     * 
     * @param step1
     * @return
     */
    @Bean
    public Job job1(JobLauncher jobLauncher, Step step1) {
    	return jobBuilderFactory.get("sampleJob")
            .incrementer(new RunIdIncrementer())
            .start(step1)
            .build();
    }

    /**
     * 
     * @param writer
     * @return
     */
    @Bean
    public Step step1(@Qualifier("reader1") ItemReader<String> reader,
    		SampleItemProcessor processor,
    		SampleItemWriter writer) {
        return stepBuilderFactory.get("sampleStep")
            .<String, String>chunk(7)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
    
    /**
     * 
     * @param step1
     * @return
     */
    @Bean
    public Job job2(Step step2) {
        return jobBuilderFactory.get("sampleJob2")
            .incrementer(new RunIdIncrementer())
            .start(step2)
            .build();
    }

    /**
     * 
     * @param writer
     * @return
     */
    @Bean
    public Step step2(@Qualifier("reader2") ItemReader<String> reader,
    		SampleItemProcessor processor,
    		SampleItemWriter writer) {
        return stepBuilderFactory.get("sampleStep2")
            .<String, String>chunk(7)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
}
