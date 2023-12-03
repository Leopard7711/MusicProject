package com.project.musicProject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@WebServlet("/FriendshipApplyCon")
public class FriendshipApplyCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//s

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	FriendshipDAO dao = new FriendshipDAO();
	        response.setContentType("application/json");
	        String userId = request.getParameter("userId");
	        String friendId = request.getParameter("friendId");
	        JSONObject jsonResponse = new JSONObject();
	        
	        try {
	        	boolean success = dao.applyFriendship(userId, friendId);
	        	jsonResponse.put("success", success);
	        } catch (SQLException e) {
	            jsonResponse.put("error", e.getMessage());
	        }
	        
	        response.getWriter().write(jsonResponse.toString());
	    }


}
