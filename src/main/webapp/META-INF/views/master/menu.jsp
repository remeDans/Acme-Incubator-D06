<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.dans-link" action="https://taleeditor.wordpress.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.dans-bulletin.list" action="/anonymous/dans-bulletin/list"/>
			<acme:menu-suboption code="master.menu.anonymous.dans-bulletin.create" action="/anonymous/dans-bulletin/create"/>
			<acme:menu-suboption code="master.menu.anonymous.notice.list" action="/anonymous/notice/list"/>
			<acme:menu-suboption code="master.menu.anonymous.technology-record.list" action="/anonymous/technology-record/list"/>
			<acme:menu-suboption code="master.menu.anonymous.tool-record.list" action="/anonymous/tool-record/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.anonymous" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.notice.list" action="/authenticated/notice/list"/>
			<acme:menu-suboption code="master.menu.authenticated.technology-record.list" action="/authenticated/technology-record/list"/>
			<acme:menu-suboption code="master.menu.authenticated.tool-record.list" action="/authenticated/tool-record/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticathed" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.challenge.listActive" action="/authenticated/challenge/list-active" />
			<acme:menu-suboption code="master.menu.authenticated.inquiry.listActive" action="/authenticated/inquiry/list-active" />
			<acme:menu-suboption code="master.menu.authenticated.overture.listActive" action="/authenticated/overture/list-active" />
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.authenticated.investment-round.listActive" action="/authenticated/investment-round/list-active" />
			<acme:menu-suboption code="master.menu.authenticated.forum.listMine" action="/authenticated/forum/list_mine" />
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.notice.list" action="/administrator/notice/list"/>
			<acme:menu-suboption code="master.menu.administrator.notice.create" action="/administrator/notice/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.inquiry.list" action="/administrator/inquiry/list"/>
			<acme:menu-suboption code="master.menu.administrator.inquiry.create" action="/administrator/inquiry/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.overture.list" action="/administrator/overture/list"/>
			<acme:menu-suboption code="master.menu.administrator.overture.create" action="/administrator/overture/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.technology-record.list" action="/administrator/technology-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.technology-record.create" action="/administrator/technology-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.tool-record.list" action="/administrator/tool-record/list"/>
			<acme:menu-suboption code="master.menu.administrator.tool-record.create" action="/administrator/tool-record/create"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.challenge.list" action="/administrator/challenge/list"/>
			<acme:menu-suboption code="master.menu.administrator.challenge.create" action="/administrator/challenge/create"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.investor" access="hasRole('Investor')">
			<acme:menu-suboption code="master.menu.investor.application.listMine" action="/investor/application/list_mine"/>
		</acme:menu-option>
		
		
		<acme:menu-option code="master.menu.entrepreneur" access="hasRole('Entrepreneur')">
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.listMine" action="/entrepreneur/investment-round/list_mine"/>
			<acme:menu-suboption code="master.menu.entrepreneur.investment-round.create" action="/entrepreneur/investment-round/create"/>
			<acme:menu-suboption code="master.menu.entrepreneur.application.listMine" action="/entrepreneur/application/list_application_entrepreneur"/>
		</acme:menu-option>
		
		
		<acme:menu-option code="master.menu.bookkeeper" access="hasRole('Bookkeeper')">
			<acme:menu-suboption code="master.menu.bookkeeper.investment-round.listMine" action="/bookkeeper/investment-round/list_mine"/>
			<acme:menu-suboption code="master.menu.bookkeeper.investment-round.listNOMine" action="/bookkeeper/investment-round/list_no_mine"/>
		</acme:menu-option>


		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.patron" access="hasRole('Patron')">
				<acme:menu-suboption code="master.menu.patron.banner.list.mine" action="/patron/banner/list-mine"/>
				<acme:menu-suboption code="master.menu.patron.banner.create" action="/patron/banner/create"/>
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
	
		<acme:menu-option code="master.menu.system" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-suboption code="master.menu.administrator.customisation.show" action="/administrator/customisation/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard.show" action="/administrator/dashboard/show"/>
			<acme:menu-suboption code="master.menu.administrator.chart.show" action="/administrator/chart/show"/>
			<acme:menu-suboption code="master.menu.administrator.indicators.show" action="/administrator/indicators/show"/>
			<acme:menu-suboption code="master.menu.administrator.banner.list" action="/administrator/banner/list"/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		</acme:menu-option>
	
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>
		

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.administrator.list-bookkeeper-requests" action="/administrator/bookkeeper-request/list" access="hasRole('Administrator')"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-entrepreneur" action="/authenticated/entrepreneur/create" access="!hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.entrepreneur" action="/authenticated/entrepreneur/update" access="hasRole('Entrepreneur')"/>
			<acme:menu-suboption code="master.menu.user-account.become-investor" action="/authenticated/investor/create" access="!hasRole('Investor')"/>
			<acme:menu-suboption code="master.menu.user-account.investor" action="/authenticated/investor/update" access="hasRole('Investor')"/>
			
			<acme:menu-suboption code="master.menu.user-account.bookkeeper-request.create" action="/authenticated/bookkeeper-request/create" access="!hasRole('Bookkeeper')" />
			<acme:menu-suboption code="master.menu.user-account.bookkeeper" action="/authenticated/bookkeeper/update" access="hasRole('Bookkeeper')"/>
			
			<acme:menu-suboption code="master.menu.user-account.become-patron" action="/authenticated/patron/create" access="!hasRole('Patron')"/>
			<acme:menu-suboption code="master.menu.user-account.patron" action="/authenticated/patron/update" access="hasRole('Patron')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

