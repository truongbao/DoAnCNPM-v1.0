package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.DeTai;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class PublicIndexQLDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexQLDeTaiController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html");
		 
		 //hiển thị danh sách đề tài có phân trang
		    
		    DetaiDAO detaiDAO = new DetaiDAO();
		    
			HttpSession session = request.getSession();
			
	        if(session.getAttribute("sobjUserPublic")!=null){

	        	 /*for (DeTai objDetai : detaiDAO.getListDeTai()) {
	        		  String input = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(objDetai.getThoiGianDangKy());
	        		  java.sql.Timestamp timeDK = java.sql.Timestamp.valueOf( input ) ;
	        		  objDetai.setThoiGianDangKy(timeDK);
					   
				 }*/
	        	
	        	request.setAttribute("listDeTai", detaiDAO.getListDeTai());
	        	
	        	RequestDispatcher rd = request.getRequestDispatcher("/quanly_detai.jsp");
	            rd.forward(request, response);
	            
	        }
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	}

}
