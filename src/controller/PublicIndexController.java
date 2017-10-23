package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.UserDAO;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//lay danh sach giang vien
		//noi bang user + hocvi
		
		UserDAO userDAO = new UserDAO();
		
		ArrayList<User> listUser = new ArrayList<User>();
		
		if(listUser != null){
			  listUser = userDAO.getListGiangVien();
		}
		
		request.setAttribute("listUser", listUser);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
         rd.forward(request, response);
         
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        
		
	}
	
	

	
	
}
