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
import model.bean.ThongBao;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.ThongBaoDAO;
import model.dao.UserDAO;

public class AdminDuyetDTKhoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String keyword = null;
	
    public AdminDuyetDTKhoa() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
         //kiểm tra đã đăng nhập chưa
       	if(  LibraryAuth.CheckQuanLyKhoa(request, response)==false){
       		return;
       	}
       		
         HttpSession session = request.getSession();
         DetaiDAO detaiDAO = new DetaiDAO();
         QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
         ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
 		User objUser = null;
 		if(session.getAttribute("quanLyNCKHKhoa")!=null){
 			 objUser = (User)session.getAttribute("quanLyNCKHKhoa");
 		}
 		int idFaculty = objUser.getIdKhoa();
 		if ("load".equals(request.getParameter("type"))) { 
 			int DT_sum = detaiDAO.getSumListDeTaiByStatus(idFaculty, LibraryConstant.DangChoDuyetCapKhoa,"",1);
 			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
 			int current_page = 1;
 			if (request.getParameter("page") != null) {
 				current_page = Integer.parseInt(request.getParameter("page"));
 			}
 			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
 			request.setAttribute("current_page", current_page);
 			request.setAttribute("page_sum", page_sum);
 			 ArrayList<DeTai> listDuyetDeTaiByIdKhoa = detaiDAO.getListDeTaiByStatus(0,LibraryConstant.DangChoDuyetCapKhoa,"",1,offset, LibraryConstant.ROW_COUNT);
 			 request.setAttribute("listDuyetDeTaiByIdKhoa", listDuyetDeTaiByIdKhoa);
 			 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/duyet_de_xuat_khoa.jsp");
 			 rd.forward(request, response);
 		}else if ("search".equals(request.getParameter("type"))){
 			if (request.getParameter("keyword") != null) {
 				keyword = request.getParameter("keyword");
 			}
 			int DT_sum = detaiDAO.getSumListDeTaiByStatus(idFaculty, LibraryConstant.DangChoDuyetCapKhoa,keyword,1);
 			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
 			int current_page = 1;
 			if (request.getParameter("page") != null) {
 				current_page = Integer.parseInt(request.getParameter("page"));
 			}
 			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
 			request.setAttribute("current_page", current_page);
 			request.setAttribute("page_sum", page_sum);
 			 ArrayList<DeTai> listDuyetDeTaiByIdKhoa = detaiDAO.getListDeTaiByStatus(0,LibraryConstant.DangChoDuyetCapKhoa,keyword,1,offset, LibraryConstant.ROW_COUNT);
 			 request.setAttribute("listDuyetDeTaiByIdKhoa", listDuyetDeTaiByIdKhoa);
 			 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/duyet_de_xuat_khoa.jsp");
 			 rd.forward(request, response);
 		}else if("action".equals(request.getParameter("type"))){
 			String[] checkedId_str = request.getParameterValues("idDeTai");
 			int action = Integer.parseInt(request.getParameter("action"));
 			int result = 0;
 			String strs_idDT = "";
 			for(String i : checkedId_str){
 				strs_idDT += i+",";
 			}
 			if(strs_idDT.endsWith(",")){
 				strs_idDT = strs_idDT.substring(0, strs_idDT.length()-1);
 			}
 			if(action == 1){
				result = detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetCapTruong, strs_idDT);
 			}else{
 				result = detaiDAO.updateToTrangThai(LibraryConstant.Huy, strs_idDT);
 			}
 			if(result > 0){
 				for (String string : checkedId_str) {
					int idDt = Integer.parseInt(string);
					DeTai objDT = detaiDAO.getObjDeTai(idDt);
					String noiDung = "";
					String chuDe = "";
					if(action == 1){
						noiDung = "Đề tài "+ objDT.getTenDeTai() + " đã được khoa thông qua.";
						chuDe = "Duyệt";
					}else{
						noiDung = "Đề tài "+ objDT.getTenDeTai() + " không được khoa thông qua.";
						chuDe = "Không duyệt";
					}
					QuaTrinhThucHien objQTTH = new QuaTrinhThucHien(0, objDT.getIdDeTai(), objDT.getTrangThai(), new Timestamp(new Date().getTime()), chuDe, noiDung);
					qtthDAO.addItem(objQTTH);
					ThongBao objTB = new ThongBao(0, objUser.getIdUser(), objDT.getIdUser(), noiDung, new Timestamp(new Date().getTime()), objDT.getIdDeTai(), objDT.getTenDeTai(), objDT.getFullName(), objUser.getFullName(), 0);
					thongBaoDAO.addItem(objTB);
				}
				response.sendRedirect(request.getContextPath()+"/admin/qldangkydetai/duyet-de-xuat-khoa?type=load&msg=1");
			}else{
				response.sendRedirect(request.getContextPath()+"/admin/qldangkydetai/duyet-de-xuat-khoa?type=load&msg=0");
			};
 		}
	}

}
