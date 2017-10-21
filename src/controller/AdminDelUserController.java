package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import model.bean.User;
import model.dao.UserDAO;


public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminDelUserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	   /* HttpSession session = request.getSession();
	    User userinfo =(User)session.getAttribute("sobjUser");
	    
	    
	    
	    UserDAO objDAO = new UserDAO();
	    
	    int idUser = Integer.parseInt(request.getParameter("uid"));
	    
	    //lay doi tuong ung voi uid
		User objUser = objDAO.getItem(idUser);
		
		//kiem tra username trong doi tuongj này có trung admin ko
		//neu trung thi ko xoa còn neu ko Xóa đi
		if("admin".equals(objUser.getUsername() )  ){
			  //ko xóa
			  response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=4");
			  return;
		 }else{
		     if( ("admin".equals(userinfo.getUsername())) ){
			  
			    if( objDAO.delItem(idUser) > 0){
					//xóa thanh cong
					response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=3");
					return; 
				}else{
					//them that bai
					response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=0");
					return; 
				}
			 
		     }else{
		    	 //chuyen hướng
		    	 response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=5");
			     return; 
		     }
			 
		 }
	    */
		
		
		
		
		
	}

}
