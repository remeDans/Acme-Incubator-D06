<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.bookkeeper-request.form.label.username" path="userAccount.username" readonly="true" />
	<acme:form-textbox code="administrator.bookkeeper-request.form.label.firmName" path="firmName" readonly="true" />
	<acme:form-textbox code="administrator.bookkeeper-request.form.label.responsabilityStatement" path="responsibilityStatement"
		readonly="true" />
	<acme:form-select code="administrator.bookkeeper-request.form.label.status" path="status">
			<acme:form-option code="authenticated.bookkeeper-request.form.label.status.rejected" value="PENDING" />
			<acme:form-option code="authenticated.bookkeeper-request.form.label.status.accepted" value="ACCEPTED" />
			<acme:form-option code="authenticated.bookkeeper-request.form.label.status.rejected" value="REJECTED" />
	</acme:form-select>
	
	<acme:form-submit test="${command == 'show' || command == 'update'}" code="bookkeeper.bookkeeper-request.form.button.update"
		action="/administrator/bookkeeper-request/update" />
	<acme:form-return code="administrator.bookkeeper-request.form.button.return" />
</acme:form>