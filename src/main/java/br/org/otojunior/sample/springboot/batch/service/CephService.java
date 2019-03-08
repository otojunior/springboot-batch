/**
 * 
 */
package br.org.otojunior.sample.springboot.batch.service;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;

/**
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 *
 */
public class CephService extends MinioClient {
	/**
	 * 
	 * @param endpoint
	 * @param accessKey
	 * @param secretKey
	 * @throws InvalidEndpointException
	 * @throws InvalidPortException
	 */
	public CephService(String endpoint, String accessKey, String secretKey) throws
			InvalidEndpointException,
			InvalidPortException {
		super(endpoint, accessKey, secretKey);
	}
}
