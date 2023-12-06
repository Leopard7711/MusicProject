package com.project.musicProject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


@WebServlet("/MemberDeleteCon")
public class MemberDeleteCon extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      

		String userId= request.getParameter("userId");
        JSONObject jsonResponse = new JSONObject();

        if (userId != null) {
            MemberDAO dao = new MemberDAO();
            boolean success = dao.deleteMember(userId);
            
            FriendshipDAO friendDao= new FriendshipDAO();
            UserMusicListDAO userMusicDao = new UserMusicListDAO();
            
            try {
				friendDao.deleteAllFriendshipsOfMember(userId);
				userMusicDao.clearUserMusicList(userId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
            if (success) {
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Account deleted successfully");
                
            } else {
                jsonResponse.put("status", "fail");
                jsonResponse.put("message", "Account deletion failed");
            }

        } else {
            jsonResponse.put("status", "fail");
            jsonResponse.put("message", "User not logged in");
        }

        response.getWriter().write(jsonResponse.toString());
    }
}

