package controller;

import java.io.IOException;
import java.util.ArrayList;

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
import model.dao.CatDAO;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class PublicListRegisterDetaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicListRegisterDetaiController() {
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
		
		//lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
        
        
        //phân trang
         int current_page = 1;		
		 int row_count = LibraryConstant.ROW_PUBLIC_COUNT; //7 tin trên 1 page
		
		 
		 //tong so danh muc
		 int sumDeTaiDK = detaiDAO.countDeTaiDKPublic(objUser.getIdUser());
		 
		 //tong so trang
		int sumPage = (int) Math.ceil((float)sumDeTaiDK / row_count) ;
		request.setAttribute("sumPage", sumPage);
		
		//lấy số trang hiện tại
		if (request.getParameter("page") != null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		 
		// tính offset
		int offset = (current_page - 1) * row_count;
		request.setAttribute("current_page", current_page);
		
		
		
		    
		//lấy danh sach đề tai do user đang login đăng ký
		request.setAttribute("listDeTaiDK", detaiDAO.getListDeTaiDK( objUser.getIdUser(), offset,row_count ) );
		//request.setAttribute("objUser", userDAO.getObjUser(objUser.getIdUser()));
		
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/list_register_detai.jsp");
         rd.forward(request, response);     
         
         
         
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        
	}

}
