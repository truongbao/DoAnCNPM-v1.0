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
import model.bean.ThoiGianDK;
import model.dao.ThoiGianDKDAO;

public class AdminIndexThoiGianDKController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexThoiGianDKController() {
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
         
         RequestDispatcher rd = request.getRequestDispatcher("/admin/thoigiandk/index.jsp");
         rd.forward(request, response);

		
	}

}
