package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class PublicIndexQLThanhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexQLThanhVienController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html");
		 
		 UserDAO userDAO  = new UserDAO(); 
		 
		 //kiểm tra đã đăng nhập ở public chưa
	     if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		 }
		 
		    
	     HttpSession session = request.getSession();
	     User objUser = null;
         if(session.getAttribute("sobjUserPublic") != null){ 
        	 objUser =  (User) session.getAttribute("sobjUserPublic");
         }
         
         
         //phân trang
         int current_page = 1;		
		 int row_count = LibraryConstant.ROW_COUNT; //5 tin trên 1 page
		
		 
		 //tong so member ứng vs idUser đang login
		 int sumMember = userDAO.countMemberPublic(objUser.getIdUser());
		 
		 //tong so trang
		int sumPage = (int) Math.ceil((float)sumMember / row_count) ;
		request.setAttribute("sumPage", sumPage);
		
		//lấy số trang hiện tại
		if (request.getParameter("page") != null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		 
		// tính offset
		int offset = (current_page - 1) * row_count;
		request.setAttribute("current_page", current_page);
         
		
        //lay danh sach thanh vien co phan trang 
	    request.setAttribute("ListThanhVienByIdUser", userDAO.getListThanhVienByIdUser(objUser.getIdUser(),offset,row_count ) );
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/quanly_thanhvien.jsp");
	     rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
       
	}

}
