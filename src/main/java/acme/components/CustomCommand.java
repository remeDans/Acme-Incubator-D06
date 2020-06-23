/*
 * CustomCommand.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.components;

import acme.framework.components.Command;

public enum CustomCommand implements Command {
	LIST_ACTIVE, LIST_ACTIVITY_INVESTMENT_ROUND, LIST_MINE, LIST_ACCOUNTING_RECORD_INVESTMENT_ROUND, SHOW_FORUM_INVESTMENT_ROUND, SHOW_APPLICATION_INVESTMENT_ROUND, LIST_MESSAGES_FORUM, LIST_APPLICATION_ENTREPRENEUR, LIST_NO_MINE, CREATE_CREDITCARD_BANNER, CREATE_CREDITCARD_PATRON, SHOW_CREDITCARD_BANNER, SHOW_CREDITCARD_PATRON
}
