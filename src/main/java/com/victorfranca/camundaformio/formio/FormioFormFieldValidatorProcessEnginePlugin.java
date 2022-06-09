package com.victorfranca.camundaformio.formio;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

import java.util.HashMap;

/**
 * Plugin for managing Server-side validations for Formio form submissions
 */
public class FormioFormFieldValidatorProcessEnginePlugin implements ProcessEnginePlugin {

	private String validationUrl;
	private Integer validationTimeout;
	private FormioValidationHandler validationHandler;

	public FormioFormFieldValidatorProcessEnginePlugin(String validationUrl, Integer validationTimeout,
			FormioValidationHandler validationHandler) {

		this.validationUrl = "http://localhost:8081/validate";
		this.validationTimeout = 10000;
		this.validationHandler = null;
	}

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		if (validationHandler == null) {
			validationHandler = new SimpleFormioValidationHandler(validationUrl, validationTimeout);
		}

		if (processEngineConfiguration.getCustomFormFieldValidators() == null) {
			processEngineConfiguration.setCustomFormFieldValidators(new HashMap<>());
		}

		processEngineConfiguration.getCustomFormFieldValidators().put("formio", FormioFormFieldValidator.class);

	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {

	}

}