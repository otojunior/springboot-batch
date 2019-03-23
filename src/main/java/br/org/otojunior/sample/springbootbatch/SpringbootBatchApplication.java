package br.org.otojunior.sample.springbootbatch;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootBatchApplication implements CommandLineRunner {
	@Autowired
	public JobOperator jobOperator;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jobOperator.startNextInstance("sampleJob");
		jobOperator.startNextInstance("sampleJob2");
	}
}
