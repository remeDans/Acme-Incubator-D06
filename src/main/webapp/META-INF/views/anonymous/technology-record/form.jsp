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
	<acme:form-textbox code="anonymous.technology-record.form.label.title" path="title" readonly="true"/>
	<acme:form-textbox code="anonymous.technology-record.form.label.activitySector" path="activitySector" readonly="true"/>
	<acme:form-textbox code="anonymous.technology-record.form.label.nameInventor" path="nameInventor" readonly="true"/>
	<acme:form-textarea code="anonymous.technology-record.form.label.description" path="description" readonly="true"/>
	<acme:form-url code="anonymous.technology-record.form.label.webSite" path="webSite" readonly="true"/>
	<acme:form-textbox code="anonymous.technology-record.form.label.contactEmail" path="contactEmail" readonly="true"/>
	<acme:form-checkbox code="anonymous.technology-record.form.label.openSource" path="openSource" readonly="true"/>
	<acme:form-textbox code="anonymous.technology-record.form.label.stars" path="stars" readonly="true"/>
	
	<!--<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create/"/>-->
  	<acme:form-return code="anonymous.technology-record.form.button.return"/>
</acme:form>


