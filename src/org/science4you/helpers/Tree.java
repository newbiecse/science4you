package org.science4you.helpers;

import java.util.ArrayList;
import java.util.List;

public class Tree {
	
	private List<Node> nodes;

	public Tree () {
		this.nodes = new ArrayList<Node>();
	}
	
	public Tree(Node node) {
		this.nodes = new ArrayList<Node>();
		this.nodes.add(node);
	}
	
	public Tree(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void add(Node node) {
		this.nodes.add(node);
	}
}
