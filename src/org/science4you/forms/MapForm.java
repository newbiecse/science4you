package org.science4you.forms;

import org.apache.struts.action.ActionForm;
import org.science4you.helpers.NodeType;

public class MapForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String specieId;
	
	private String groupName;
	
	private String genusName;
	

	private String dispatch;

	public String getDispatch() {
		return dispatch;
	}

	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}

	public String getSpecieId() {
		return specieId;
	}

	public void setSpecieId(String specieId) {
		this.specieId = specieId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGenusName() {
		return genusName;
	}

	public void setGenusName(String genusName) {
		this.genusName = genusName;
	}

}