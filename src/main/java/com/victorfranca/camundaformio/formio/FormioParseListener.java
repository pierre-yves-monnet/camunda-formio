package com.victorfranca.camundaformio.formio;

import org.camunda.bpm.engine.delegate.Expression;
import org.camunda.bpm.engine.impl.bpmn.parser.AbstractBpmnParseListener;
import org.camunda.bpm.engine.impl.context.Context;
import org.camunda.bpm.engine.impl.form.handler.DefaultStartFormHandler;
import org.camunda.bpm.engine.impl.form.handler.DelegateStartFormHandler;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.impl.pvm.process.ActivityImpl;
import org.camunda.bpm.engine.impl.pvm.process.ScopeImpl;
import org.camunda.bpm.engine.impl.util.xml.Element;

class FormioParseListener extends AbstractBpmnParseListener {

	private final String FORMIO_PROPERTY_PREFIX = "formio_";
	private final String FORMIO_DEPLOYMENT_KEY = "deployment";
	private final String FORMIO_PATH_KEY = "path";
	private final String FORMIO_TRANSIENT_KEY = "transient";
	private final String FORMIO_VAR_KEY = "var";
	private final String FORMIO_BASE_FORM_KEY = "embedded:/forms/formio.html";
	private final String FORMIO_SERVER_VALIDATION_KEY = "validation";
	private final String FORM_KEY_PATH = "http://camunda.org/schema/1.0/bpmn:formKey";

	protected String buildFormKeyFromExtensionProperties(Element element, ActivityImpl activity) {

		return null;
//      val props: Map<String, String>? = element.element("extensionElements")
//      ?.element("properties")
//      ?.elements("property")
//      ?.filter { it.attribute("name").startsWith(FORMIO_PROPERTY_PREFIX) }
//      ?.associateBy(
//              { it.attribute("name") },
//              { it.attribute("value") }
//      )
//      ?.mapKeys {
//          it.key.substringAfter(FORMIO_PROPERTY_PREFIX)
//      }
//
//if (!props.isNullOrEmpty()){
//
//  var formKey: String = "${FORMIO_BASE_FORM_KEY}?"
//
//  formKey = if (props.containsKey(FORMIO_DEPLOYMENT_KEY) && props.getValue(FORMIO_DEPLOYMENT_KEY).isNotBlank()){
//      val value = props[FORMIO_DEPLOYMENT_KEY]
//      formKey.plus("${FORMIO_DEPLOYMENT_KEY}=${value}")
//
//  } else if (props.containsKey(FORMIO_PATH_KEY) && props.getValue(FORMIO_VAR_KEY).isNotBlank()){
//      val value = props[FORMIO_PATH_KEY]
//      formKey.plus("${FORMIO_PATH_KEY}=${value}")
//  } else {
//      throw BpmnParseException("Formio Extension property configuration must have a deployment or a path property. (${activity.activityId})", element)
//  }
//
//  if (props.containsKey(FORMIO_VAR_KEY) && props.getValue(FORMIO_VAR_KEY).isNotBlank()){
//      val value = props[FORMIO_VAR_KEY]
//      formKey = formKey.plus("&${FORMIO_VAR_KEY}=${value}")
//  }
//
//  if (props.containsKey(FORMIO_TRANSIENT_KEY)){
//      val value = props[FORMIO_TRANSIENT_KEY]?: "false"
//      formKey = formKey.plus("&${FORMIO_TRANSIENT_KEY}=${value}")
//  }
//
//  return formKey
//
//} else {
//  return null
//}

	}

	protected boolean hasFormioServerValidation(Element element) {
		return false;

		// TODO formIOServerValidation is disabled

//      val props: Map<String, String>? = element.element("extensionElements")
//      ?.element("properties")
//      ?.elements("property")
//      ?.filter { it.attribute("name").startsWith(FORMIO_PROPERTY_PREFIX) }
//      ?.associateBy(
//              { it.attribute("name") },
//              { it.attribute("value") }
//      )
//      ?.mapKeys {
//          it.key.substringAfter(FORMIO_PROPERTY_PREFIX)
//      }
//
//return if (!props.isNullOrEmpty()){
//  props[FORMIO_SERVER_VALIDATION_KEY].toBoolean()
//} else {
//  false
//}

	}

	// TODO: server validation disabled
//    protected fun createFormioServerValidation(handler: DefaultFormHandler){
//        val prop = handler::class.memberProperties.single { it.name == "formFieldHandlers" }.also { it.isAccessible = true } as KMutableProperty1<DefaultFormHandler, ArrayList<FormFieldHandler>>
//
//        val handlersList = prop.get(handler)
//
//        val ffH = FormFieldHandler().apply {
//            id = "formio"
//            setType(StringFormType())
//            validationHandlers = listOf(FormFieldValidationConstraintHandler().apply {
//                name = "formio"
//                validator = FormioFormFieldValidator()
//            })
//        }
//        handlersList.add(ffH)
//    }
//
//
	@Override
	public void parseUserTask(Element userTaskElement, ScopeImpl scope, ActivityImpl activity) {

		// TODO it seams to be required: parseUserTask

		super.parseUserTask(userTaskElement, scope, activity);

//      val formKey = buildFormKeyFromExtensionProperties(userTaskElement, activity)
//      if (activity.activityBehavior is UserTaskActivityBehavior){
//          if (formKey != null){
//              val exp = Context.getProcessEngineConfiguration().expressionManager.createExpression(formKey)
//              (activity.activityBehavior as UserTaskActivityBehavior).taskDefinition.formKey = exp
//
//              if (hasFormioServerValidation(userTaskElement)){
//                  createFormioServerValidation(((activity.activityBehavior as UserTaskActivityBehavior).taskDefinition.taskFormHandler as DelegateTaskFormHandler).formHandler as DefaultFormHandler)
//              }
//          }
//      }
	}

	protected void modifyStartEventFormKey(String formKey, DefaultStartFormHandler handler) {
		Expression formKeyExp = Context.getProcessEngineConfiguration().getExpressionManager()
				.createExpression(formKey);

		// TODO seams to be required: modifyStartEventFormKey

//        val formKeyProp = handler::class.memberProperties.single { it.name == "formKey" }.also { it.isAccessible = true } as KMutableProperty1<DefaultStartFormHandler, Expression>
//        
//        
//        formKeyProp.set(handler, formKeyExp)
	}

	@Override
	public void parseStartEvent(Element startEventElement, ScopeImpl scope, ActivityImpl startEventActivity) {
		if (startEventActivity.getProperties().toMap().get("type").equals("startEvent")
				&& scope instanceof ProcessDefinitionEntity) {
			String formKey = buildFormKeyFromExtensionProperties(startEventElement, startEventActivity);

			if (formKey != null) {
				if (((ProcessDefinitionEntity) scope).getStartFormHandler() instanceof DelegateStartFormHandler) {

					DelegateStartFormHandler delegateStartFormHandler = (DelegateStartFormHandler) ((ProcessDefinitionEntity) scope)
							.getStartFormHandler();

					if (delegateStartFormHandler.getFormHandler() instanceof DefaultStartFormHandler) {
						modifyStartEventFormKey(formKey,
								(DefaultStartFormHandler) (delegateStartFormHandler.getFormHandler()));

						if (hasFormioServerValidation(startEventElement)) {

							// TODO FormIo server validation disabled
							// createFormioServerValidation(delegateStartFormHandler.getFormHandler());
						}
					}
				}
			}

		}

	}

}