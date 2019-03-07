/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.launcher;

import static br.org.otojunior.sample.springboot.batch.config.SampleBatchConfiguration.SAMPLE_JOB_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	name=SAMPLE_JOB_NAME)
public class SampleJobLauncher {
	private static final Logger log = LoggerFactory.getLogger(SampleJobLauncher.class);

	@Autowired
	public JobOperator jobOperator;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay=1_000) // 10 segundos.
	public void runJob() throws Exception {
		log.info("Disparado job {}", SAMPLE_JOB_NAME);
		this.jobOperator.startNextInstance(SAMPLE_JOB_NAME);
	}
}
