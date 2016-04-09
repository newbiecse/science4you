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
import org.science4you.beans.Group;
import org.science4you.beans.SpecieStatistic;
import org.science4you.datasources.Science4youContext;
import org.science4you.forms.SpecieForm;
import org.science4you.forms.StatisticForm;
import org.science4you.helpers.Node;
import org.science4you.helpers.NodeType;
import org.science4you.helpers.SubNode;

import com.google.gson.Gson;

public class StatisticAction extends DispatchAction {

	public ActionForward goToStatisticPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		StatisticForm statisticForm = (StatisticForm) form;
		
		Connection connection = Science4youContext.getConnection();
		Statement stmt = null;
		
		String query = 
		"select DISTINCT pt.dispgroup groupname " + 
		"from vims2you.S4projecttaxa pt " + 
		"where pt.project_id = 503";
		
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		List<Group> groupList = new ArrayList<Group>();
		
		int i = 1;
		
        while (rs.next()) {
        	// TODO: get groupId
        	groupList.add(new Group(i, rs.getString("groupname")));
        	i++;
        }				
		
        request.setAttribute("groupList", groupList);
        
		return mapping.findForward("statistic");
	}	
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		response.setContentType("application/json");
		PrintWriter out = null;
		
		StatisticForm statisticForm = (StatisticForm) form;
		String groupName = statisticForm.getGroupname();
		
		List<SpecieStatistic> specieList = new ArrayList<SpecieStatistic>();
			
		try {
			
			Connection connection = Science4youContext.getConnection();
			Statement stmt = null;
			
			String query = String.format(
			"select n.ptname_id id, n.fullname wissname, n.fullname trivialname, pt.watchlevel minimumlevel, pt.dispgroup sensitiv, pt.dispgroup groupname " + 
			"from vims2you.S4projecttaxa pt, vims2you.s4ptname n " + 
			"where pt.project_id = 503 and pt.ptname_id = n.ptname_id and pt.dispgroup='%s' " +
			"order by id", groupName);
			
			stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while (rs.next()) {

	        	specieList.add(new SpecieStatistic(
	        			rs.getInt("id"),
	        			rs.getString("wissname"),
	        			rs.getString("trivialname"),
	        			rs.getInt("minimumlevel"),
	        			rs.getString("sensitiv"),
	        			rs.getString("groupname")
        			));
	        	
	        }				
			
			String json = new Gson().toJson(specieList);
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