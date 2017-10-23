package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.StringLibrary;
import model.bean.User;
import model.dao.UserDAO;

public class AuthAdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AuthAdminLoginController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/admin/auth/login.jsp");
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");

	    
	    StringLibrary st = new StringLibrary();//tao doi tuong lop ma hóa
	    
	    UserDAO objDAO = new UserDAO();
	    
		String username = request.getParameter("username");
		String password =  st.MD5( request.getParameter("matKhau")) ;
		
		//viet ham lay ra obj user ung vs username , pass
		User  objUserLogin  = objDAO.checkLoginPublic(username, password);
		if( objUserLogin !=null && objUserLogin.getIdLoaiTaiKhoan() != 2){ //kiem tra  user và pass
			//login thanh cong = > tạo session

			HttpSession session = request.getSession();
			String permision = null; 
			switch (objUserLogin.getIdLoaiTaiKhoan() ) {
			case 1:
				permision = "admin";					
				break;
			case 3:
				permision = "quanLyNCKHKhoa";
				break;

			case 4:
				permision = "nhanVienQLNCKHTruong";
				break;
			}
			//System.out.println(objUserLogin.toString());
			session.setAttribute(permision, objUserLogin );//gán đối tuong tra về cho session=> tâ có đoi tuong user lưu trong sesion
			response.sendRedirect(request.getContextPath() + "/admin");
			return; 
		}else{
			//that bai
			response.sendRedirect(request.getContextPath() + "/auth/admin/login?msg=0");
			return; 
		}
         
         
	}
}
