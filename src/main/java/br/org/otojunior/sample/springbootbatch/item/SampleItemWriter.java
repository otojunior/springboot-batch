/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.item;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.org.otojunior.sample.springbootbatch.jpa.Entidade;
import br.org.otojunior.sample.springbootbatch.jpa.EntidadeRepository;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class SampleItemWriter implements ItemWriter<String>  {
	private static final Logger log = LoggerFactory.getLogger(SampleItemWriter.class);

	@Autowired
	private EntidadeRepository repository;
	
	/**
	 * 
	 */
	@Override
	public void write(List<? extends String> items) throws Exception {
		log.debug("Writing {} itens", items.size());
		repository.saveAll(items
			.stream()
			.map(Entidade::new)
			.collect(Collectors.toList()));
	}
}
