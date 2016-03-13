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
			List<SubNode> tree1 = new ArrayList<SubNode>();
			
			String json = "";
			
			if (id == 0) {
				Node root = new Node(1, "Select specie", NodeType.ROOT);
				root.addChild(new SubNode(2, "Group 1", NodeType.GROUP, true));
				root.addChild(new SubNode(3, "Group 2", NodeType.GROUP, true));	
				
				tree.add(root);
				json = new Gson().toJson(tree);
			} else {
				
				SpecieForm specieForm = (SpecieForm) form;
				NodeType type = NodeType.valueOf(specieForm.getType());
				
				if (NodeType.GROUP == type) {

					tree1.add(new SubNode(id * 10 + 1, String.format("Order %d", id * 10 + 1), NodeType.ORDER, true));
					tree1.add(new SubNode(id * 10 + 2, String.format("Order %d", id * 10 + 2), NodeType.ORDER, true));

					json = new Gson().toJson(tree1);
				} else if (NodeType.ORDER == type) {				
					
					tree1.add(new SubNode(id * 10 + 1, String.format("Family %d", id * 10 + 1), NodeType.FAMILY, true));
					tree1.add(new SubNode(id * 10 + 2, String.format("Family %d", id * 10 + 2), NodeType.FAMILY, true));
					
					json = new Gson().toJson(tree1);
				} else if (NodeType.FAMILY == type) {

					tree1.add(new SubNode(id * 10 + 1, String.format("Genus %d", id * 10 + 1), NodeType.GENUS, true));
					tree1.add(new SubNode(id * 10 + 2, String.format("Genus %d", id * 10 + 2), NodeType.GENUS, true));
					
					json = new Gson().toJson(tree1);					
				} else if (NodeType.GENUS == type) {

					tree1.add(new SubNode(id * 10 + 1, String.format("Specie %d", id * 10 + 1), NodeType.SPECIE, false));
					tree1.add(new SubNode(id * 10 + 2, String.format("Specie %d", id * 10 + 2), NodeType.SPECIE, false));
					
					json = new Gson().toJson(tree1);					
				} 
			}
			
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