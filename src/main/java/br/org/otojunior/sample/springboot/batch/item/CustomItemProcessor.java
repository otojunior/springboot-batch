/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomItemProcessor implements ItemProcessor<String, String> {
	private static Logger LOG = LoggerFactory.getLogger(CustomItemProcessor.class);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String process(String input) throws Exception {
		String output = input.toUpperCase();
		LOG.debug(output);
		Thread.sleep(100);
		return output;
	}
}
