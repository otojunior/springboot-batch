/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.item;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class SampleItemProcessor implements ItemProcessor<String, String>  {
	/**
	 * 
	 */
	@Override
	public String process(String item) throws Exception {
		return item.toUpperCase();
	}
}
