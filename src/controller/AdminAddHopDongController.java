package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.StringLibrary;
import model.bean.HopDong;
import model.dao.HopDongDAO;;


public class AdminAddHopDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminAddHopDongController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    StringLibrary st = new StringLibrary();//tao doi tuong lop ma hóa
	    
	    HopDongDAO objDAO = new HopDongDAO();
	    
		String tenKhachHang = request.getParameter("fax");
		String chucVuKH = request.getParameter("fax");
		String diaChiKH = request.getParameter("fax");
		String emailKH = request.getParameter("fax");
		int idNguoiDaiDien = Integer.parseInt(request.getParameter("fax"));
		int idGiangVien = Integer.parseInt(request.getParameter("fax"));
		int idDeTai = Integer.parseInt(request.getParameter("fax"));
		Timestamp thoiGianBatDau = Timestamp.valueOf(request.getParameter("fax"));
		Timestamp thoiGianKetThuc = Timestamp.valueOf(request.getParameter("fax"));
		int kinhPhi = Integer.parseInt(request.getParameter("fax"));
		Timestamp thoiGianKyHopDong = Timestamp.valueOf(request.getParameter("fax"));
		String dienThoaiKH = request.getParameter("fax");
		String trangThaiHopDong = request.getParameter("fax");
		HopDong objHopDong = new  HopDong(0, tenKhachHang, chucVuKH,
				diaChiKH, emailKH, idNguoiDaiDien,
				idGiangVien, idDeTai, thoiGianBatDau,
				thoiGianKetThuc, kinhPhi,
				thoiGianKyHopDong, dienThoaiKH,
				trangThaiHopDong);
			if( objDAO.addItem(objHopDong) > 0){
				//them thanh cong
				response.sendRedirect(request.getContextPath() + "/admin/hopdong/index?msg=1");
				return; 
			}else{
				//them that bai
				response.sendRedirect(request.getContextPath() + "/admin/hopdong/index?msg=0");
				return; 
			}
			
		
		
		
		
		
		
	}

}
