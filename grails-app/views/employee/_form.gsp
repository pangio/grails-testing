<%@ page import="com.pangio.mall.Employee" %>



<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="employee.name.label" default="Name" />
		
	</label>
	<g:textField name="name" pattern="${employeeInstance.constraints.name.matches}" value="${employeeInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="employee.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" pattern="${employeeInstance.constraints.lastName.matches}" value="${employeeInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'personalId', 'error')} ">
	<label for="personalId">
		<g:message code="employee.personalId.label" default="Personal Id" />
		
	</label>
	<g:textField name="personalId" value="${employeeInstance?.personalId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'status', 'error')} required">
	<label for="status">
		<g:message code="employee.status.label" default="Status" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="status" from="${com.pangio.mall.Status?.values()}" keys="${com.pangio.mall.Status.values()*.name()}" required="" value="${employeeInstance?.status?.name()}" />

</div>

