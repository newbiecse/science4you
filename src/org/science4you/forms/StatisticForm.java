package org.science4you.forms;

import org.apache.struts.action.ActionForm;

public class StatisticForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String groupname;
	
	private String dispatch;

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

}