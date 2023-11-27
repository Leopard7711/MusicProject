package com.project.musicProject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;


@WebServlet("/MemberInfoGetCon")
public class MemberInfoGetCon extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        
        MemberDTO dto= (MemberDTO)session.getAttribute("MemberDTO");
        
        JSONObject json = new JSONObject();
        if (dto != null) {
            
            json.put("id", dto.getId());
            json.put("email", dto.getEmail());
            json.put("password", dto.getPassword());
            // 추가적으로 필요한 MemberDTO의 다른 필드들도 여기에 포함시키세요.
            json.put("result", "success");
            
        } else {
        	json.put("result", "fail");
        	
            
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json.toString());
    }
}
