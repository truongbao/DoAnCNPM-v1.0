package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.DeTai;
import model.bean.QuaTrinhThucHien;
import model.bean.ThanhVien;
import model.bean.ThongBao;
import model.bean.User;
import model.dao.CapDeTaiDAO;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.ThongBaoDAO;
import model.dao.UserDAO;

public class AdminDetailDuyetDeXuatKhoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDetailDuyetDeXuatKhoa() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
      //kiểm tra đã đăng nhập chưa
      	if(  LibraryAuth.CheckQuanLyKhoa(request, response)==false){
      		return;
      	}
      	
        DetaiDAO detaiDAO = new DetaiDAO();
		  
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
	    //tu idDeTai => danh sach thanh vien lam de tai nay (o bang thanh vien)
	    //send qua jsp
	    
	    	    
		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		CapDeTaiDAO capDeTaiDAO = new CapDeTaiDAO();
		request.setAttribute("tenCapDeTaiQly", capDeTaiDAO.getItem(1).getTenCapDeTai()); 
		request.setAttribute("objDeTai", objDeTai);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/detail_duyet_dx_khoa.jsp");
         rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        int result = 0;
        //kiểm tra đã đăng nhập chưa
      	if(  LibraryAuth.CheckQuanLyKhoa(request, response)==false){
      		return;
      	}
      	HttpSession session = request.getSession();
      	User objUser = new User();
      	if(session.getAttribute("quanLyNCKHKhoa") != null){
			objUser = (User) session.getAttribute("quanLyNCKHKhoa");
		}
      	
        DetaiDAO detaiDAO = new DetaiDAO();
        QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
        ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
        int idDeTai = Integer.parseInt(request.getParameter("did"));
        DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
        String noidung = "";
        String chuDe = "";
		if(request.getParameter("submit") != null){
			result = detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetCapTruong, objDeTai.getIdDeTai());
			noidung = "Đề tài "+ objDeTai.getTenDeTai() + " đã được khoa thông qua.";
			chuDe = "Duyệt";
		}else if(request.getParameter("cancel") != null){
			result = detaiDAO.updateToTrangThai(LibraryConstant.Huy, objDeTai.getIdDeTai());
			noidung = "Đề tài "+ objDeTai.getTenDeTai() + " không được khoa thông qua.";
			chuDe = "Không duyệt";
		}
		if(result > 0){
			QuaTrinhThucHien objQTTH = new QuaTrinhThucHien(0, objDeTai.getIdDeTai(), objDeTai.getTrangThai(), new Timestamp(new Date().getTime()), chuDe, noidung);
			qtthDAO.addItem(objQTTH);
			ThongBao objTB = new ThongBao(0, objUser.getIdUser(), objDeTai.getIdUser(), noidung, new Timestamp(new Date().getTime()), objDeTai.getIdDeTai(), objDeTai.getTenDeTai(), objDeTai.getFullName(), objUser.getFullName(), 0);
			thongBaoDAO.addItem(objTB);
			response.sendRedirect(request.getContextPath()+"/admin/qldangkydetai/duyet-de-xuat-khoa?type=load&msg=1");
		}else{
			response.sendRedirect(request.getContextPath()+"/admin/qldangkydetai/duyet-de-xuat-khoa?type=load&msg=0");
		};
	}

}
