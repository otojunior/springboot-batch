/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.item;

import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import br.org.otojunior.sample.springboot.batch.service.CephService;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Component
public class CustomItemWriter implements ItemWriter<EntradaSaida> {
	private static final Logger log = LoggerFactory.getLogger(CustomItemWriter.class);
	private static final String MIME_GZ = "application/gzip";
	
	@Autowired
	private CephService service;
	
	@Override
	public void write(List<? extends EntradaSaida> items) throws Exception {
		items.forEach(item -> {
			try {
				Resource entrada = item.getEntrada();
				Resource saida = item.getSaida();
				
				log.info("Gravando no Ceph: {}", saida.getFilename());
				
				String bucketName = "bucketteste";
				String objectKey = entrada.getFilename() + ".gz";
				
				service.putObject(bucketName, objectKey, saida.getInputStream(), MIME_GZ);
				
				FileUtils.moveFile(
					FileUtils.getFile(item.getEntrada().getFile().getAbsolutePath()), 
					FileUtils.getFile(item.getEntrada().getFile().getAbsolutePath() + ".processado"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
