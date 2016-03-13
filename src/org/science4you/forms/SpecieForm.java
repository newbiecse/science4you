package org.science4you.forms;

import org.apache.struts.action.ActionForm;
import org.science4you.helpers.NodeType;

public class SpecieForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String name;
	
	private String type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	private String dispatch;

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}