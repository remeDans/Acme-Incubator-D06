
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
	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-textarea code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>
	
	<acme:form-moment code="entrepreneur.application.form.label.creationMoment" path="creationMoment" readonly="true"/>
	
		<acme:form-select code="entrepreneur.application.form.label.status" path="status" >
			<acme:form-option code="entrepreneur.application.form.label.status.pending" value="PENDING" selected="${status == 'PENDING'}"/>
			<acme:form-option code="entrepreneur.application.form.label.status.accepted" value="ACCEPTED" selected="${status == 'ACCEPTED'}"/>
			<acme:form-option code="entrepreneur.application.form.label.status.rejected" value="REJECTED" selected="${status == 'REJECTED'}"/>
		</acme:form-select>
		
		<jstl:if test="${(command == 'show' || status == 'REJECTED')}">
		<acme:form-textarea code="entrepreneur.application.form.label.justification" path="justification" />
		</jstl:if>
	
	<acme:form-money code="entrepreneur.application.form.label.investmentMoneyOffer" path="investmentMoneyOffer" readonly="true"/>
	
	<jstl:if test="${command != 'create'}" >
	<acme:form-panel code="entrepreneur.application.form.label.investor">
	<acme:form-textbox code="entrepreneur.application.form.label.investor.username" path="investor.userAccount.username" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.investor.identity.name" path="investor.userAccount.identity.name" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.investor.identity.surname" path="investor.userAccount.identity.surname" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.investor.identity.email" path="investor.userAccount.identity.email" readonly="true"/>
	</acme:form-panel>
	</jstl:if>
	
	<acme:form-submit test="${ (command == 'update' || command == 'show' )}" code="entrepreneur.application.form.button.update" action="/entrepreneur/application/update" />
	
	<acme:form-return code="entrepreneur.application.form.button.return"/>

</acme:form>

	
