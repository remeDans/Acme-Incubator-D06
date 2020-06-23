
<%--
- list.jsp
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

<acme:form readonly="true">
	<acme:form-textbox code="investor.accounting-record.form.label.title" path="title" />
	<acme:form-moment code="investor.accounting-record.form.label.status" path="status"/>
	<acme:form-textbox code="investor.accounting-record.form.label.creationMoment" path="creationMoment" />
	<acme:form-textarea code="investor.accounting-record.form.label.body" path="body" />


	<acme:form-panel code="authenticated.accounting-record.form.label.bookkeeper">
	<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.username" path="bookkeeper.userAccount.username"/>
	<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.identity.name" path="bookkeeper.userAccount.identity.name"/>
	<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.identity.surname" path="bookkeeper.userAccount.identity.surname"/>
	<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.identity.email" path="bookkeeper.userAccount.identity.email"/>
</acme:form-panel>

	<acme:form-return code="investor.accounting-record.form.button.return"/>

</acme:form>


