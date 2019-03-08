/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
@Configuration
public class CephServiceFactory {
	@Value("${ceph.endpoint}") private String endpoint;
	@Value("${ceph.accesskey}") private String accessKey;
	@Value("${ceph.secretkey}") private String secretKey;
	
	/**
	 * 
	 * @return
	 * @throws InvalidPortException 
	 * @throws InvalidEndpointException 
	 */
	@Bean
	public CephService createCephService() throws
			InvalidEndpointException,
			InvalidPortException {
		return new CephService(endpoint, accessKey, secretKey);
	}
}
