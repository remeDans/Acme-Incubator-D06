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
	<acme:form-textbox code="administrator.technology-record.form.label.title" path="title" readonly="false"/>
	<acme:form-select code="administrator.technology-record.form.label.activitySector" path="activitySector">	
		<jstl:forEach items="${words}" var="word">
			<acme:form-option code="${word}" value="${word}" selected="${activitySector==word}"/>
		</jstl:forEach>
	</acme:form-select>
	<acme:form-textbox code="administrator.technology-record.form.label.nameInventor" path="nameInventor" readonly="false"/>
	<acme:form-textarea code="administrator.technology-record.form.label.description" path="description" readonly="false"/>
	<acme:form-url code="administrator.technology-record.form.label.webSite" path="webSite" readonly="false"/>
	<acme:form-textbox code="administrator.technology-record.form.label.contactEmail" path="contactEmail" readonly="false"/>
	<acme:form-checkbox code="administrator.technology-record.form.label.openSource" path="openSource" readonly="false"/>
	<acme:form-integer code="administrator.technology-record.form.label.stars" path="stars" readonly="false"/>
	
	<acme:form-submit test="${command == 'create'}" code="administrator.technology-record.form.button.create" action="/administrator/technology-record/create" />
  	
  	<acme:form-submit test="${command == 'show'}" code="administrator.technology-record.form.button.update" action="/administrator/technology-record/update" />
<acme:form-submit test="${command == 'show'}" code="administrator.technology-record.form.button.delete" action="/administrator/technology-record/delete" />
<acme:form-submit test="${command == 'update'}" code="administrator.technology-record.form.button.update" action="/administrator/technology-record/update" />
<acme:form-submit test="${command == 'delete'}" code="administrator.technology-record.form.button.delete" action="/administrator/technology-record/delete" />
  	
  	<acme:form-return code="administrator.technology-record.form.button.return"/>
</acme:form>


