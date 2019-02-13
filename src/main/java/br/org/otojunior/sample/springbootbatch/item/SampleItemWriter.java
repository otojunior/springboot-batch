/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.item;

import static java.lang.System.out;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class SampleItemWriter implements ItemWriter<String>  {
	/**
	 * 
	 */
	@Override
	public void write(List<? extends String> items) throws Exception {
		items.forEach(out::println);		
	}
}
