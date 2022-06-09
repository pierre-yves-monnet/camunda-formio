package com.victorfranca.camundaformio.config;

import connectjar.org.apache.http.HttpVersion;
import connectjar.org.apache.http.client.methods.CloseableHttpResponse;
import connectjar.org.apache.http.entity.ContentType;
import connectjar.org.apache.http.entity.StringEntity;
import connectjar.org.apache.http.message.BasicHttpResponse;
import org.camunda.connect.httpclient.HttpConnector;
import org.camunda.connect.spi.ConnectorConfigurator;
import org.camunda.connect.spi.ConnectorInvocation;
import org.camunda.connect.spi.ConnectorRequestInterceptor;

import java.io.IOException;

public class MockHttpConnectorConfigurator implements ConnectorConfigurator<HttpConnector> {

	@Override
	public void configure(HttpConnector connector) {
		
		System.out.println();
		
		connector.addRequestInterceptor(new ConnectorRequestInterceptor() {

			@Override
			public Object handleInvocation(ConnectorInvocation arg0) throws Exception {
				// intercept the call. => do not call invocation.proceed()

				// Could do validation on the invocation here:
				// invocation.getRequest() ...

				// build response using http client api
				TestHttpResponse testHttpResponse = new TestHttpResponse();
				testHttpResponse.setEntity(new StringEntity("{\"access_token\": \"1\"}", ContentType.APPLICATION_JSON));

				return testHttpResponse;
			}
		});
	}

	@Override
	public Class<HttpConnector> getConnectorClass() {
		// TODO Auto-generated method stub
		return HttpConnector.class;
	}

	static class TestHttpResponse extends BasicHttpResponse implements CloseableHttpResponse {

		public TestHttpResponse() {
			super(HttpVersion.HTTP_1_1, 200, "OK");
		}

		@Override
		public void close() throws IOException {
			// no-op
		}

	}

}
