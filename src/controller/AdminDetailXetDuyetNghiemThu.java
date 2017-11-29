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
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.ThongBaoDAO;
import model.dao.UserDAO;

public class AdminDetailXetDuyetNghiemThu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDetailXetDuyetNghiemThu() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
      //kiểm tra đã đăng nhập chưa
      	if(  LibraryAuth.CheckNhanVienTruong(request, response)==false){
      		return;
      	}
      	
        DetaiDAO detaiDAO = new DetaiDAO();
		  
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
	    //tu idDeTai => danh sach thanh vien lam de tai nay (o bang thanh vien)
	    //send qua jsp
	    
	    	    
		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		request.setAttribute("objDeTai", objDeTai);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldetai/nhanvien/detail_duyet_nt_nv.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
       //kiểm tra đã đăng nhập chưa
       	if(  LibraryAuth.CheckNhanVienTruong(request, response)==false){
       		return;
       	}
       	
       	HttpSession session = request.getSession();
    	User objUser = null;
 		if(session.getAttribute("admin")!=null){
 			 objUser = (User)session.getAttribute("admin");
 		}else{
 			objUser = (User)session.getAttribute("nhanVienQLNCKHTruong");
 		}	
       	
         DetaiDAO detaiDAO = new DetaiDAO();
         ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
         QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
         int idDeTai = Integer.parseInt(request.getParameter("did"));
         DeTai objDT = detaiDAO.getObjDeTai(idDeTai);
         String noiDung = "";
         String chuDe ="";
 		if(request.getParameter("update") != null){
 			
 			float score = Float.parseFloat(request.getParameter("score"));
 	 	    String content = request.getParameter("content");
 	 	    String xepLoai = LibraryConstant.getXepLoai(score);
 	 	   
 	 		if (detaiDAO.updateKQNghiemThu(idDeTai, content, score, xepLoai)>0){
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_nv?type=load&msg=1");
 	 		}else{
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_nv?type=load&msg=0");
 	 		}
 		}else if(request.getParameter("confirm") != null){
 			if (detaiDAO.updateToTrangThai(LibraryConstant.DaHoanThanh, idDeTai) > 0){
 				noiDung = "Đề tài "+ objDT.getTenDeTai() + " đã được nghiệm thu.";
				chuDe = "Kết quả nghiệm thu";
				ThongBao objTB = new ThongBao(0, objUser.getIdUser(), objDT.getIdUser(), noiDung, new Timestamp(new Date().getTime()), objDT.getIdDeTai(), objDT.getTenDeTai(), objDT.getFullName(), objUser.getFullName(), 0);
				thongBaoDAO.addItem(objTB);
				QuaTrinhThucHien objQTTH = new QuaTrinhThucHien(0, objDT.getIdDeTai(), objDT.getTrangThai(), new Timestamp(new Date().getTime()), chuDe, noiDung);
				qtthDAO.addItem(objQTTH);
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=1");
 	 		}else{
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=0");
 	 		}
 		}else if(request.getParameter("cancel") != null){
 			if (detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetNghiemThu, idDeTai) > 0){
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=1");
 	 		}else{
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=0");
 	 		}
 		}
 	   
 	    
          
	}

}
