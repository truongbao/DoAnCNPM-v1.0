package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.define;
import library.LibraryAuth;
import model.bean.Cat;
import model.bean.DeTai;
import model.dao.CatDAO;
import model.dao.UserDAO;

public class ListDeTaiFollowGiangVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListDeTaiFollowGiangVienController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
		UserDAO objDAO = new UserDAO();
		int idUser = Integer.parseInt(request.getParameter("uid"));
		ArrayList<DeTai> listDeTai= objDAO.getListDeTaiByIdUserCanCreateHopDong(idUser);
		PrintWriter out = response.getWriter();
		if (listDeTai!=null && listDeTai.size()>0) {
			
			out.println("<select name='de_tai' class='form-control border-input'>");
		for(int i = 0; i < listDeTai.size(); i++) {
			out.println("<option value="+ listDeTai.get(i).getIdDeTai()+">"+listDeTai.get(i).getTenDeTai()+"___"+ listDeTai.get(i).getMaSoDeTai()+"</option>");
		}
		out.println("</select>");
		}
		else {
			out.println("<select name='de_tai' class='form-control border-input'>Khong co de tai!</select>");
		}
        
		
	}

}
