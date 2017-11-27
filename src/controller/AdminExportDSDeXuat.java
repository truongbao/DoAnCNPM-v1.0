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
import model.bean.DeTai;
import model.dao.DetaiDAO;

public class AdminExportDSDeXuat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminExportDSDeXuat() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
      doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
       //kiểm tra đã đăng nhập chưa
       	if(  LibraryAuth.CheckNhanVienTruong(request, response)==false){
       		return;
       	}
       	
       	DetaiDAO detaiDAO = new DetaiDAO();
       	
       	ArrayList<DeTai> listDeTai = detaiDAO.getListDeTaiWith(LibraryConstant.DangChoXetCapTruong);
 		 request.setAttribute("listDeTai", listDeTai);
 		 System.out.println("XUAT FILE DS: "+listDeTai.size());
  		RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/nhanvien/ds.jsp");
          rd.forward(request, response);
	}

}
