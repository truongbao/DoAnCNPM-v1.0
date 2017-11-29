package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import model.bean.ThanhVien;
import model.bean.User;
import model.dao.UserDAO;

public class PublicThongTinGVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicThongTinGVController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html");
		  
		   UserDAO userDAO  = new UserDAO(); 
		    
	     int idUserGV = Integer.parseInt(request.getParameter("uid"));
	     
	     //System.out.println("idusser : "+idUser);
	     
	     request.setAttribute("objUser", userDAO.getObjUser(idUserGV) );
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/thongtin_giangvien.jsp");
         rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
	}
	
	
	
	
	

}
