<%@page language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<h3>
	 <acme:message code="administrator.chart.totalNumberTechnologiesGroupByActivitySector"/>
</h3>
</br>
<div>
	<canvas id="canvas1" style="height: 260px; width: 70%;"></canvas>
</div>

<h3>
	 <acme:message code="administrator.chart.ratioOpensourceTechnologiesVersusClosedsourceTechnologies"/>
</h3>
</br>
 <div >
	<canvas id="canvas2pie" style="height: 370px; width: 100%;"></canvas>
</div> 
<h3>
	 <acme:message code="administrator.chart.totalNumberToolGroupByActivitySector"/>
</h3>
</br>
 <div >
	<canvas id="canvas3" style="height: 370px; width: 100%;"></canvas>
</div> 
<h3>
	 <acme:message code="administrator.chart.ratioOpensourceToolsVersusClosedsourceTools"/>
</h3>
</br>
 <div >
	<canvas id="canvas4pie" style="height: 370px; width: 100%;"></canvas>
</div> 

<h3>
	 <acme:message code="administrator.chart.chart5"/>
</h3>
</br>
 <div >
	<canvas id="canvas5pie" style="height: 370px; width: 100%;"></canvas>
</div>

<h3>
	 <acme:message code="administrator.chart.chart6"/>
</h3>
</br>
 <div >
	<canvas id="canvas6pie" style="height: 370px; width: 100%;"></canvas>
</div>
 
<acme:form-return code="administrator.chart.form.button.return"/>

<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels : [	<jstl:forEach var="i" items="${activitySectorTechnologiesGroupByActivitySector}">"<jstl:out value='${i[0]}'/>",</jstl:forEach>
						<jstl:forEach var="i" items="${totalNumberTechnologiesGroupByActivitySector}">"<jstl:out value='${i[0]}'/>",</jstl:forEach>
					],
			datasets : [
				{
					backgroundColor:"#AED6F1",
					label:"<acme:message code='administrator.chart.form.label.totalTechnologyPerSector'/>",
					data :[	<jstl:forEach var="i" items="${activitySectorTechnologiesGroupByActivitySector}"><jstl:out value='${i[0]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${totalNumberTechnologiesGroupByActivitySector}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
						]
				}
			]
		};
		
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0,
							suggestedMax : 20
						}
					}
				]
			},
			legend : {
				display: true
			}
		};
	
		var canvas, context;
		canvas=document.getElementById("canvas1");
		context=canvas.getContext("2d");
		new Chart( context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
</script>



<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				"OpenSource","ClosedSource"
			],
			datasets : [
				{
					backgroundColor: [ "#AEB6BF", "#A9CCE3" ],
					data : [ <jstl:out value='${ratioOpensourceTechnologiesVersusTotalTechnologies}'/>, <jstl:out value='${ratioClosedsourceTechnologiesVersusTotalTechnologies}'/> ]
				}
			]
		};
		var options = {
			legend : {
				display : true
			}
		};
		var canvas, context;
		canvas = document.getElementById("canvas2pie");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>


<script type="text/javascript">
	$(document).ready(function(){
		var data = {
			labels : [	<jstl:forEach var="i" items="${activitySectorToolsGroupByActivitySector}">"<jstl:out value='${i[0]}'/>",</jstl:forEach>
						<jstl:forEach var="i" items="${totalNumberToolGroupByActivitySector}">"<jstl:out value='${i[0]}'/>",</jstl:forEach>
					],
			datasets : [
				{
					backgroundColor:"#AED6F1",
					label:"<acme:message code='administrator.chart.form.label.totalToolPerSector'/>",
					data :[	<jstl:forEach var="i" items="${activitySectorToolsGroupByActivitySector}"><jstl:out value='${i[0]}'/>,</jstl:forEach>
							<jstl:forEach var="i" items="${totalNumberToolGroupByActivitySector}"><jstl:out value='${i[1]}'/>,</jstl:forEach>
						]
				}
			]
		};
		
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0,
							suggestedMax : 20
						}
					}
				]
			},
			legend : {
				display: true
			}
		};
	
		var canvas, context;
		canvas=document.getElementById("canvas3");
		context=canvas.getContext("2d");
		new Chart( context, {
			type : "bar",
			data : data,
			options : options
		});
	});
	
</script>



<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				"OpenSource","ClosedSource"
			],
			datasets : [
				{
					backgroundColor: [ "#AEB6BF", "#A9CCE3" ],
					data : [ <jstl:out value='${ratioOpensourceToolsVersusTotalTools}'/>, <jstl:out value='${ratioClosedsourceToolsVersusTotalTools}'/> ]
				}
			]
		};
		var options = {
			legend : {
				display : true
			}
		};
		var canvas, context;
		canvas = document.getElementById("canvas4pie");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>


<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				"Seed","Angel","Series-A","Series-B","Series-C","Bridge"
			],
			datasets : [
				{
					backgroundColor: [ "#AEB6BF", "#A9CCE3", "orange", "blue" , "green" , "red"  ],
					data : [ <jstl:out value='${ratioSeedInvestmentRoundGroupByKind}'/>, <jstl:out value='${ratioAngelInvestmentRoundGroupByKind}'/>, <jstl:out value='${ratioSeriesAInvestmentRoundGroupByKind}'/>
					
						,<jstl:out value='${ratioSeriesBInvestmentRoundGroupByKind}'/>, <jstl:out value='${ratioSeriesCInvestmentRoundGroupByKind}'/>, <jstl:out value='${ratioBridgeInvestmentRoundGroupByKind}'/>
					
					]
				}
			]
		};
		var options = {
			legend : {
				display : true
			}
		};
		var canvas, context;
		canvas = document.getElementById("canvas5pie");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>


<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				"Pending","Accepted","Rejected"
			],
			datasets : [
				{
					backgroundColor: [ "#AEB6BF", "#A9CCE3", "orange" ],
					data : [ <jstl:out value='${ratioPendingApplicationsGroupByStatus}'/>, <jstl:out value='${ratioAcceptedApplicationsGroupByStatus}'/>, <jstl:out value='${ratioRejectedApplicationsGroupByStatus}'/> ]
				}
			]
		};
		var options = {
			legend : {
				display : true
			}
		};
		var canvas, context;
		canvas = document.getElementById("canvas6pie");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>

