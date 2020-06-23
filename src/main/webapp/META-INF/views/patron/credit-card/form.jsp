
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
	
	<acme:form-textbox code="patron.creditCard.form.label.holderName" path="holderName" />
	<acme:form-textbox code="patron.creditCard.form.label.number" path="number" />
		<acme:form-select  code="patron.creditCard.form.label.brand" path="brand" readonly="false" >
			<acme:form-option code="patron.creditCard.form.label.brand.---" value="" />
			<acme:form-option code="patron.creditCard.form.label.brand.VISA" value="VISA" selected="${brand == 'VISA'}" />
			<acme:form-option code="patron.creditCard.form.label.brand.MASTERCARD" value="MASTERCARD" selected="${brand == 'MASTERCARD'}"/>
			<acme:form-option code="patron.creditCard.form.label.brand.DISCOVER" value="DISCOVER" selected="${brand == 'DISCOVER'}"/>
			<acme:form-option code="patron.creditCard.form.label.brand.DINNERS" value="DINNERS" selected="${brand == 'DINNERS'}"/>
			<acme:form-option code="patron.creditCard.form.label.brand.AMEX" value="AMEX" selected="${brand == 'AMEX'}"/>
		</acme:form-select>
	<acme:form-textbox code="patron.creditCard.form.label.monthExpired" path="monthExpired" />
	<acme:form-textbox code="patron.creditCard.form.label.yearExpired" path="yearExpired" />
	<acme:form-textbox code="patron.creditCard.form.label.CVV" path="CVV" />

<acme:form-submit test="${command == 'create_creditcard_banner'}" code="administrator.creditCard.form.button.create.banner" action="/patron/credit-card/create_creditcard_banner?banner_id=${banner_id}"/>
<acme:form-submit test="${command == 'create_creditcard_patron'}" code="administrator.creditCard.form.button.create.patron" action="/patron/credit-card/create_creditcard_patron?patron_id=${patron_id}"/>

<acme:form-submit test="${command == 'show_creditcard_banner' || command == 'show_creditcard_patron' || command == 'update'}" code="administrator.creditCard.form.button.update" action="/patron/credit-card/update" />
<acme:form-submit test="${command == 'show_creditcard_banner' || command == 'show_creditcard_patron'|| command == 'delete'}" code="administrator.creditCard.form.button.delete" action="/patron/credit-card/delete" />

	<acme:form-return code="patron.creditCard.form.button.return"/>

</acme:form>

