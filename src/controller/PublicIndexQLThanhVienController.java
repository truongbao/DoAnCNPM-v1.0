package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class PublicIndexQLThanhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexQLThanhVienController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html");
		 
		 UserDAO userDAO  = new UserDAO(); 
		 
		 //kiểm tra đã đăng nhập ở public chưa
	     if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		 }
		 
		    
	     HttpSession session = request.getSession();
	     User objUser = null;
         if(session.getAttribute("sobjUserPublic") != null){ 
        	 objUser =  (User) session.getAttribute("sobjUserPublic");
         }
        	     
	     request.setAttribute("ListThanhVienByIdUser", userDAO.getListThanhVienByIdUser(objUser.getIdUser() ) );
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/quanly_thanhvien.jsp");
	     rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
       
	}

}
