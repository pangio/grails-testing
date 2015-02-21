<%@ page import="com.pangio.mall.Mall" %>



<div class="fieldcontain ${hasErrors(bean: mallInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="mall.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${mallInstance?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: mallInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="mall.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${mallInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: mallInstance, field: 'stores', 'error')} ">
	<label for="stores">
		<g:message code="mall.stores.label" default="Stores" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${mallInstance?.stores?}" var="s">
    <li><g:link controller="store" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="store" action="create" params="['mall.id': mallInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'store.label', default: 'Store')])}</g:link>
</li>
</ul>


</div>

