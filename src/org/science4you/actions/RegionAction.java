package org.science4you.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.science4you.forms.SpecieForm;
import org.science4you.helpers.Node;
import org.science4you.helpers.NodeType;
import org.science4you.helpers.SubNode;
import org.science4you.utils.StringUtil;

import com.google.gson.Gson;

public class RegionAction extends DispatchAction {

	public ActionForward goToSearchPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("search");
	}	
	
	public ActionForward searchGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("application/json");
		PrintWriter out = null;
		
		String sId = request.getParameter("id");
		int id = 0;
		
		if (sId != null && StringUtil.isInteger(sId)) {
			id = Integer.parseInt(sId);
		}
		
		try {			

			List<Node> tree = new ArrayList<Node>();
			
			if (id == 0) {
				Node root = new Node(1, "Root node", NodeType.ROOT);
				root.addChild(new SubNode(2, "Group 1", NodeType.GROUP, true));
				root.addChild(new SubNode(3, "Group 2", NodeType.GROUP, false));	
				
				tree.add(root);
			} else {
				
				SpecieForm specieForm = (SpecieForm) form;
				NodeType type = NodeType.valueOf(specieForm.getType());
				
				if (NodeType.GROUP == type) {

					Node groupNode = new Node(id * 10, String.format("Group %d", id * 10), NodeType.ORDER);
					groupNode.addChild(new SubNode(id * 10 + 1, String.format("Order %d", id * 10 + 1), NodeType.FAMILY, true));
					groupNode.addChild(new SubNode(id * 10 + 2, String.format("Order %d", id * 10 + 2), NodeType.FAMILY, false));
					
					tree.add(groupNode);
				} else if (NodeType.ORDER == type) {

					Node orderNode = new Node(id * 10, String.format("Order %d", id * 10), NodeType.FAMILY);
					orderNode.addChild(new SubNode(id * 10 + 1, String.format("Family %d", id * 10 + 1), NodeType.GENUS, true));
					orderNode.addChild(new SubNode(id * 10 + 2, String.format("Family %d", id * 10 + 2), NodeType.FAMILY, false));
					
					tree.add(orderNode);
				} else if (NodeType.FAMILY == type) {

					Node familyNode = new Node(id * 10, String.format("Family %d", id * 10), NodeType.GENUS);
					familyNode.addChild(new SubNode(id * 10 + 1, String.format("Genus %d", id * 10 + 1), NodeType.SPECIE, true));
					familyNode.addChild(new SubNode(id * 10 + 2, String.format("Genus %d", id * 10 + 2), NodeType.SPECIE, false));
					
					tree.add(familyNode);
				} else if (NodeType.GENUS == type) {

					Node genusNode = new Node(id * 10, String.format("Genus %d", id * 10), NodeType.GENUS);
					genusNode.addChild(new SubNode(id * 10 + 1, String.format("Specie %d", id * 10 + 1), NodeType.SPECIE, true));
					genusNode.addChild(new SubNode(id * 10 + 2, String.format("Specie %d", id * 10 + 2), NodeType.SPECIE, false));
					
					tree.add(genusNode);
				} 
			}

			String json = new Gson().toJson(tree);
			out = response.getWriter();
			out.write(json.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

		return null;
	}
}