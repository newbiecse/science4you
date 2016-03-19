package org.science4you.actions;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.science4you.datasources.Science4youContext;
import org.science4you.forms.MapForm;
import org.science4you.forms.SpecieForm;
import org.science4you.helpers.Node;
import org.science4you.helpers.NodeType;
import org.science4you.helpers.SubNode;

import com.google.gson.Gson;

public class RegionAction extends DispatchAction {

	public ActionForward goToSearchPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SpecieForm specieForm = (SpecieForm) form;
		
		if (specieForm.getSpecieId() != null && !specieForm.getSpecieId().isEmpty()) {
			
			Connection connection = Science4youContext.getConnection();
			Statement stmt = null;
			
			String query = String.format(
			"select n.ptname_id id, n.fullname peciename, n.gattung genusname, pt.dispgroup groupname " +
			"from vims2you.S4projecttaxa pt, vims2you.s4ptname n " + 
			"where pt.project_id = 503 and pt.ptname_id = %s and pt.ptname_id = n.ptname_id", specieForm.getSpecieId());
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);

	        if(rs.next()) {
	        	
	        	specieForm.setGroupName(rs.getString("groupname"));
	        	specieForm.setGenusName(rs.getString("genusname"));
        	}
		}
		
		return mapping.findForward("search");
	}	
	
	public ActionForward searchGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("application/json");
		PrintWriter out = null;
		
		SpecieForm specieForm = (SpecieForm) form;
		NodeType type = NodeType.valueOf(specieForm.getType());

		Connection connection = Science4youContext.getConnection();
		Statement stmt = null;
		String query = null;
		
		try {			
			List<Node> tree = new ArrayList<Node>();
			List<SubNode> tree1 = new ArrayList<SubNode>();
			String json = "";					
			
			if (NodeType.ROOT == type) {
				Node root = new Node(1, "Select specie", NodeType.ROOT);
				
				query = "select DISTINCT pt.dispgroup text " +
						"from vims2you.S4projecttaxa pt, vims2you.s4ptname n " +
						"where pt.project_id = 503 and pt.ptname_id = n.ptname_id " +
						"order by text";
				
				stmt = connection.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        int i = 0;
		        
		        while (rs.next()) {

		        	root.addChild(new SubNode(String.format("GR%d", i), 
		        			rs.getString("text"), NodeType.GROUP, true));
		            i++;
		        }				
								
				tree.add(root);
				
				json = new Gson().toJson(tree);
				
			} else if (NodeType.GROUP == type) {

				query = String.format(
				"select DISTINCT n.gattung text " +
				"from vims2you.S4projecttaxa pt, vims2you.s4ptname n " + 
				"where pt.project_id = 503 and pt.ptname_id = n.ptname_id and pt.dispgroup = '%s' " +
				"order by text", specieForm.getText());
				
				stmt = connection.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        int i = 0;
		        
		        while (rs.next()) {

		        	tree1.add(new SubNode(String.format("GN%d", i), 
		        			rs.getString("text"), NodeType.GENUS, true));
		            i++;
		        }
		        
				json = new Gson().toJson(tree1);
				
			} else if (NodeType.ORDER == type) {				
				// TODO:
			} else if (NodeType.FAMILY == type) {
				// TODO:
			} else if (NodeType.GENUS == type) {

				query = String.format(
				"select n.ptname_id id, n.fullname text " +
				"from vims2you.S4projecttaxa pt, vims2you.s4ptname n " + 
				"where pt.project_id = 503 and pt.ptname_id = n.ptname_id and n.gattung = '%s' " +
				"order by text", specieForm.getText());
				
				stmt = connection.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        
		        while (rs.next()) {

		        	tree1.add(new SubNode(rs.getString("id"), 
		        			rs.getString("text"), NodeType.SPECIE, false));
		        }
		        
				json = new Gson().toJson(tree1);
			} 
			
			out = response.getWriter();
			out.write(json.toString());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			out.flush();
			out.close();
		}

		return null;
	}
}