package com.project.musicProject;

import java.io.IOException;
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
      

        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO) session.getAttribute("MemberDTO");

        JSONObject jsonResponse = new JSONObject();

        if (user != null) {
            MemberDAO dao = new MemberDAO();
            boolean success = dao.deleteMember(user.getId());

            if (success) {
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Account deleted successfully");
                session.invalidate(); // 사용자 세션 무효화
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

