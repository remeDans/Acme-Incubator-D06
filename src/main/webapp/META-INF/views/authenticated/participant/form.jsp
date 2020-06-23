<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="${command != 'create'}">
	<acme:form-hidden path="forumId" />
	<acme:form-textbox code="authenticated.participant.form.label.username" path="authenticated.userAccount.username" />

	<acme:form-submit test="${command == 'create'}" code="authenticated.participant.form.button.create" action="/authenticated/participant/create?forumId=${forumId}" />
	<acme:form-submit test="${command != 'create'}" code="authenticated.participant.form.button.delete" action="delete" />
	<acme:form-return code="authenticated.participant.form.button.return" />

</acme:form>