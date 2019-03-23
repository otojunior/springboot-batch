/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.job.custom.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
public class CustomJobConfig {
	public static final String CUSTOM_JOB_NAME = "customJob";
	public static final String CUSTOM_JOB_NAME2 = "customJob2";
	public static final String CUSTOM_STEPUM_NAME = "customStepUm";
	public static final String CUSTOM_STEPUM_NAME2 = "customStepUm2";
	
	@Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    
	/**
     * 
     * @param listener
     * @param step1
     * @return
     */
    @Bean
    public Job customJob(JobExecutionListener listener, @Qualifier("step1") Step step) {
        return jobBuilderFactory.get(CUSTOM_JOB_NAME).
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
	@Bean("step1")
	public Step customStepUm(StepExecutionListener listener,
			@Qualifier("reader1") ItemReader<String> reader,
			ItemProcessor<String, String> processor,
			ItemWriter<String> writer) {
		return stepBuilderFactory.get(CUSTOM_STEPUM_NAME).
			listener(listener).
			<String, String>chunk(5).
			reader(reader).
			processor(processor).
			writer(writer).
			build();
	}
	
	/**
     * 
     * @param listener
     * @param step1
     * @return
     */
    @Bean
    public Job customJob2(JobExecutionListener listener, @Qualifier("step2") Step step) {
        return jobBuilderFactory.get(CUSTOM_JOB_NAME2).
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
	@Bean("step2")
	public Step customStepUm2(StepExecutionListener listener,
			@Qualifier("reader2") ItemReader<String> reader,
			ItemProcessor<String, String> processor,
			ItemWriter<String> writer) {
		return stepBuilderFactory.get(CUSTOM_STEPUM_NAME2).
			listener(listener).
			<String, String>chunk(5).
			reader(reader).
			processor(processor).
			writer(writer).
			build();
	}
}
