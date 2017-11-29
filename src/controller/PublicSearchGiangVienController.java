package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Khoa;
import model.bean.User;
import model.dao.UserDAO;

public class PublicSearchGiangVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicSearchGiangVienController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
	      response.setCharacterEncoding("UTF-8");
	      response.setContentType("text/html");
	      
	      UserDAO userDAO = new UserDAO();
	      
	      int idKhoa = Integer.parseInt(request.getParameter("idKhoa"));
	      
		  String txtSearch = new String(request.getParameter("txtSearch").getBytes("ISO-8859-1"), "UTF-8");//lây tên tin nguoi dung nhập 
		  
		  //Gửi qua jsp danh sach các khoa
	      ArrayList<Khoa> listKhoa = new ArrayList<Khoa>();
		
		  if(listKhoa != null){
			 listKhoa = userDAO.getListKhoa();
		  }
		 
		  request.setAttribute("listKhoa", listKhoa);
		  
		  if(idKhoa == 0){ 
		  
			  if(!"".equals(txtSearch) ){
		          ArrayList<User> listGiangVien = userDAO.searchByNameGiangVien(txtSearch);
				  
				  if( listGiangVien.size() > 0 ){ //tìm thấy 
					  request.setAttribute("listSearchGiangVien", listGiangVien);
			          RequestDispatcher rd = request.getRequestDispatcher("/list_search_gv.jsp");
			          rd.forward(request, response);
				  }else{//ko thấy
					  RequestDispatcher rd = request.getRequestDispatcher("/list_search_gv.jsp");
			          rd.forward(request, response);
				  }
	       
			  }
		  
		  }else{
			  if(!"".equals(txtSearch) ){
		          ArrayList<User> listGiangVienByIdKhoa = userDAO.searchByNameGiangVienByIdKhoa(txtSearch,idKhoa);
				  
				  if( listGiangVienByIdKhoa.size() > 0 ){ //tìm thấy 
					  request.setAttribute("listSearchGiangVien", listGiangVienByIdKhoa);
			          RequestDispatcher rd = request.getRequestDispatcher("/list_search_gv.jsp");
			          rd.forward(request, response);
				  }else{//ko thấy
					  RequestDispatcher rd = request.getRequestDispatcher("/list_search_gv.jsp");
			          rd.forward(request, response);
				  }
	       
			  }
		  }
		  
		  
        
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	

}
