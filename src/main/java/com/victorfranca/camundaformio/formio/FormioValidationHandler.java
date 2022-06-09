package com.victorfranca.camundaformio.formio;

import org.camunda.spin.json.SpinJsonNode;

public interface FormioValidationHandler {

	SpinJsonNode validate(SpinJsonNode schema, SpinJsonNode submission);
}