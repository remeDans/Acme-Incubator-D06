
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
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:form >

	<jstl:if test="${command == 'create'}" >
	<input name = "patron" type="hidden"/>
	</jstl:if>

	<acme:form-url code="patron.banner.form.label.picture" path="picture" />
	<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan"/>
	<acme:form-url code="patron.banner.form.label.targetUrl" path="targetUrl" />

<acme:form-submit test="${command == 'create'}" code="patron.banner.form.button.create" action="/patron/banner/create" />
<acme:form-submit test="${command == 'show'|| command == 'update'}" code="patron.banner.form.button.update" action="/patron/banner/update" />
<acme:form-submit test="${command == 'show'|| command == 'delete' }" code="patron.banner.form.button.delete" action="/patron/banner/delete" />

<jstl:if test="${command != 'create'}" >
<acme:form-submit test="${!hasCreditcardBanner}" code="patron.banner.form.button.creditCard.create" action="/patron/credit-card/create_creditcard_banner?banner_id=${id}" method="get"/>
<acme:form-submit test="${hasCreditcardBanner}" code="patron.banner.form.button.creditCard.show" action="/patron/credit-card/show_creditcard_banner?banner_id=${id}" method="get"/>
</jstl:if>
	<acme:form-return code="patron.banner.form.button.return"/>

</acme:form>

