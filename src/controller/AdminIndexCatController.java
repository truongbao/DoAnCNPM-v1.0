package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.define;
import library.LibraryAuth;
import model.bean.Cat;
import model.dao.CatDAO;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexCatController() {
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
         
 		/*if(  LibraryAuth.CheckLogin(request, response)==false){
 			return;
 		}*/
 		
        //================================
 		 int current_page = 1;		
 		 int row_count = define.ROW_COUNT_CAT;
 		 CatDAO objDAO = new CatDAO();
 		 
 		 //tong so danh muc
 		 int sumCat = objDAO.countCat();
 		 
 		 //tong so trang
 		int sumPage = (int) Math.ceil((float)sumCat / row_count) ;
		request.setAttribute("sumPage", sumPage);
		
		//lấy số trang hiện tại
		if (request.getParameter("page") != null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
 		 
		// tính offset
		int offset = (current_page - 1) * row_count;
		request.setAttribute("current_page", current_page);
 		
		
		
		ArrayList<Cat> listCatParent = objDAO.getItemsByParentPage(offset);
		request.setAttribute("listCatParent", listCatParent);
        
         //ArrayList<Cat> listCatParent =  objDAO.getItemsByParent();
        // request.setAttribute("listCatParent", listCatParent);
         
         RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/index.jsp");
         rd.forward(request, response);

		
	}

}
