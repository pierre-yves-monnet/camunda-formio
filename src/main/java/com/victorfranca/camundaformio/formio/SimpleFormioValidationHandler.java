package com.victorfranca.camundaformio.formio;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.camunda.bpm.engine.impl.form.validator.FormFieldConfigurationException;
import org.camunda.spin.Spin;
import org.camunda.spin.json.SpinJsonNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.stream.Collectors;

class SimpleFormioValidationHandler implements FormioValidationHandler {

	private String validationUrl;
	private Integer validationTimeout;

	public SimpleFormioValidationHandler(String validationUrl, Integer validationTimeout) {
		this.validationUrl = validationUrl;
		this.validationTimeout = validationTimeout;
	}

	@Override
	public SpinJsonNode validate(SpinJsonNode schema, SpinJsonNode submission) {

		SpinJsonNode requestBody = SpinJsonNode.JSON("{}").prop("schema", schema).prop("submission", submission);

		HttpResponse response;
		try {
			response = Request.Post(validationUrl)
					.body(new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON))
					.socketTimeout(validationTimeout).connectTimeout(validationTimeout).execute().returnResponse();

		} catch (UnsupportedCharsetException | IOException ex) {
			throw new FormFieldConfigurationException("Request Failure!!", "Unable to reach Formio validation server.",
					ex.getCause());
		}

		String responseBody;
		try {
			responseBody = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8)).lines()
							.collect(Collectors.joining());
		} catch (UnsupportedOperationException | IOException e) {
			throw new FormFieldConfigurationException("Submission Validation returned false", e);
		}

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_ACCEPTED) {
			return Spin.JSON(responseBody);

		} else {
			// FormFieldConfiguration needed to be used because it is the only exception
			// that returned a proper response with a body...
			throw new FormFieldConfigurationException("Submission Validation returned false", responseBody);
		}

	}

}
