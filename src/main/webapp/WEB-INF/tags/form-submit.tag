<%--
- form-submit.tag
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.

Modificado por Reme Dans
--%>

<%@tag language="java" body-content="empty"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
 
<%@attribute name="test" required="false" type="java.lang.Boolean"%>
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="method" required="false" type="java.lang.String"%>
<%@attribute name="action" required="true" type="java.lang.String"%>
<%@attribute name="access" required="false" type="java.lang.String"%>

<jstl:if test="${method == null}">
	<jstl:set var="method" value="post"/>
</jstl:if>

<jstl:if test="${access == null}">
	<jstl:set var="access" value="true"/>
</jstl:if>

<jstl:if test="${test == null || test == true}">
	<jstl:choose>
		<jstl:when test="${method == 'get'}">
		<security:authorize access="${access}">	
			<button type="button" formmethod="get" onclick="javascript: redirect('${action}')" class="btn btn-primary">
				<acme:message code="${code}"/>
			</button>	
		</security:authorize>	
		</jstl:when>
		
		<jstl:otherwise>
			<button type="submit" formmethod="post" onclick="javascript: form.action = getAbsoluteUrl('${action}')" class="btn btn-primary">
				<acme:message code="${code}"/>
			</button>
		</jstl:otherwise>
	</jstl:choose>	
</jstl:if>



