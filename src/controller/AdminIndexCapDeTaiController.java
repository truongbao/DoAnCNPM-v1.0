package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.CapDeTai;
import model.dao.CapDeTaiDAO;

public class AdminIndexCapDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexCapDeTaiController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
        //kiểm tra đã đăng nhập chưa
 		if(  LibraryAuth.CheckAdmin(request, response)==false){
 			return;
 		}
         
         CapDeTaiDAO objDAO = new CapDeTaiDAO();
         
         int row_count = LibraryConstant.ROW_COUNT;
 		 int current_page = 1;		
 		 
 		 //tong so user
 		 int sumCapDeTai = objDAO.countCapDeTai();
 		// tong so trang
 		 int sumPage = (int)Math.ceil((float)sumCapDeTai/row_count);
 		 request.setAttribute("sumPage", sumPage);
 		
 		//lấy số trang hiện tại
 		if (request.getParameter("page") != null){
 			current_page = Integer.parseInt(request.getParameter("page"));
 		}
  		 
 		// tính offset
 		int offset = (current_page - 1) * row_count;
 		request.setAttribute("current_page", current_page);
         
 		ArrayList<CapDeTai> listCapDeTai = objDAO.getItemsByPage(offset);
		request.setAttribute("listCapDeTai", listCapDeTai); 
         
        //ArrayList<User> listUser = objDAO.getItems();
        //request.setAttribute("listUser", listUser);
         RequestDispatcher rd = request.getRequestDispatcher("/admin/capdetai/index.jsp");
         rd.forward(request, response);

		
	}

}
