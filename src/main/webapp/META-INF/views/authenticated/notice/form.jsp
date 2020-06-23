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
	<acme:form-url code="authenticated.notice.form.label.headerPicture" path="headerPicture" readonly="true"/>
	<acme:form-textbox code="authenticated.notice.form.label.title" path="title" readonly="true"/>
		<acme:form-moment code="authenticated.notice.form.label.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-moment code="authenticated.notice.form.label.deadline" path="deadline" readonly="true"/>
		<acme:form-textarea code="authenticated.notice.form.label.body" path="body" readonly="true"/>
	<acme:form-textarea code="authenticated.notice.form.label.optionalLinks" path="optionalLinks" readonly="true"/>
	
	<!--<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create/"/>-->
  	<acme:form-return code="authenticated.notice.form.button.return"/>
</acme:form>

