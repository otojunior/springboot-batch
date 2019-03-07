/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomItemProcessor implements ItemProcessor<Resource, InputStream> {
	@Override
	public InputStream process(Resource item) throws Exception {
		File tmpfile = File.createTempFile("prefix", "suffix");
		
		StreamUtils.copy(item.getInputStream(),
			new GZIPOutputStream(
			new FileOutputStream(tmpfile)));
		
		return new FileInputStream(tmpfile);
	}
}
