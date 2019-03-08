/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomItemReader extends AbstractItemStreamItemReader<Resource> {
	@Autowired
	private ResourceLoader resourceLoader;
	
	private List<Resource> resources;

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		try {
			Resource[] res = ResourcePatternUtils
				.getResourcePatternResolver(resourceLoader)
				.getResources("file:entrada/input*.txt");
			this.resources = new ArrayList<>(Arrays.asList(res));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Resource read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return resources.isEmpty() ? null : resources.remove(0);
	}
}
