package org.science4you.helpers;

public class SubNode {
	
	private String id;
	private String text;
	private Boolean children;
	
//	private String icon = "+";
	private Object data = new Object();
	private Object li_attr = new Object();
	private Object a_attr = new Object();
	private NodeType type;
	
	public SubNode(String id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public SubNode(String id, String text, NodeType type, Boolean children) {
		this.id = id;
		this.text = text;
		this.type = type;
		this.children = children;
	}
	
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
}