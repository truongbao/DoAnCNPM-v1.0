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


public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminEditUserController() {
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
	    
	    /*UserDAO objDAO = new UserDAO();
	    
	    int idUser = Integer.parseInt( request.getParameter("uid") ) ;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		int active = 0;
		if(request.getParameter("active")!=null){
		    active = 1;
		}
		
		//lây ra doi tuong ứng voi uid để lây ra doi tượng pass cũ
		User objUserId = objDAO.getItem(idUser);
		
		StringLibrary st = new StringLibrary();//doi tuong de ma hoa MD5
		
		if("".equals(password)){ //lay lại pass cũ dựa vào id
			password = objUserId.getPassword();
			
		}else{//cap nhat pass moi
			password = st.MD5(password);
		}
		
		
		
		User objUser = new User(idUser, username, password, fullname, email, active);
		
		if( objDAO.editItem(objUser) > 0){
			//sua thanh cong
			response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=2");
			return; 
		}else{
			//them that bai
			response.sendRedirect(request.getContextPath() + "/admin/indexUser?msg=0");
			return; 
		}
		*/
	    
		
	}

}
