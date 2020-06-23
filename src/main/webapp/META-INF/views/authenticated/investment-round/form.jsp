
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
	<acme:form-textbox code="authenticated.investment-round.form.label.ticker" path="ticker" />
	<acme:form-moment code="authenticated.investment-round.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment code="authenticated.investment-round.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.kindOfRound" path="kindOfRound" />
	<acme:form-textbox code="authenticated.investment-round.form.label.title" path="title" />
	<acme:form-textarea code="authenticated.investment-round.form.label.description" path="description" />
	<acme:form-money code="authenticated.investment-round.form.label.amountOfMoney" path="amountOfMoney"/>
	<acme:form-url code="authenticated.investment-round.form.label.link" path="link"/>
	
<acme:form-panel code="authenticated.investment-round.form.label.entrepreneur">
	<acme:form-textbox code="authenticated.investment-round.form.label.entrepreneur.userAccount.username" path="entrepreneur.userAccount.username"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.entrepreneur.userAccount.identity.name" path="entrepreneur.userAccount.identity.name"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.entrepreneur.userAccount.identity.surname" path="entrepreneur.userAccount.identity.surname"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.entrepreneur.userAccount.identity.email" path="entrepreneur.userAccount.identity.email"/>
</acme:form-panel>

<acme:form-submit test="${hasActivities}" method="get" code="authenticated.investment-round.form.button.workProgramme" action="/authenticated/activity/LIST_ACTIVITY_INVESTMENT_ROUND?id=${id}" />
<acme:form-submit test="${hasAccountingRecords}" method="get" code="authenticated.investment-round.form.button.accounting-records" action="/authenticated/accounting-record/LIST_ACCOUNTING_RECORD_INVESTMENT_ROUND?investmentId=${id}" />
<acme:form-submit test="${hasForum}" method="get" code="authenticated.investment-round.form.button.forum" action="/authenticated/forum/SHOW_FORUM_INVESTMENT_ROUND?investmentId=${id}" />

	<acme:form-submit test="${!hasForum && status == 'PUBLISHED'}" method="get" code="authenticated.investment-round.form.button.forum.create" action="/authenticated/forum/create?roundId=${id}" />
<acme:form-submit test="${!iHaveApplication && status == 'PUBLISHED'}" code="investor.investment-round.form.button.apply" action="/investor/application/create?investmentId=${id}" method="get" access="hasRole('Investor')"/>

	<acme:form-return code="authenticated.investment-round.form.button.return"/>

</acme:form>
