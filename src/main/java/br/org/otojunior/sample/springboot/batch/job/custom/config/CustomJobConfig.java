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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
public class CustomJobConfig {
	public static final String CUSTOM_JOB_NAME = "customJob";
	public static final String CUSTOM_STEPUM_NAME = "customStepUm";
	
	@Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    
	/**
     * 
     * @param listener
     * @param step1
     * @return
     */
    @Bean
    public Job customJob(JobExecutionListener listener, Step step) {
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
	@Bean
	public Step customStepUm(StepExecutionListener listener,
			ItemReader<String> reader,
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
}
