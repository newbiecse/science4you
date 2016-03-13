package org.science4you.forms;

import org.apache.struts.action.ActionForm;

public class RegionForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;

	private String dispatch;

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}
}