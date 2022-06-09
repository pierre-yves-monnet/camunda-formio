package com.victorfranca.camundaformio.formio;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

import java.util.ArrayList;

public class FormioParseListenerProcessEnginePlugin implements ProcessEnginePlugin {

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		if (processEngineConfiguration.getCustomPreBPMNParseListeners() == null) {
			processEngineConfiguration.setCustomPreBPMNParseListeners(new ArrayList<>());
		}
		processEngineConfiguration.getCustomPreBPMNParseListeners().add(new FormioParseListener());
	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {

	}

}