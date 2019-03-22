/**
 * 
 */
package br.org.otojunior.sample.springbootbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    /**
     * 
     * @param step1
     * @return
     */
    @Bean
    public Job job1(Step step1) {
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
    public Step step1(ItemReader<String> reader,
    		SampleItemProcessor processor,
    		SampleItemWriter writer) {
        return stepBuilderFactory.get("sampleStep")
            .<String, String>chunk(7)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
}
