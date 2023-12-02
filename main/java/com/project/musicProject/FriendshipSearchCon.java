package com.project.musicProject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@WebServlet("/FriendshipSearchCon")
public class FriendshipSearchCon extends HttpServlet {
	
	private FriendshipDAO dao = new FriendshipDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String userId = request.getParameter("userId");
        JSONObject jsonResponse = new JSONObject();
        System.out.println("FriendshipSearchCon->"+userId);
        	
        try {
            List<FriendshipDTO> friendRequests = dao.searchFriendAndRequests(userId);
            jsonResponse.put("friendRequests", new org.json.JSONArray(friendRequests));
        } catch (SQLException e) {
            jsonResponse.put("error", e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }
}