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
import org.science4you.helpers.Node;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
		
		try {			

			Gson gson = new Gson();
			
			Node root = new Node(1, "Root node");
			root.addChild(new Node(2, "Child node 1"));
			root.addChild(new Node(3, "Child node 2"));
			
			List<Node> tree = new ArrayList<Node>();
			tree.add(root);
			
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