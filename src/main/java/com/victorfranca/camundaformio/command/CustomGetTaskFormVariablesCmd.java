package com.victorfranca.camundaformio.command;

import org.camunda.bpm.engine.impl.cmd.GetTaskFormVariablesCmd;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.camunda.bpm.engine.variable.VariableMap;

import java.util.Collection;

class CustomGetTaskFormVariablesCmd extends GetTaskFormVariablesCmd {

	private static final long serialVersionUID = 1L;

	final String allowedVariablesKey = "allowed-variables";
	final String restrictedVariablesKey = "restricted-variables";

	public CustomGetTaskFormVariablesCmd(String taskId, Collection<String> variableNames,
			boolean deserializeObjectValues) {
		super(taskId, variableNames, deserializeObjectValues);
	}

	@Override
	public VariableMap execute(CommandContext commandContext) {

		// TODO allowed/restricted vars
		return super.execute(commandContext);

//		UserTask utModelInstance = commandContext.getTaskManager().findTaskById(resourceId)
//				.getBpmnModelElementInstance();
//
//		List<CamundaProperty> allowedVars = utModelInstance.getExtensionElements().getElementsQuery()
//				.filterByType(CamundaProperties.class).list().stream().findFirst().get().getCamundaProperties().stream()
//				.filter(o -> o.getCamundaName().equals(allowedVariablesKey)).collect(Collectors.toList());
//
//		List<CamundaProperty> restrictedVars = utModelInstance.getExtensionElements().getElementsQuery()
//				.filterByType(CamundaProperties.class).list().stream().findFirst().get().getCamundaProperties().stream()
//				.filter(o -> o.getCamundaName().equals(restrictedVariablesKey)).collect(Collectors.toList());

//allowedVars.stream().map(o -> o.getCamundaValue().split(",")).;
//
////		 
////		         allowedVars?.let { camProp ->
////		             val varNames = camProp.camundaValue.split(",").map { it.trim() }
////		             result.entries.removeIf {
////		                 it.key !in varNames
////		             }
////		         }
////		 
////		         restrictedVars?.let { camProp->
////		             val varNames = camProp.camundaValue.split(",").map { it.trim() }
////		             result.entries.removeIf {
////		                 it.key in varNames
////		             }
////		         }
////		 
////		         return result
//}

	}
}