/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class SampleItemProcessor implements ItemProcessor<String, String>  {
	private static final Logger log = LoggerFactory.getLogger(SampleItemProcessor.class);

	/**
	 * 
	 */
	@Override
	public String process(String item) throws Exception {
		String processed = item.toUpperCase();
		Thread.sleep(500); // Simulate hard work
		
		log.debug("{} ---> {}", item, processed );
		return processed; 
	}
}
