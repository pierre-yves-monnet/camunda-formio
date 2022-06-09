package com.victorfranca.camundaformio;


import com.victorfranca.camundaformio.command.GetFormVariablesSecurityProcessEnginePlugin;
import com.victorfranca.camundaformio.formio.FormioFormFieldValidatorProcessEnginePlugin;
import com.victorfranca.camundaformio.formio.FormioParseListenerProcessEnginePlugin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ProcessApp {
	
	public static void main(String... args) {
		SpringApplication.run(ProcessApp.class);
	}

	@Component
	class MyPlugin extends FormioFormFieldValidatorProcessEnginePlugin {

		public MyPlugin() {
			super("http://localhost:8080/validate", 10000, null);
		}

	}

	@Component
	class MyFormsSecurityPlugin extends GetFormVariablesSecurityProcessEnginePlugin {

	}

	@Component
	class MyFormioConfigParser extends FormioParseListenerProcessEnginePlugin {

	}

}