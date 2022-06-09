# camunda-formio

See https://github.com/StephenOTT/camunda-formio-plugin
# installation
Not very clear. "See the springboot folder for a example of the deployment"
and the springboot folder README say
````
This is a springboot example of setup of the plugin and additional static files.
See the gradle build for specific files that are copied from the common folder in the root of this repo.
````
How are we suppose to understand it?

# Setup the server
Open this project in Intellij and navigate to `src/main/java/org/camunda/server/CamundaServer`

Click the green triangle to start the project

Once the Spring boot application starts, open your browser and navigate to:

http://localhost:8080/camunda/app/welcome/default/#!/login.
Log as demo/demo

Go to task list, and start the process "expenseNotes" 

# build a form
Go to https://formio.github.io/formio.js/app/builder and build a form

Question: how to edit a form??

Save the JSON under a file, under `src/main/resources`. Example `FormExpenseNote.json`


# Reference the form in the process
In the process you can reference the form in the Starter or in the User task


Starter:
Type: Embedded or External Forms 
embedded:/forms/formio.html?deployment=FormExpenseNote.json&var=submission&transient=true

Task:
Type: Embedded or External Forms
`embedded:/forms/formio.html?deployment=FormExpenseNote.json&var=subWithServerValidation

