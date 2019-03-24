/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.job.azul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component("azulJobListener")
public class AzulJobListener implements JobExecutionListener {
	private static Logger log = LoggerFactory.getLogger(AzulJobListener.class);

	@Autowired
	public JobOperator jobOperator;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (log.isDebugEnabled()) {
			log.debug(jobExecution.toString());
		}
		try {
			this.jobOperator.startNextInstance(AzulJobConfig.AZUL_JOB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeJob(JobExecution jobExecution) {
		if (log.isDebugEnabled()) {
			log.debug(jobExecution.toString());
		}
	}
}
