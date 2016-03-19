package org.science4you.forms;

import org.apache.struts.action.ActionForm;
import org.science4you.helpers.NodeType;

public class SpecieForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String text;
	
	private String type;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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