
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form >

	<jstl:if test="${command == 'show' || command == 'update'}">
		<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" readonly="true"/>
	</jstl:if>

	<jstl:if test="${command == 'create'}" >
	<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker" placeholder="SSS-YY-NNNNNN" readonly="false"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create'}" >
	<acme:form-moment code="entrepreneur.investment-round.form.label.creationMoment" path="creationMoment" readonly="true"/>

	</jstl:if>
	<acme:form-moment code="entrepreneur.investment-round.form.label.deadline" path="deadline" readonly="false"/>
	<!--<acme:form-textbox code="entrepreneur.investment-round.form.label.kindOfRound" path="kindOfRound" />-->
	<acme:form-select  code="entrepreneur.investment-round.form.label.kindOfRound" path="kindOfRound" readonly="false" >
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.---" value="" />
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.SEED" value="SEED" selected="${kindOfRound == 'SEED'}" />
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.ANGEL" value="ANGEL" selected="${kindOfRound == 'ANGEL'}"/>
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.SERIES-A" value="SERIES-A" selected="${kindOfRound == 'SERIES-A'}"/>
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.SERIES-B" value="SERIES-B" selected="${kindOfRound == 'SERIES-B'}"/>
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.SERIES-C" value="SERIES-C" selected="${kindOfRound == 'SERIES-C'}"/>
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.BRIDGE" value="BRIDGE" selected="${kindOfRound == 'BRIDGE'}"/>
		</acme:form-select>
	
	<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title" />
	<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description" />
	
		<acme:form-select  code="entrepreneur.investment-round.form.label.status" path="status" readonly="false" >
			<acme:form-option code="entrepreneur.investment-round.form.label.kindOfRound.---" value="" />
			<acme:form-option code="entrepreneur.investment-round.form.label.status.DRAFT" value="DRAFT" selected="${status == 'DRAFT'}" />
			<acme:form-option code="entrepreneur.investment-round.form.label.status.PUBLISHED" value="PUBLISHED" selected="${status == 'PUBLISHED'}"/>
		</acme:form-select>
	
	<acme:form-url code="entrepreneur.investment-round.form.label.link" path="link"/>

	<acme:form-money code="entrepreneur.investment-round.form.label.amountOfMoney" path="amountOfMoney" />


<jstl:if test="${command != 'create'}" >
	<acme:form-panel code="entrepreneur.investment-round.form.label.entrepreneur">
		<acme:form-textbox code="entrepreneur.investment-round.form.label.entrepreneur.userAccount.username" path="entrepreneur.userAccount.username" readonly="true"/>
		<acme:form-textbox code="entrepreneur.investment-round.form.label.entrepreneur.userAccount.identity.name" path="entrepreneur.userAccount.identity.name" readonly="true"/>
		<acme:form-textbox code="entrepreneur.investment-round.form.label.entrepreneur.userAccount.identity.surname" path="entrepreneur.userAccount.identity.surname" readonly="true"/>
		<acme:form-textbox code="entrepreneur.investment-round.form.label.entrepreneur.userAccount.identity.email" path="entrepreneur.userAccount.identity.email" readonly="true"/>
	</acme:form-panel>
	
	<acme:form-submit test="${(!hasApplication) && ((command == 'delete') || (command == 'show' ))}" code="entrepreneur.investment-round.form.button.delete" action="/entrepreneur/investment-round/delete" />
	<acme:form-submit test="${(status == 'DRAFT') && ((command == 'update') ||(command == 'show')) }" code="entrepreneur.investment-round.form.button.update" action="/entrepreneur/investment-round/update" />
	
	<acme:form-submit test="${hasActivities}" method="get" code="entrepreneur.investment-round.form.button.workProgramme" action="/entrepreneur/activity/LIST_ACTIVITY_INVESTMENT_ROUND?id=${id}" />
	<!--<acme:form-submit test="${hasAccountingRecords}" method="get" code="entrepreneur.investment-round.form.button.accounting-records" action="/entrepreneur/accounting-record/LIST_ACCOUNTING_RECORD_INVESTMENT_ROUND?investmentId=${id}" />-->
	<acme:form-submit test="${hasForum}" method="get" code="entrepreneur.investment-round.form.button.forum" action="/entrepreneur/forum/SHOW_FORUM_INVESTMENT_ROUND?investmentId=${id}" />
	
	<acme:form-submit test="${!hasActivities && (status == 'DRAFT')}" method="get" code="entrepreneur.investment-round.form.button.workProgramme.create" action="/entrepreneur/activity/create?roundId=${id}" />
	<acme:form-submit test="${!isCompletedSumActivitiesMoney && hasActivities && (status == 'DRAFT')}" method="get" code="entrepreneur.activity.form.button.create" action="/entrepreneur/activity/create?roundId=${id}" />

</jstl:if>

	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investment-round.form.button.create" action="/entrepreneur/investment-round/create" />
	<acme:form-return code="entrepreneur.investment-round.form.button.return"/>

</acme:form>
