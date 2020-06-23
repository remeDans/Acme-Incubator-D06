
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
	<acme:form-textbox code="administrator.inquiry.form.label.title" path="title" readonly="false"/>
	<jstl:if test="${command != 'create'}" >
	<acme:form-moment code="administrator.inquiry.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>
	<acme:form-moment code="administrator.inquiry.form.label.deadline" path="deadline" readonly="false"/>
	<acme:form-textarea code="administrator.inquiry.form.label.paragraphsDescription" path="paragraphsDescription" readonly="false"/>
	<acme:form-textbox code="administrator.inquiry.form.label.contactEmail" path="contactEmail" readonly="false"/>
	<acme:form-panel code="administrator.inquiry.form.label.intervalMoney">
		<acme:form-money code="administrator.inquiry.form.label.minIntervalMoney" path="minIntervalMoney" readonly="false"/>
		<acme:form-money code="administrator.inquiry.form.label.maxIntervalMoney" path="maxIntervalMoney" readonly="false"/>
	</acme:form-panel>


	<acme:form-submit test="${command == 'create'}" code="administrator.inquiry.form.button.create" action="/administrator/inquiry/create" />

<acme:form-submit test="${command == 'show'}" code="administrator.inquiry.form.button.update" action="/administrator/inquiry/update" />
<acme:form-submit test="${command == 'show'}" code="administrator.inquiry.form.button.delete" action="/administrator/inquiry/delete" />
<acme:form-submit test="${command == 'update'}" code="administrator.inquiry.form.button.update" action="/administrator/inquiry/update" />
<acme:form-submit test="${command == 'delete'}" code="administrator.inquiry.form.button.delete" action="/administrator/inquiry/delete" />

	<acme:form-return code="administrator.inquiry.form.button.return"/>

</acme:form>




