package org.science4you.helpers;

public class SubNode {
	
	private int id;
	private String text;
	private Boolean children;
	
	private String icon = "+";
	private Object li_attr = new Object();
	private Object a_attr = new Object();
	
	public SubNode(int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public SubNode(int id, String text, Boolean children) {
		this.id = id;
		this.text = text;
		this.children = children;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}	
}
