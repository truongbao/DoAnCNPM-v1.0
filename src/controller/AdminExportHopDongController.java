package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.HopDong;
import model.dao.HopDongDAO;
import model.bean.DeTai;
import model.dao.DetaiDAO;;

public class AdminExportHopDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminExportHopDongController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckNhanVienTruong(request, response)==false){
					return;
				}
		int idDeTai = Integer.parseInt(request.getParameter("uid"));//idDeTai tren url
		HopDongDAO hopDongDAO = new HopDongDAO();
		HopDong hopDong = new HopDong();
		DeTai deTai = new DeTai();
		DetaiDAO deTaiDAO = new DetaiDAO();
		deTai = deTaiDAO.getObjDeTai(idDeTai);
		hopDong.setTenKhachHang("Nguyễn Văn A");
		hopDong.setChucVuKH("");
		hopDong.setDiaChiKH("");
		hopDong.setEmailKH("trana@gamil.com");
		hopDong.setIdGiangVien(deTai.getIdUser());
		hopDong.setIdDeTai(idDeTai);
		hopDong.setKinhPhi(deTai.getKinhPhiThucHien());
		hopDong.setThoiGianBatDau(Timestamp.valueOf("2018-12-10 12:00:00"));
		hopDong.setThoiGianKetThuc(Timestamp.valueOf("2019-12-10 23:59:00"));
		hopDong.setThoiGianKyHopDong(Timestamp.valueOf("2018-12-10 12:00:00"));
		hopDong.setDienThoaiKH("0976666663");
		hopDong.setTrangThaiHopDong("Đã ký");
		int result = 0;
		HopDong objHD = hopDongDAO.getObjHopDongByIdDeTai(idDeTai);
    	if (objHD == null) { //đề tài chưa lưu hơp đồng=> lưu hợp đồng
    		result = hopDongDAO.addItem(hopDong);
    		deTaiDAO.updateToTrangThai(LibraryConstant.DangThucHien, idDeTai);
    	} else {//đề tài đã được lưu hơp đồng=> sửa hợp đồng
    		result = hopDongDAO.editItem(objHD.getIdHopDong(),hopDong);
    	}
		if (result != 0) {
			request.setAttribute("objHopDong", hopDongDAO.getObjHopDongByIdDeTai(idDeTai));
			RequestDispatcher rd = request.getRequestDispatcher("/admin/hopdong/exportWord.jsp");
			rd.forward(request, response);
		
	    }else{
	    	response.sendRedirect(request.getContextPath()+"/admin/qldetai/index_nhanvien?type=load&msg=0");
	    	return;
	    } 
	    
	    
	}

}
