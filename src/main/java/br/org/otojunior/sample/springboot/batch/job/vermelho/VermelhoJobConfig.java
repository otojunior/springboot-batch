/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.job.vermelho;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
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
public class VermelhoJobConfig {
	public static final String VERMELHO_JOB_NAME = "vermelhoJob";
	public static final String VERMELHO_STEPUM_NAME = "vermelhoStepUm";
	
	@Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private StepBuilderFactory stepBuilderFactory;
    
	/**
     * 
     * @param listener
     * @param step1
     * @return
     */
    @Bean
    public Job vermelhoJob(
    		@Qualifier("vermelhoJobListener") JobExecutionListener listener,
    		@Qualifier("vermelhoStepUm") Step step) {
        return jobBuilderFactory.get(VERMELHO_JOB_NAME).
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
	@Bean("vermelhoStepUm")
	public Step vermelhoStepUm(
			@Qualifier("vermelhoReader") ItemReader<String> reader,
			ItemProcessor<String, String> processor,
			ItemWriter<String> writer) {
		return stepBuilderFactory.get(VERMELHO_STEPUM_NAME).
			<String, String>chunk(5).
			reader(reader).
			processor(processor).
			writer(writer).
			build();
	}
}
