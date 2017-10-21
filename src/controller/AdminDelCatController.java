package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.Cat;
import model.dao.CatDAO;


public class AdminDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminDelCatController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*//kiểm tra đã đăng nhập chưa
		if(  LibraryAuth.CheckLogin(request, response)==false){
			return;
		}
		*/
		
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    CatDAO objDAO = new CatDAO();
	    
	    int idCat = Integer.parseInt(request.getParameter("cid"));
		
		
		if( objDAO.delItem(idCat) > 0){
			//xóa thanh cong
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=3");
			return; 
		}else{
			//them that bai
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=0");
			return; 
		}
		
		
	}

}
