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

import library.LibraryAuth;
import library.LibraryConstant;
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
		 
		//kiểm tra đã đăng nhập ở public chưa
		if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		}
		 
		 //hiển thị danh sách đề tài có phân trang
		  UserDAO userDAO = new UserDAO();  
		  DetaiDAO detaiDAO = new DetaiDAO();
		    
    	 /*for (DeTai objDetai : detaiDAO.getListDeTai()) {
    		  String input = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(objDetai.getThoiGianDangKy());
    		  java.sql.Timestamp timeDK = java.sql.Timestamp.valueOf( input ) ;
    		  objDetai.setThoiGianDangKy(timeDK);
			   
		 }*/
		  
		  
		//lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        } 
        
        
        
        //---------phân trang--------------
        int current_page = 1;		
		int row_count = LibraryConstant.ROW_COUNT; //5 tin trên 1 page
		
		 
		 //tong so de tai da duyet thuyet minh (co ma so)
		 int sumDeTai = detaiDAO.countDeTaiCoMaSoPublic(objUser.getIdUser());
		 
		 //tong so trang
		int sumPage = (int) Math.ceil((float)sumDeTai / row_count) ;
		request.setAttribute("sumPage", sumPage);
		
		//lấy số trang hiện tại
		if (request.getParameter("page") != null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		 
		// tính offset
		int offset = (current_page - 1) * row_count;
		request.setAttribute("current_page", current_page);
        
        
    	
    	request.setAttribute("listDeTai", detaiDAO.getListDeTaiPagingPublic(objUser.getIdUser(), offset,row_count )  );
    	
    	RequestDispatcher rd = request.getRequestDispatcher("/quanly_detai.jsp");
        rd.forward(request, response);
	            
	        
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	}

}
