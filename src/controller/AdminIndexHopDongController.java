package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.HopDong;
import model.dao.HopDongDAO;
import constant.define;

public class AdminIndexHopDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexHopDongController() {
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
 		if(  LibraryAuth.CheckLogin(request, response)==false){
 			return;
 		}
 		HopDongDAO objDAO = new HopDongDAO();
         
         int row_count = define.ROW_COUNT;
 		 int current_page = 1;		
 		 
 		 //tong so hop dong
 		 int sumHopDong = objDAO.countHopDong();
 		// tong so trang
 		 int sumPage = (int)Math.ceil((float)sumHopDong/row_count);
 		 request.setAttribute("sumPage", sumPage);
 		
 		//lấy số trang hiện tại
 		if (request.getParameter("page") != null){
 			current_page = Integer.parseInt(request.getParameter("page"));
 		}
  		 
 		// tính offset
 		int offset = (current_page - 1) * row_count;
 		request.setAttribute("current_page", current_page);
         
 		ArrayList<HopDong> listHopDong = objDAO.getItemsByPage(offset);
		request.setAttribute("listHopDong", listHopDong); 
         
         RequestDispatcher rd = request.getRequestDispatcher("/admin/hopdong/index.jsp");
         rd.forward(request, response);

		
	}

}
