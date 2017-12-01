package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.StringLibrary;
import model.bean.ThoiGianDK;
import model.dao.ThoiGianDKDAO;


public class AdminEditThoiGianDKController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminEditThoiGianDKController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckAdmin(request, response)==false){
					return;
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckAdmin(request, response)==false){
					return;
				}
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    ThoiGianDKDAO objDAO = new ThoiGianDKDAO();
	    
	    int idThoiGianDK = Integer.parseInt( request.getParameter("uid") ) ;
	    
	    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
	    java.util.Date parsed;
		Date thoiGianBatDau = null, thoiGianKetThuc = null;
		try {
			parsed = format.parse(request.getParameter("tgbd"));
			thoiGianBatDau = new Date(parsed.getTime()); 
			parsed = format.parse(request.getParameter("tgkt"));
			thoiGianKetThuc = new Date(parsed.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("tgbd : "+thoiGianBatDau+"--tgkt : "+thoiGianKetThuc);
		
		if( objDAO.changeThoiGianDangKy(idThoiGianDK, thoiGianBatDau, thoiGianKetThuc) > 0){
			LibraryAuth.CheckThoiGianChuyenTrangThaiDeTai();
			//sửa thanh cong
			response.sendRedirect(request.getContextPath() + "/admin/thoigiandk/index?msg=1");
			return; 
		}else{
			//sửa that bai
			response.sendRedirect(request.getContextPath() + "/admin/thoigiandk/index?msg=0");
			return; 
		}
		
	}

}
