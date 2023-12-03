package com.project.musicProject;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/FriendshipRequestCon")
public class FriendshipRequestCon extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	FriendshipDAO dao = new FriendshipDAO();
        response.setContentType("application/json");
        String action = request.getParameter("action");
        String userId = request.getParameter("userId");
        String friendId = request.getParameter("friendId");
        JSONObject jsonResponse = new JSONObject();
        System.out.println(action+userId+friendId);
        try {
            if ("send".equals(action)) {
                boolean success = dao.addFriendRequest(userId, friendId);
                jsonResponse.put("success", success);
            } else if ("delete".equals(action)) {
               
                boolean success = dao.removeFriendRequest(userId, friendId);
                jsonResponse.put("success", success);
            }
            
        } catch (SQLException e) {
            jsonResponse.put("error", e.getMessage());
        }
        
        response.getWriter().write(jsonResponse.toString());
    }

    
}