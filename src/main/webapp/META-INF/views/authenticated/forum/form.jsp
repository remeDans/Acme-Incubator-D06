<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.forum.form.label.title" path="title" />
	
	
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="authenticated.forum.form.label.creator" path="creator.userAccount.username" readonly="true"/>
	<acme:form-submit code="authenticated.forum.form.button.add-message" action="/authenticated/message/create?forumId=${id}" method="get" />
	<acme:form-submit test="${iCreator}" code="authenticated.forum.form.button.add-participant" action="/authenticated/participant/create?forumId=${id}" method="get" />
	<acme:form-submit test="${iCreator}" code="authenticated.forum.form.button.list-participants" action="/authenticated/participant/list?id=${id}" method="get" />
	<acme:form-submit test="${hasMessages}" method="get" code="authenticated.forum.form.button.messages" action="/authenticated/message/LIST_MESSAGES_FORUM?forumId=${id}" />
	<acme:form-submit test="${iCreator && ((command == 'delete')|| (command == 'show' ))}" code="authenticated.forum.form.button.delete" action="/authenticated/forum/delete" />
	</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="authenticated.forum.form.button.create" action="/authenticated/forum/create?roundId=${roundId}" />
	
  	<acme:form-return code="authenticated.forum.form.button.return"/>
</acme:form>


