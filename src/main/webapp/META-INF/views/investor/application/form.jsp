
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

	<jstl:if test="${command == 'create'}" >
	<input name = "creationMoment" type="hidden"/>
	</jstl:if>
	
	
		<jstl:if test="${command == 'show' || command == 'update'}">
		<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" readonly="true"/>
	</jstl:if>

	<jstl:if test="${command == 'create'}" >
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" readonly="false"/>
	</jstl:if>

	
	<acme:form-textarea code="investor.application.form.label.statement" path="statement"/>
	
	<jstl:if test="${command != 'create'}" >
	<acme:form-moment code="investor.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="investor.application.form.label.status" path="status" />
	<acme:form-textarea code="investor.application.form.label.justification" path="justification" />
	</jstl:if>
	<acme:form-money code="investor.application.form.label.investmentMoneyOffer" path="investmentMoneyOffer" />
	
	<jstl:if test="${command != 'create'}" >
	<acme:form-panel code="investor.application.form.label.investor">
	<acme:form-textbox code="investor.application.form.label.investor.username" path="investor.userAccount.username"/>
	<acme:form-textbox code="investor.application.form.label.investor.identity.name" path="investor.userAccount.identity.name"/>
	<acme:form-textbox code="investor.application.form.label.investor.identity.surname" path="investor.userAccount.identity.surname"/>
	<acme:form-textbox code="investor.application.form.label.investor.identity.email" path="investor.userAccount.identity.email"/>
	</acme:form-panel>
	
	<acme:form-submit  method="get" code="investor.application.form.button.investmentRound" action="/investor/investment-round/SHOW_INVESTMENT_ROUND_APPLICATION?applicationId=${id}"/>
	</jstl:if>
	

	
	<acme:form-submit test="${command == 'create'}" code="investor.application.form.button.create" action="/investor/application/create?investmentId=${investmentId}" />

	
	<acme:form-return code="investor.application.form.button.return"/>
	

</acme:form>

	
