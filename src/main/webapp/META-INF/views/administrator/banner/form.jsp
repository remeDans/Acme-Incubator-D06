
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

<acme:form readonly="false">
	<acme:form-url code="administrator.banner.form.label.picture" path="picture" />
	<acme:form-textbox code="administrator.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="administrator.banner.form.label.targetUrl" path="targetUrl" />

<acme:form-submit test="${hasCreditcard}" code="administrator.banner.form.button.creditCard.show" action="/administrator/credit-card/show?banner_id=${id}" method="get"/>

	<acme:form-return code="administrator.banner.form.button.return"/>

</acme:form>

