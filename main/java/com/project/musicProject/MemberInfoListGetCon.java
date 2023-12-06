package com.project.musicProject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet("/MemberInfoListGetCon")
public class MemberInfoListGetCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberInfoListGetCon() {
        super();
       
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        MemberDAO dao = new MemberDAO();
        List<MemberDTO> members = dao.memberFindList();

        JSONArray jsonArray = new JSONArray();
        for (MemberDTO member : members) {
            JSONObject json = new JSONObject();
            json.put("id", member.getId());
            json.put("email", member.getEmail());
            json.put("password", member.getPassword());
            json.put("joinDate", member.getDatetime().toString());
            jsonArray.put(json);
        }

        response.getWriter().write(jsonArray.toString());
    }

}
