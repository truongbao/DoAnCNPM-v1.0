package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import model.bean.DeTai;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class PublicUpdateDetaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicUpdateDetaiController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    //kiểm tra đã đăng nhập ở public chưa
		if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		}
		
		
		UserDAO userDAO = new UserDAO();
	    DetaiDAO detaiDAO =new DetaiDAO();
	    
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
        DeTai objDeTaiByIdDeTaiDK = detaiDAO.getObjectDeTaiByIdDeTaiDK(idDeTai);
	    
	    request.setAttribute("objDeTaiByIdDeTaiDK", objDeTaiByIdDeTaiDK);
	    
       /* DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		
		request.setAttribute("objDeTai", objDeTai);*/
	    
	    
	    
	    request.setAttribute("listLinhVucNC", detaiDAO.getListLinhVucNC());
	    
	    
	    //lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
        
        request.setAttribute("objUser", objUser);
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/update_detai.jsp");
         rd.forward(request, response);
         
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        
	}

}
