/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.job.custom.config;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
@ConditionalOnProperty(
	prefix="br.org.otojunior.sample.springboot.batch.schedule",
	name=CustomJobConfig.CUSTOM_JOB_NAME)
public class CustomJobLauncher {
	@Autowired
	public JobOperator jobOperator;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay=1_000) // 10 segundos.
	public void runJob() throws Exception {
		this.jobOperator.startNextInstance(CustomJobConfig.CUSTOM_JOB_NAME);
	}
}
