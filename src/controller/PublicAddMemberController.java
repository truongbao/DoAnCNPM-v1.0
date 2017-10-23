package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.User;
import model.dao.UserDAO;

public class PublicAddMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicAddMemberController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 //kiểm tra đã đăng nhập ở public chưa
	     if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		 }
	     
	     UserDAO userDAO  = new UserDAO(); 
		
		//gửi danh sách các thanh viên trong trường
	     ArrayList<User> listUserInSchool = new ArrayList<User>();
		
		 if(listUserInSchool != null){
			 listUserInSchool = userDAO.getListUserInSchool();
		 }
			
		 request.setAttribute("listUserInSchool", listUserInSchool);
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/add_member.jsp");
         rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        
	}

}
