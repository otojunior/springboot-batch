/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.job.custom.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomJobListener implements JobExecutionListener {
	private static Logger log = LoggerFactory.getLogger(CustomJobListener.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (log.isDebugEnabled()) {
			log.debug(jobExecution.toString());
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
