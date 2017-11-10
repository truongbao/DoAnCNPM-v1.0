package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.StringLibrary;
import model.bean.CapDeTai;
import model.dao.CapDeTaiDAO;;


public class AdminAddCapDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminAddCapDeTaiController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckAdmin(request, response)==false){
					return;
				}
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
	    
		String name = request.getParameter("name");
		CapDeTai objCapDeTai = new  CapDeTai(0, name);
		if(!objDAO.checkExistCapDeTai(name)){ //cap de tai đã ton tai user 
			
			response.sendRedirect(request.getContextPath() + "/admin/capdetai/index?msg=3");
			return;
			
		} else { //chưa ton tại thi thêm
			if( objDAO.addItem(objCapDeTai) > 0){
				//them thanh cong
				response.sendRedirect(request.getContextPath() + "/admin/capdetai/index?msg=1");
				return; 
			}else{
				//them that bai
				response.sendRedirect(request.getContextPath() + "/admin/capdetai/index?msg=0");
				return; 
			}
		}
			

	}

}
