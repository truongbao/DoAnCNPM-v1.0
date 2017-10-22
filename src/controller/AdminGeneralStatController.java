package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.DeTai;
import model.bean.Khoa;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.FacultyDAO;
import model.dao.UserDAO;
import constant.define;

public class AdminGeneralStatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminGeneralStatController() {
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
		ArrayList<DeTai> arrDT = new ArrayList<DeTai>();
		// kiá»ƒm tra Ä‘Ã£ Ä‘Äƒng nháº­p chÆ°a
		// if( LibraryAuth.CheckLogin(request, response)==false){
		// return;
		// }
		// HttpSession session = request.getSession();
		// User objUser = (User) session.getAttribute("objUser");
		// int idFaculty = objUser.getIdKhoa();

		FacultyDAO modelFaculty = new FacultyDAO();
		request.setAttribute("list_Faculty", modelFaculty.getList());
		DetaiDAO modelDT = new DetaiDAO();
		
		
		if ("load".equals(request.getParameter("type"))) {		
			int DT_sum = modelDT.getSum();
			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
			int current_page = 1;
			if (request.getParameter("page") != null) {
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
			request.setAttribute("current_page", current_page);
			request.setAttribute("page_sum", page_sum);
			request.setAttribute("alItem",modelDT.getListDeTai( offset, LibraryConstant.ROW_COUNT));
			arrDT = (ArrayList<DeTai>)modelDT.getListDeTai();
			for (DeTai obj : arrDT) {
				if( "Cấp cơ sở".equals(obj.getCapDeTai())) dt_CCS++;
				else if ("Đại học Đà Nẵng".equals(obj.getCapDeTai())) dt_DHDN++;
				else if ("Bộ giáo dục".equals(obj.getCapDeTai())) dt_BGD++;
				
				if("Đã hoàn thành".equals(obj.getTrangThai())) dt_hoanthanh++;
				else if("Đang thực hiện".equals(obj.getTrangThai())) dt_dangthuchien++;
			}
			request.setAttribute("dt_CCS", dt_CCS);
			request.setAttribute("dt_DHDN", dt_DHDN);
			request.setAttribute("dt_BGD", dt_BGD);
			request.setAttribute("dt_hoanthanh", dt_hoanthanh);
			request.setAttribute("dt_dangthuchien", dt_dangthuchien);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/general_statistical.jsp");
			rd.forward(request, response);
		} else if ("search".equals(request.getParameter("type"))) {
			int page_sum = 0;
			int current_page = 1;
			int year = Integer.parseInt(request.getParameter("year_create"));
			String type_stat = request.getParameter("type_stat");
			String type_detai =(String) request.getParameter("type_detai");
			int idFaculty = Integer.parseInt(request.getParameter("faculty"));
			if(idFaculty != 0){
				int DT_sum =  modelDT.getSumWithIdFacultyAndSearch(idFaculty, year,type_detai,type_stat);
				page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
				if (request.getParameter("page") != null) {
					current_page = Integer.parseInt(request.getParameter("page"));
				}
				int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
				request.setAttribute("alItem",modelDT.getSearchListByFaculty(idFaculty,year,type_detai, type_stat, offset, LibraryConstant.ROW_COUNT));
				arrDT = (ArrayList<DeTai>)modelDT.getSearchListByFaculty(idFaculty,year,type_detai, type_stat, offset, DT_sum);
			}else{
				int DT_sum =  modelDT.getSumWithSearch(year,type_detai,type_stat);
				page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
				if (request.getParameter("page") != null) {
					current_page = Integer.parseInt(request.getParameter("page"));
				}
				int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
				request.setAttribute("alItem",modelDT.getSearchList(year,type_detai, type_stat, offset, LibraryConstant.ROW_COUNT));
				arrDT = (ArrayList<DeTai>)modelDT.getSearchList(year,type_detai, type_stat, offset, DT_sum);
			}
			for (DeTai obj : arrDT) {
				if( "Cấp cơ sở".equals(obj.getCapDeTai())) dt_CCS++;
				else if ("Đại học Đà Nẵng".equals(obj.getCapDeTai())) dt_DHDN++;
				else if ("Bộ giáo dục".equals(obj.getCapDeTai())) dt_BGD++;
				if("Đã hoàn thành".equals(obj.getTrangThai())) dt_hoanthanh++;
				else if("Đang thực hiện".equals(obj.getTrangThai())) dt_dangthuchien++;
			}
			request.setAttribute("filter_year", year);
			request.setAttribute("filter_type_stat", type_stat);
			request.setAttribute("filter_type_detai", type_detai);
			request.setAttribute("filter_faculty", modelFaculty.getItem(idFaculty));
			request.setAttribute("current_page", current_page);
			request.setAttribute("page_sum", page_sum);
			request.setAttribute("dt_CCS", dt_CCS);
			request.setAttribute("dt_DHDN", dt_DHDN);
			request.setAttribute("dt_BGD", dt_BGD);
			request.setAttribute("dt_hoanthanh", dt_hoanthanh);
			request.setAttribute("dt_dangthuchien", dt_dangthuchien);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/general_statistical.jsp");
			rd.forward(request, response);
		}
	}

}
