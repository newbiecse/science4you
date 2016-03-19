package org.science4you.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		SpecieForm specieForm = (SpecieForm) form;
		NodeType type = NodeType.valueOf(specieForm.getType());

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		Connection connection = null;

		try {
			
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@s4ora.science4you.org:1521:s2orcl", "s2web",
					"s2web");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		Statement stmt = null;
		String query = null;
		
		try {			
			List<Node> tree = new ArrayList<Node>();
			List<SubNode> tree1 = new ArrayList<SubNode>();
			String json = "";
					
			if (NodeType.ROOT == type) {
				Node root = new Node(1, "Select specie", NodeType.ROOT);
				
				query = "select DISTINCT dispgroup text " +
						"from vims2you.S4projecttaxa pt, vims2you.s4ptname n " +
						"where pt.project_id = 503 and pt.ptname_id = n.ptname_id " +
						"order by text";
				
				stmt = connection.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        int i = 0;
		        
		        while (rs.next()) {
		            String text = rs.getString("text");
		            root.addChild(new SubNode(String.format("GR%d", i), text, NodeType.GROUP, true));
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
		            String text = rs.getString("text");
		            tree1.add(new SubNode(String.format("GN%d", i), text, NodeType.GENUS, true));
		            i++;
		        }
		        
				json = new Gson().toJson(tree1);
				
			} else if (NodeType.ORDER == type) {				
				// TODO:
			} else if (NodeType.FAMILY == type) {
				// TODO:
			} else if (NodeType.GENUS == type) {

				query = String.format(
				"select DISTINCT n.fullname text " +
				"from vims2you.S4projecttaxa pt, vims2you.s4ptname n " + 
				"where pt.project_id = 503 and pt.ptname_id = n.ptname_id and n.gattung = '%s' " +
				"order by text", specieForm.getText());
				
				stmt = connection.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        int i = 0;
		        
		        while (rs.next()) {
		            String text = rs.getString("text");
		            tree1.add(new SubNode(String.format("SP%d", i), text, NodeType.GENUS, true));
		            i++;
		        }
		        
				json = new Gson().toJson(tree1);
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