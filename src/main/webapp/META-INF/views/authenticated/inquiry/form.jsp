
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
	<acme:form-textbox code="authenticated.inquiry.form.label.title" path="title" />
	<acme:form-moment code="authenticated.inquiry.form.label.creationMoment" path="creationMoment"/>
	<acme:form-moment code="authenticated.inquiry.form.label.deadline" path="deadline" />
	<acme:form-textarea code="authenticated.inquiry.form.label.paragraphsDescription" path="paragraphsDescription" />
	<acme:form-textbox code="authenticated.inquiry.form.label.contactEmail" path="contactEmail" />
	<acme:form-panel code="authenticated.inquiry.form.label.intervalMoney">
		<acme:form-money code="authenticated.inquiry.form.label.minIntervalMoney" path="minIntervalMoney"/>
		<acme:form-money code="authenticated.inquiry.form.label.maxIntervalMoney" path="maxIntervalMoney"/>
	</acme:form-panel>


	<acme:form-return code="authenticated.inquiry.form.button.return"/>

</acme:form>




