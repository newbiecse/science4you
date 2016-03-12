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
				Node root = new Node(1, "Root node");
				root.addChild(new SubNode(2, "Child node 1", true));
				root.addChild(new SubNode(3, "Child node 2"));	
				
				tree.add(root);
			} else {
				
				Node root = new Node(id * 10, String.format("Child node %d", id * 10));
				root.addChild(new SubNode(id * 10 + 1, String.format("Child node %d", id * 10 + 1), true));
				root.addChild(new SubNode(id * 10 + 2, String.format("Child node %d", id * 10 + 2)));	
				
				tree.add(root);				
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