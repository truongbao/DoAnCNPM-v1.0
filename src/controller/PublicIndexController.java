package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryConstant;
import model.bean.Khoa;
import model.bean.User;
import model.dao.UserDAO;

public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//lay danh sach giang vien
		//noi bang user + hocvi
		
		UserDAO userDAO = new UserDAO();
		
		//phân trang
        int current_page = 1;		
		int row_count = LibraryConstant.ROW_COUNT_GV; //8 tin trên 1 page
		
		 
		//tong so giang vien
		int sumGV = userDAO.countGiangVienPublic();
		 
		//tong so trang
		int sumPage = (int) Math.ceil((float)sumGV / row_count) ;
		request.setAttribute("sumPage", sumPage);
		
		//lấy số trang hiện tại
		if (request.getParameter("page") != null){
			current_page = Integer.parseInt(request.getParameter("page"));
		}
		 
		// tính offset
		int offset = (current_page - 1) * row_count;
		request.setAttribute("current_page", current_page);
		
		
		
		//lay danh sach giang vien
        ArrayList<User> listGiangVienPublic = new ArrayList<User>();
		
		if(listGiangVienPublic != null){
			  listGiangVienPublic = userDAO.getListGiangVienPaging(offset,row_count);
		}
		
		request.setAttribute("listGiangVienPublic", listGiangVienPublic);
		 
		//Gửi qua jsp danh sach các khoa
	     ArrayList<Khoa> listKhoa = new ArrayList<Khoa>();
		
		 if(listKhoa != null){
			 listKhoa = userDAO.getListKhoa();
		 }
		 
		 request.setAttribute("listKhoa", listKhoa);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
         rd.forward(request, response);
         
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        
		
	}
	
	

	
	
}
