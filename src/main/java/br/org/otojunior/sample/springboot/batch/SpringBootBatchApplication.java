/*
 * 
 */
package br.org.otojunior.sample.springboot.batch;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.org.otojunior.sample.springboot.batch.job.custom.config.CustomJobConfig;

/**
 * 
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@SpringBootApplication
public class SpringBootBatchApplication implements CommandLineRunner {
	@Autowired
	public JobOperator jobOperator;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		this.jobOperator.startNextInstance(CustomJobConfig.CUSTOM_JOB_NAME);
		this.jobOperator.startNextInstance(CustomJobConfig.CUSTOM_JOB_NAME2);
	}
}
