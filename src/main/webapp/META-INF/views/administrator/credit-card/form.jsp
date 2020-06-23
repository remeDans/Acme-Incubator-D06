
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
	
	<acme:form-textbox code="administrator.creditCard.form.label.holderName" path="holderName" />
	<acme:form-textbox code="administrator.creditCard.form.label.number" path="number" />
	<acme:form-textbox code="administrator.creditCard.form.label.brand" path="brand" />
	<acme:form-textbox code="administrator.creditCard.form.label.monthExpired" path="monthExpired" />
	<acme:form-textbox code="administrator.creditCard.form.label.yearExpired" path="yearExpired" />
	<acme:form-textbox code="administrator.creditCard.form.label.CVV" path="CVV" />

	<acme:form-return code="administrator.creditCard.form.button.return"/>

</acme:form>

