<%@ page import="com.pangio.mall.Store" %>



<div class="fieldcontain ${hasErrors(bean: storeInstance, field: 'employees', 'error')} ">
	<label for="employees">
		<g:message code="store.employees.label" default="Employees" />
		
	</label>
	<g:select name="employees" from="${com.pangio.mall.Employee.list()}" multiple="multiple" optionKey="id" size="5" value="${storeInstance?.employees*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: storeInstance, field: 'mall', 'error')} required">
	<label for="mall">
		<g:message code="store.mall.label" default="Mall" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="mall" name="mall.id" from="${com.pangio.mall.Mall.list()}" optionKey="id" required="" value="${storeInstance?.mall?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: storeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="store.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${storeInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: storeInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="store.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="type" from="${com.pangio.mall.Type?.values()}" keys="${com.pangio.mall.Type.values()*.name()}" required="" value="${storeInstance?.type?.name()}" />

</div>

