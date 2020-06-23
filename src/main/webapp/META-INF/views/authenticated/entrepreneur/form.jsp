<%--
- form.jsp
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

<acme:form>
	<acme:form-textbox code="authenticated.entrepreneur.form.label.startupName" path="startupName"/>
		<acme:form-select code="authenticated.entrepreneur.form.label.activitySector" path="activitySector">	
			<jstl:forEach items="${words}" var="word">
				<acme:form-option code="${word}" value="${word}" selected="${activitySector==word}"/>
			</jstl:forEach>
		</acme:form-select>
		<acme:form-textarea code="authenticated.entrepreneur.form.label.qualificationRecord" path="qualificationRecord"/>
	<acme:form-textarea code="authenticated.entrepreneur.form.label.skillsRecord" path="skillsRecord"/>
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.entrepreneur.form.button.create" action="/authenticated/entrepreneur/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.entrepreneur.form.button.update" action="/authenticated/entrepreneur/update"/>
	<acme:form-return code="authenticated.entrepreneur.form.button.return"/>
</acme:form>
