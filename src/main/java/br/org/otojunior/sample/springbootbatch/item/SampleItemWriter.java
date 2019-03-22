/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class SampleItemWriter implements ItemWriter<String>  {
	private static final Logger log = LoggerFactory.getLogger(SampleItemWriter.class);

	/**
	 * 
	 */
	@Override
	public void write(List<? extends String> items) throws Exception {
		log.debug("Writing {} itens", items.size());
		Thread.sleep(250);
	}
}
