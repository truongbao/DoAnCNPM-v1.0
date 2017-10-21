package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.StringLibrary;
import model.bean.Cat;
import model.bean.User;
import model.dao.CatDAO;
import model.dao.UserDAO;


public class AuthAdminLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AuthAdminLogoutController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("sobjUser")!=null){
			//xóa session
			session.removeAttribute("sobjUser");
			
		}
		response.sendRedirect(request.getContextPath()+"/home");//chuyen sang trang public
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
