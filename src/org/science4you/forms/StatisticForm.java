package org.science4you.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.science4you.beans.Group;

public class StatisticForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	
	private List<Group> groupList;
	

	private String dispatch;

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

}