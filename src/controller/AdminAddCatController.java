package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.Cat;
import model.dao.CatDAO;


public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminAddCatController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				/*//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}*/
				CatDAO objDAO = new CatDAO();
				ArrayList<Cat> listCatParent = objDAO.getItemsByParent();
				request.setAttribute("listCatParent", listCatParent);
				
				RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/add.jsp");
				rd.forward(request, response);
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*//kiểm tra đã đăng nhập chưa
		if(  LibraryAuth.CheckLogin(request, response)==false){
			return;
		}*/
		
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    CatDAO objDAO = new CatDAO();
	    
		String tenDanhMuc = request.getParameter("tendanhmuc");
		int idParrent = Integer.parseInt(request.getParameter("parent_id"));
		int active = 0;
		
		if(request.getParameter("active")!=null){
		    active = 1;
		}
		
		Cat objCat = new Cat(0, tenDanhMuc, idParrent, active);
		
		if( objDAO.addItem(objCat) > 0){
			//them thanh cong
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=1");
			return; 
		}else{
			//them that bai
			response.sendRedirect(request.getContextPath() + "/admin/cat/index?msg=0");
			return; 
		}
		
		
	}

}
