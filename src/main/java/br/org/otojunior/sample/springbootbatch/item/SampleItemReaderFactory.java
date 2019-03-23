/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
public class SampleItemReaderFactory {
	/**
	 * 
	 * @return
	 */
	@Bean("reader1")
	public ItemReader<String> createReader1() {
		List<String> lista = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			lista.add("vermelho" + i);
		}
		return new ListItemReader<String>(lista); 
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean("reader2")
	public ItemReader<String> createReader2() {
		List<String> lista = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			lista.add("azul" + i);
		}
		return new ListItemReader<String>(lista); 
	}
}
