
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
	<acme:form-textbox code="authenticated.application.form.label.ticker" path="ticker" />
	<acme:form-textarea code="authenticated.application.form.label.statement" path="statement"/>
	
	<acme:form-moment code="authenticated.application.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textbox code="authenticated.application.form.label.status" path="status" />
	<acme:form-textarea code="authenticated.application.form.label.justification" path="justification" />
	<acme:form-money code="authenticated.application.form.label.investmentMoneyOffer" path="investmentMoneyOffer" />
	
	<acme:form-panel code="authenticated.application.form.label.investor">
	<acme:form-textbox code="authenticated.application.form.label.investor.username" path="investor.userAccount.username"/>
	<acme:form-textbox code="authenticated.application.form.label.investor.identity.name" path="investor.userAccount.identity.name"/>
	<acme:form-textbox code="authenticated.application.form.label.investor.identity.surname" path="investor.userAccount.identity.surname"/>
	<acme:form-textbox code="authenticated.application.form.label.investor.identity.email" path="investor.userAccount.identity.email"/>
	</acme:form-panel>
	
	<acme:form-return code="authenticated.application.form.button.return"/>
	

</acme:form>

	
