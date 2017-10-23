package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.User;
import model.dao.UserDAO;
import constant.define;

public class AdminIndexUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexUserController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
       
         
        /*//kiểm tra đã đăng nhập chưa
 		if(  LibraryAuth.CheckLogin(request, response)==false){
 			return;
 		}*/
         
         
         
         /*UserDAO objDAO = new UserDAO();
         
         int row_count = define.ROW_COUNT;
 		 int current_page = 1;		
 		 
 		 //tong so tin
 		  * +
 		 int sumUser = objDAO.countUser();
 		 
 		// tong so trang
 		 int sumPage = (int)Math.ceil((float)sumUser/row_count);
 		 request.setAttribute("sumPage", sumPage);
 		
 		//lấy số trang hiện tại
 		if (request.getParameter("page") != null){
 			current_page = Integer.parseInt(request.getParameter("page"));
 		}
  		 
 		// tính offset
 		int offset = (current_page - 1) * row_count;
 		request.setAttribute("current_page", current_page);
         
 		ArrayList<User> listUser = objDAO.getItemsByPage(offset);
		request.setAttribute("listUser", listUser); 
         
        //ArrayList<User> listUser = objDAO.getItems();
        //request.setAttribute("listUser", listUser);
         */
         RequestDispatcher rd = request.getRequestDispatcher("/admin/users/index.jsp");
         rd.forward(request, response);

		
	}

}
