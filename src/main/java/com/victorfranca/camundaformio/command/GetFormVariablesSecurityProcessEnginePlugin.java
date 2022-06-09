package com.victorfranca.camundaformio.command;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.FormServiceImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;
import org.camunda.bpm.engine.variable.VariableMap;

import java.util.Collection;

/**
 * Plugin for replacing the GetTaskFormVariablesCmd with
 * CustomGetTaskFormVariablesCmd. The replacement provides additional security
 * filtering based on Camunda Extension Properties configurations in the bpmn
 */
public class GetFormVariablesSecurityProcessEnginePlugin implements ProcessEnginePlugin {

	@Override
	public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
		// Setup a new instance of form service that has the cmd for security filtering
		// of variables
		// @TODO add startup info about form service being overriden and security added
		// to remove variables.
		processEngineConfiguration.setFormService(new FormServiceImpl() {
			@Override
			public VariableMap getTaskFormVariables(String taskId, Collection<String> formVariables,
					boolean deserializeObjectValues) {
				return commandExecutor
						.execute(new CustomGetTaskFormVariablesCmd(taskId, formVariables, deserializeObjectValues));
			}
		});
	}

	@Override
	public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {

	}

	@Override
	public void postProcessEngineBuild(ProcessEngine processEngine) {

	}

}