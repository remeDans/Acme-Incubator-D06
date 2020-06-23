
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

<acme:form >

	<acme:form-textbox code="bookeeper.accounting-record.form.label.title" path="title" />
	<acme:form-textbox code="bookeeper.accounting-record.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-select  code="bookeeper.accounting-record.form.label.status" path="status" readonly="false" >
			<acme:form-option code="bookeeper.accounting-record.form.label.status.---" value="" />
			<acme:form-option code="bookeeper.accounting-record.form.label.status.DRAFT" value="DRAFT" selected="${status == 'DRAFT'}" />
			<acme:form-option code="bookeeper.accounting-record.form.label.status.PUBLISHED" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
	</acme:form-select>
	<acme:form-textarea code="bookeeper.accounting-record.form.label.body" path="body" />

	<jstl:if test="${command != 'create'}">
		<acme:form-panel code="authenticated.accounting-record.form.label.bookkeeper">
		<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.username" path="bookkeeper.userAccount.username" readonly="true"/>
		<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.identity.name" path="bookkeeper.userAccount.identity.name" readonly="true"/>
		<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.identity.surname" path="bookkeeper.userAccount.identity.surname" readonly="true"/>
		<acme:form-textbox code="authenticated.accounting-record.form.label.bookkeeper.userAccount.identity.email" path="bookkeeper.userAccount.identity.email" readonly="true"/>
		</acme:form-panel>
	</jstl:if>

<acme:form-submit test="${command == 'create'}" code="bookkeeper.accounting-record.form.button.create" action="/bookkeeper/accounting-record/create?investmentId=${investmentId}" />
<acme:form-submit test="${(status == 'DRAFT') && ((command == 'update') ||(command == 'show')) }" code="bookkeeper.accounting-record.form.button.update" action="/bookkeeper/accounting-record/update" />

	<acme:form-return code="bookeeper.accounting-record.form.button.return"/>

</acme:form>


