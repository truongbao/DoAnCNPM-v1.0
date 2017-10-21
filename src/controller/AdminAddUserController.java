package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.StringLibrary;
import model.bean.User;
import model.dao.UserDAO;


public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminAddUserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    /*StringLibrary st = new StringLibrary();//tao doi tuong lop ma hóa
	    
	    UserDAO objDAO = new UserDAO();
	    
		String username = request.getParameter("username");
		String password =  st.MD5( request.getParameter("password")) ;
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		
		int active = 0;
		
		if(request.getParameter("active")!=null){
		    active = 1;
		}
		
		User objUser = new User(0, username, password, fullname, email, active);
		
		if(objDAO.checkUser(username)!=null){ //đã ton tai user 
			
			response.sendRedirect(request.getContextPath() + "/admin/show-addUser?msg=4");
			return;
			
		}else{ //chưa ton tại thi thêm
			
			if( objDAO.addItem(objUser) > 0){
				//them thanh cong
				response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=1");
				return; 
			}else{
				//them that bai
				response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=0");
				return; 
			}
			
		}
		*/
		
		
		
		
		
	}

}
