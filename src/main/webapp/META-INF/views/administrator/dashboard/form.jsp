<%@page language="java" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true"> 
	<acme:form-textbox code="administrator.dashboard.totalNumberNotices" path="totalNumberNotices"/>
	<acme:form-textbox code="administrator.dashboard.totalNumberTechnologyRecords" path="totalNumberTechnologyRecords"/>
	<acme:form-textbox code="administrator.dashboard.totalNumberToolRecords" path="totalNumberToolRecords"/>
	
	<acme:form-textbox code="administrator.dashboard.minMoneyIntervalsActiveInquiries" path="minMoneyIntervalsActiveInquiries"/>
	<acme:form-textbox code="administrator.dashboard.maxMoneyIntervalsActiveInquiries" path="maxMoneyIntervalsActiveInquiries"/>
	<acme:form-textbox code="administrator.dashboard.avgMoneyIntervalsActiveInquiries" path="avgMoneyIntervalsActiveInquiries"/>
	<acme:form-textbox code="administrator.dashboard.desvMoneyIntervalsActiveInquiries" path="desvMoneyIntervalsActiveInquiries"/>
	
	<acme:form-textbox code="administrator.dashboard.minMoneyIntervalsActiveOvertures" path="minMoneyIntervalsActiveOvertures"/>
	<acme:form-textbox code="administrator.dashboard.maxMoneyIntervalsActiveOvertures" path="maxMoneyIntervalsActiveOvertures"/>
	<acme:form-textbox code="administrator.dashboard.avgMoneyIntervalsActiveOvertures" path="avgMoneyIntervalsActiveOvertures"/>
	<acme:form-textbox code="administrator.dashboard.desvMoneyIntervalsActiveOvertures" path="desvMoneyIntervalsActiveOvertures"/>
	
	<acme:form-textbox code="administrator.dashboard.avgNumberInventmentRoundPerEntrepreneur" path="avgNumberInventmentRoundPerEntrepreneur"/>
	<acme:form-textbox code="administrator.dashboard.avgNumberApplicationPerEntrepreneur" path="avgNumberApplicationPerEntrepreneur"/>
	<acme:form-textbox code="administrator.dashboard.avgNumberApplicationPerInvestor" path="avgNumberApplicationPerInvestor"/>

	<acme:form-return code="administrator.dashboard.form.button.return"/>
</acme:form>
