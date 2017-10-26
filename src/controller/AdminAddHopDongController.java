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
	    
		String tenKhachHang = request.getParameter("ten_KH");
		String chucVuKH = request.getParameter("chuc_vu_KH");
		String diaChiKH = request.getParameter("dia_chi_KH");
		String emailKH = request.getParameter("email_KH");
		int idGiangVien = Integer.parseInt(request.getParameter("giang_vien"));
		int idDeTai = Integer.parseInt(request.getParameter("de_tai"));
		Timestamp thoiGianBatDau = Timestamp.valueOf(request.getParameter("thoi_gian_bat_dau"));
		Timestamp thoiGianKetThuc = Timestamp.valueOf(request.getParameter("thoi_gian_ket_thuc"));
		int kinhPhi = Integer.parseInt(request.getParameter("kinh_phi"));
		Timestamp thoiGianKyHopDong = request.getParameter("thoi_gian_ki_HD") != "" ? Timestamp.valueOf(request.getParameter("thoi_gian_ki_HD")) : null;
		String dienThoaiKH = request.getParameter("sdt_KH");
		String trangThaiHopDong = request.getParameter("trang_thai_HD");
		HopDong objHopDong = new  HopDong(0, tenKhachHang, chucVuKH,
				diaChiKH, emailKH,
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
