package com.victorfranca.camundaformio;

import com.victorfranca.camundaformio.helper.ProcessVariable;

import static com.victorfranca.camundaformio.helper.ProcessVariable.create;

public class ProcessVariables {
	
	/**
	 * The following three are just demo variables and can be deleted in your
	 * project.
	 */
	public static final ProcessVariable<String> VAR_SAMPLE = create("sampleVar");
	public static final ProcessVariable<String> VAR_LOGMESSAGE = create("logMessage");
	public static final ProcessVariable<String> VAR_AMOUNT = create("amount");
	public static final ProcessVariable<String> VAR_REQUEST = create("request");
	public static final ProcessVariable<String> VAR_APPROVAL_TYPE = create("approvalType");

}
