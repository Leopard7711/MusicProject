package com.project.musicProject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import org.json.JSONObject;

@WebServlet("/UserMusicListClearCon")
public class UserMusicListClearCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO) session.getAttribute("MemberDTO");

        JSONObject jsonResponse = new JSONObject();

        
        if (user == null) {
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "User not logged in");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

     
        String userId = user.getId();
  

        
        UserMusicListDAO dao = new UserMusicListDAO();
        boolean success = dao.clearUserMusicList(userId);

        if (success) {
            jsonResponse.put("status", "success");
            jsonResponse.put("message", "Music list cleared successfully");
        } else {
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "Failed to clear music list");
        }

        response.getWriter().write(jsonResponse.toString());
    }
}