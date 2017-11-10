package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import model.bean.CapDeTai;
import model.dao.CapDeTaiDAO;


public class AdminDelCapDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminDelCapDeTaiController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckAdmin(request, response)==false){
					return;
				}
		
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");   
	    
	    
	    CapDeTaiDAO objDAO = new CapDeTaiDAO();
	    
	    int idCapDeTai = Integer.parseInt(request.getParameter("uid"));
	    
		
	  
			    if( objDAO.delItem(idCapDeTai) > 0){
					//xóa thanh cong
					response.sendRedirect(request.getContextPath() + "/admin/capdetai/index?msg=1");
					return; 
				}else{
					//them that bai
					response.sendRedirect(request.getContextPath() + "/admin/capdetai/index?msg=0");
					return; 
				}
			 
//		     }
			 
		 }
}
