/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item;

import java.io.InputStream;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.stereotype.Component;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomItemWriter implements ItemWriter<InputStream> {
	@Override
	public void write(List<? extends InputStream> items) throws Exception {
		items.forEach(item -> {
			System.out.println("Gravar no Ceph: " + item);
		});
	}
}
