/**
 * 
 */
package br.org.otojunior.sample.springbootbatch;

import java.util.Arrays;
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
public class SampleConfigFactory {
	/**
	 * 
	 * @return
	 */
	@Bean
	public ItemReader<String> createReader() {
		return new ListItemReader<String>(Arrays.asList(
			"arnaldo", "beatriz", "carlos", "daniela", "edson", "fernanda",
			"geraldo", "helena", "igor", "juliana", "kelly", "leandro", "maria", 
			"nadia", "oto", "pedro", "quenia", "renato", "sabrina", "tiago",
			"ursula", "viviane", "william", "xico", "yuri", "zuleica")); 
	}
}
