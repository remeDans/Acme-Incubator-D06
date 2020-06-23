
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
	<input name = "investmentRound" type="hidden"/>
	</jstl:if>

	<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title" />
	<acme:form-moment code="entrepreneur.activity.form.label.startMoment" path="startMoment"/>
	<acme:form-moment code="entrepreneur.activity.form.label.endMoment" path="endMoment"/>
	<acme:form-money code="entrepreneur.activity.form.label.budget" path="budget" />

	<acme:form-submit test="${command == 'create'}" code="entrepreneur.activity.form.button.create" action="/entrepreneur/activity/create?roundId=${roundId}" />
	
	<acme:form-submit test="${investmentRoundIsDRAFT && ((command == 'delete')|| (command == 'show' ))}" code="entrepreneur.activity.form.button.delete" action="/entrepreneur/activity/delete" />
	<acme:form-submit test="${investmentRoundIsDRAFT && ((command == 'update') ||(command == 'show')) }" code="entrepreneur.activity.form.button.update" action="/entrepreneur/activity/update" />
	
	<acme:form-return code="entrepreneur.activity.form.button.return"/>

</acme:form>


