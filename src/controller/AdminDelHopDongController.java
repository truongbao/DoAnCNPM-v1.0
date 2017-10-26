package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import model.bean.HopDong;
import model.bean.User;
import model.dao.HopDongDAO;


public class AdminDelHopDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminDelHopDongController() {
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
	    
	    
	    HopDongDAO objDAO = new HopDongDAO();
	    
	    int idHopDong = Integer.parseInt(request.getParameter("uid"));
	    
		
//		//kiem tra usercos phai la admin dang dang nhap ko
//		//neu phai thi ko xoa còn neu ko Xóa đi
//		HttpSession session = request.getSession();
//		User objLogAdmin = (User) session.getAttribute("sobjUserAdmin");
//		if(idUser ==  objLogAdmin.getIdUser()){
//			  //ko xóa
//			  response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=1");
//			  return;
//		 }else{			  
			    if( objDAO.delItem(idHopDong) > 0){
					//xóa thanh cong
					response.sendRedirect(request.getContextPath() + "/admin/hopdong/index?msg=1");
					return; 
				}else{
					//them that bai
					response.sendRedirect(request.getContextPath() + "/admin/hopdong/index?msg=0");
					return; 
				}
			 
//		     }
			 
		 }
}
