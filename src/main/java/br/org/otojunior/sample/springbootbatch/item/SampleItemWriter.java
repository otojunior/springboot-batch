/**
 * 
 */
package br.org.otojunior.sample.springbootbatch.item;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class SampleItemWriter implements ItemWriter<String>  {
	private static final Logger log = LoggerFactory.getLogger(SampleItemWriter.class);

	@Autowired
	private EntityManager entityManager;
	
	/**
	 * 
	 */
	@Override
	public void write(List<? extends String> items) throws Exception {
		log.debug("Writing {} itens", items.size());
		items.forEach(item -> {
			Entidade e = new Entidade(item);
			entityManager.persist(e);
		});
		Thread.sleep(500);
	}
}
