package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;

public class AdminShowAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminShowAddUserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//kiểm tra đã đăng nhập chưa
//				if(  LibraryAuth.CheckLogin(request, response)==false){
//					return;
//				}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/users/add.jsp");
		rd.forward(request, response);
	}

}
