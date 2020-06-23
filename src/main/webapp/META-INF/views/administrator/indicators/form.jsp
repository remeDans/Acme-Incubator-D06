<%@page language="java"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<!--<acme:form readonly="true">-->


<h2>
	<acme:message code="administrator.dashboard.form.title.applications-per-day.title" />
</h2>

<div>
	<canvas id="applications-per-day-canvas"></canvas>
</div>
<!--</acme:form>-->

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			datasets : [
				{
					label: "<acme:message code='administrator.dashboard.form.label.application-pending' />",
					borderColor:"#7F8C8D",
					backgroundColor:"#00000000",
					data : [
						<jstl:forEach var="i" items="${pendingApplicationsPerDay}">
							{t: new Date("<fmt:formatDate value='${i[0]}' pattern='yyyy-MM-dd'/>"), y: <jstl:out value='${i[1]}'/>},
						</jstl:forEach>
					]
				},
				{
					label: "<acme:message code='administrator.dashboard.form.label.application-accepted' />",
					borderColor:"#2ECC71",
					backgroundColor:"#00000000",
					data : [
						<jstl:forEach var="i" items="${acceptedApplicationsPerDay}">
							{t: new Date("<fmt:formatDate value='${i[0]}' pattern='yyyy-MM-dd'/>"), y: <jstl:out value='${i[1]}'/>},
						</jstl:forEach>
					]
				},
				{
					label: "<acme:message code='administrator.dashboard.form.label.application-rejected' />",
					borderColor:"#E74C3C",
					backgroundColor:"#00000000",
					data : [
						<jstl:forEach var="i" items="${rejectedApplicationsPerDay}">
							{t: new Date("<fmt:formatDate value='${i[0]}' pattern='yyyy-MM-dd'/>"), y: <jstl:out value='${i[1]}'/>},
						</jstl:forEach>
					]
				}
			]
		};

		var fifteenDaysAgo = new Date();
		fifteenDaysAgo.setDate(fifteenDaysAgo.getDate() - 15);
		
		var options = {
			scales: {
				xAxes: [{
					type: "time",
			        distribution: "linear",
			        time: {
	                    min: fifteenDaysAgo,
	                    max: new Date(),
	                    unit: "day",
	                    tooltipFormat: "ll"
	                }
				}],
				yAxes : [{
					ticks : {
						suggestedMin: 0,
						stepSize: 1
					}
				}]
			},
			legend : {
				display : true
			},
			tooltips: {
				mode: "x",
				intersect: false
	        }
		};

		var canvas, context;
		canvas = document.getElementById("applications-per-day-canvas");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "line",
			data : data,
			options : options
		});
	});
</script>