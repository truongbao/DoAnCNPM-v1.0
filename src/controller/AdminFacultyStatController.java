package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.DeTai;
import model.bean.User;
import model.dao.DetaiDAO;

public class AdminFacultyStatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminFacultyStatController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int dt_hoanthanh = 0; //so de tai da hoan thanh
		int dt_dangthuchien = 0; // so de tai dang thuc hien
		int dt_CCS = 0; // so detai cap co so
		int dt_DHDN = 0; // so detai cap ĐH Da Nang
		int dt_BGD = 0; // so detai cap Bo GD
		
		// kiá»ƒm tra Ä‘Ã£ Ä‘Äƒng nháº­p chÆ°a
		 if( !LibraryAuth.CheckQuanLyKhoa(request, response)){
			 return;
		 }
		 HttpSession session = request.getSession();
		 User objUser = (User) session.getAttribute("quanLyNCKHKhoa");
		 System.out.println(objUser.getIdKhoa());
		 int idFaculty = objUser.getIdKhoa();

//		int idFaculty = 6;
		DetaiDAO model = new DetaiDAO();
		if ("load".equals(request.getParameter("type"))) {
			
			int DT_sum = model.getSumWithIdFaculty(idFaculty);
			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
			int current_page = 1;
			if (request.getParameter("page") != null) {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
			request.setAttribute("current_page", current_page);
			request.setAttribute("page_sum", page_sum);
			request.setAttribute("alItem",model.getListByFaculty(idFaculty, offset, LibraryConstant.ROW_COUNT));
			ArrayList<DeTai> arrDT = (ArrayList<DeTai>)model.getListByFaculty(idFaculty, offset, DT_sum);
			for (DeTai obj : arrDT) {
				if( LibraryConstant.TOPICTYPE_CAPCOSO.equals(obj.getCapDeTai())) dt_CCS++;
				else if (LibraryConstant.TOPICTYPE_CAPDHDN.equals(obj.getCapDeTai())) dt_DHDN++;
				else if (LibraryConstant.TOPICTYPE_CAPBGD.equals(obj.getCapDeTai())) dt_BGD++;
				
				if(LibraryConstant.DaHoanThanh.equals(obj.getTrangThai())) dt_hoanthanh++;
				else if(LibraryConstant.DangThucHien.equals(obj.getTrangThai())) dt_dangthuchien++;
			}
			request.setAttribute("dt_CCS", dt_CCS);
			request.setAttribute("dt_DHDN", dt_DHDN);
			request.setAttribute("dt_BGD", dt_BGD);
			request.setAttribute("dt_hoanthanh", dt_hoanthanh);
			request.setAttribute("dt_dangthuchien", dt_dangthuchien);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/faculty_statistical.jsp");
			rd.forward(request, response);
		} else if ("search".equals(request.getParameter("type"))) {
			int year = Integer.parseInt(request.getParameter("year_create"));
			String type_stat = request.getParameter("type_stat");
			String type_detai =(String) request.getParameter("type_detai");
			int DT_sum =  model.getSumWithIdFacultyAndSearch(idFaculty,year,type_detai,type_stat);
			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
			int current_page = 1;
			if (request.getParameter("page") != null) {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
			request.setAttribute("current_page", current_page);
			request.setAttribute("page_sum", page_sum);
			request.setAttribute("alItem",model.getSearchListByFaculty(idFaculty,year,type_detai, type_stat, offset, LibraryConstant.ROW_COUNT));
			ArrayList<DeTai> arrDT = (ArrayList<DeTai>)model.getSearchListByFaculty(idFaculty,year,type_detai, type_stat, offset, DT_sum);
			for (DeTai obj : arrDT) {
				if( LibraryConstant.TOPICTYPE_CAPCOSO.equals(obj.getCapDeTai())) dt_CCS++;
				else if (LibraryConstant.TOPICTYPE_CAPDHDN.equals(obj.getCapDeTai())) dt_DHDN++;
				else if (LibraryConstant.TOPICTYPE_CAPBGD.equals(obj.getCapDeTai())) dt_BGD++;
				
				if(LibraryConstant.DaHoanThanh.equals(obj.getTrangThai())) dt_hoanthanh++;
				else if(LibraryConstant.DangThucHien.equals(obj.getTrangThai())) dt_dangthuchien++;
			}
			request.setAttribute("filter_year", year);
			request.setAttribute("filter_type_stat", type_stat);
			request.setAttribute("filter_type_detai", type_detai);
			request.setAttribute("dt_CCS", dt_CCS);
			request.setAttribute("dt_DHDN", dt_DHDN);
			request.setAttribute("dt_BGD", dt_BGD);
			request.setAttribute("dt_hoanthanh", dt_hoanthanh);
			request.setAttribute("dt_dangthuchien", dt_dangthuchien);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/faculty_statistical.jsp");
			rd.forward(request, response);
		}
	}

}
