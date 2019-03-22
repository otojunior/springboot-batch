/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.job.custom.stepum;

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
public class CustomItemWriter implements ItemWriter<String> {
	private static Logger log = LoggerFactory.getLogger(CustomItemWriter.class);
	
	@Override
	public void write(List<? extends String> items) throws Exception {
		log.debug("GRAVADO: {}", items.size());
	}
}