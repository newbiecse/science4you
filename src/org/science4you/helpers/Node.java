package org.science4you.helpers;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private int id;
	private String text;
	private List<SubNode> children;
	
	private String icon = "+";
	private Object li_attr = new Object();
	private Object a_attr = new Object();	
	
	public Node(int id, String text) {
		this.id = id;
		this.text = text;
		this.children = new ArrayList<SubNode>();
	}
	
	public Node(int id, String text, List<SubNode> children) {
		this.id = id;
		this.text = text;
		this.children = children;
	}
	
	public void addChild(SubNode node) {
		if (this.children == null) {
			this.children = new ArrayList<SubNode>();
		}
		
		this.children.add(node);
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
	
	public List<SubNode> getChildren() {
		return children;
		
	}
	public void setChildren(List<SubNode> children) {
		this.children = children;
	}
}
