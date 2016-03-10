package org.science4you.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RegionAction extends DispatchAction {

	public ActionForward goToSearchPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("search");
	}	
	
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("search");
	}

	
	public ActionForward getUserJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sUserId = request.getParameter("id");
		int userId = 0;
		
		if(sUserId != null) {
			try {
				userId = Integer.parseInt(sUserId);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
//		this.userDao = new UserDAO();
//		User user = this.userDao.findById(userId);
		response.setContentType("application/json");

		PrintWriter out = null;
		
		try {			

			
			Gson gson = new Gson();
			
//			JsonObject o = new JsonObject();
//			o.add("user", jsonElement);
			
//			String userJson = gson.toJson(user);
						
			out = response.getWriter();
			
//			out.write(userJson);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

		return null;
	}	
	
//	public ActionForward addUserJson(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		UserForm userForm = (UserForm) form;
//		this.userDao = new UserDAO();
//		
//		if(userForm.getId() != null) {
//			if(userForm.getId() > 0) {
//				this.userDao.update(userForm);
//			} else {
//				this.userDao.createUser(userForm);
//			}
//		}
//		
//		return null;
//	}
}