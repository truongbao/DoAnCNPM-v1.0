package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;

public class PublicIndexQLTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexQLTaiKhoanController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    UserDAO userDAO = new UserDAO();
	    
		//lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
		
        //request.setAttribute("objUser", objUser);
        
        request.setAttribute("objUser", userDAO.getObjUser(objUser.getIdUser()));
        
       RequestDispatcher rd = request.getRequestDispatcher("/quanly_taikhoan.jsp");
       rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
		
		
       
	}

}
