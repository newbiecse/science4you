package org.science4you.helpers;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private int id;
	private String text;
	private List<Node> children;
	
	public Node(int id, String text) {
		this.id = id;
		this.text = text;
		this.children = new ArrayList<Node>();
	}
	
	public Node(int id, String text, List<Node> children) {
		this.id = id;
		this.text = text;
		this.children = children;
	}
	
	public void addChild(Node node) {
		if (this.children == null) {
			this.children = new ArrayList<Node>();
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
	
	public List<Node> getChildren() {
		return children;
		
	}
	public void setChildren(List<Node> children) {
		this.children = children;
	}
}
