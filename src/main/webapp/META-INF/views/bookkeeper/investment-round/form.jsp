
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
	<acme:form-textbox code="bookeeper.investment-round.form.label.ticker" path="ticker" />
	<acme:form-moment code="bookeeper.investment-round.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment code="bookeeper.investment-round.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="bookeeper.investment-round.form.label.kindOfRound" path="kindOfRound" />
	<acme:form-textbox code="bookeeper.investment-round.form.label.title" path="title" />
	<acme:form-textarea code="bookeeper.investment-round.form.label.description" path="description" />
	<acme:form-money code="bookeeper.investment-round.form.label.amountOfMoney" path="amountOfMoney"/>
	<acme:form-url code="bookeeper.investment-round.form.label.link" path="link"/>
	
<acme:form-panel code="bookeeper.investment-round.form.label.entrepreneur">
	<acme:form-textbox code="bookeeper.investment-round.form.label.entrepreneur.userAccount.username" path="entrepreneur.userAccount.username"/>
	<acme:form-textbox code="bookeeper.investment-round.form.label.entrepreneur.userAccount.identity.name" path="entrepreneur.userAccount.identity.name"/>
	<acme:form-textbox code="bookeeper.investment-round.form.label.entrepreneur.userAccount.identity.surname" path="entrepreneur.userAccount.identity.surname"/>
	<acme:form-textbox code="bookeeper.investment-round.form.label.entrepreneur.userAccount.identity.email" path="entrepreneur.userAccount.identity.email"/>
</acme:form-panel>

<acme:form-submit test="${hasActivities}" method="get" code="bookeeper.investment-round.form.button.workProgramme" action="/bookkeeper/activity/LIST_ACTIVITY_INVESTMENT_ROUND?id=${id}" />
<acme:form-submit test="${hasAccountingRecords}" method="get" code="bookeeper.investment-round.form.button.accounting-records" action="/bookkeeper/accounting-record/LIST_ACCOUNTING_RECORD_INVESTMENT_ROUND?investmentId=${id}" />
<acme:form-submit method="get" code="bookeeper.investment-round.form.button.accounting-records.create" action="/bookkeeper/accounting-record/create?investmentId=${id}" />
<acme:form-submit test="${hasForum}" method="get" code="bookeeper.investment-round.form.button.forum" action="/bookkeeper/forum/SHOW_FORUM_INVESTMENT_ROUND?investmentId=${id}" />
<!--<acme:form-submit test="${hasApplication}" method="get" code="bookeeper.investment-round.form.button.application" action="/bookkeeper/application/SHOW_APPLICATION_INVESTMENT_ROUND?investmentId=${id}" />-->


	<acme:form-return code="bookeeper.investment-round.form.button.return"/>

</acme:form>
