/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.launcher;

import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import br.org.otojunior.sample.springboot.batch.config.SampleBatchConfiguration;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
@ConditionalOnProperty(
	prefix="br.org.otojunior.sample.springboot.batch.schedule",
	name=SampleBatchConfiguration.UPPERCASEITEMNAME_JOB_NAME,
	havingValue="true",
	matchIfMissing=false)
public class UpperCaseItemNameJobLauncher {
	@Autowired
	public JobOperator jobOperator;
	
	/**
	 * 
	 * @throws Exception
	 */
	@Scheduled(fixedDelay=10_000) // 10 segundos.
	public void runJob() throws Exception {
		this.jobOperator.startNextInstance(SampleBatchConfiguration.UPPERCASEITEMNAME_JOB_NAME);
	}
}
