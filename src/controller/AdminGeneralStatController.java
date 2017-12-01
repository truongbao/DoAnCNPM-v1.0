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
import model.bean.CapDeTai;
import model.bean.DeTai;
import model.dao.CapDeTaiDAO;
import model.dao.DetaiDAO;
import model.dao.FacultyDAO;

public class AdminGeneralStatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int year = 0;
	String type_stat = null;
	String type_detai = null;
	int idFaculty_search = 0;
	String cdt = null;
	ArrayList<DeTai> list_export = new ArrayList<>();
	
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
		
		// kiá»ƒm tra Ä‘Ã£ Ä‘Äƒng nháº­p chÆ°a
		 if( !(LibraryAuth.CheckNhanVienTruong(request, response))){
			 return;
		 }
		String tenKhoa = null;
		String trangThai = null;
		FacultyDAO modelFaculty = new FacultyDAO();
		request.setAttribute("list_Faculty", modelFaculty.getList());
		DetaiDAO modelDT = new DetaiDAO();
		CapDeTaiDAO cdtModel = new CapDeTaiDAO();
		request.setAttribute("alCDT", cdtModel.getItemsByPage());
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
			list_export = modelDT.getListDeTaiEx();
			RequestDispatcher rd = request.getRequestDispatcher("/admin/general_statistical.jsp");
			rd.forward(request, response);
		} else if ("search".equals(request.getParameter("type"))) {
			int page_sum = 0;
			int current_page = 1;
			if(request.getParameter("year_create") != null && request.getParameter("type_stat")!= null && request.getParameter("type_detai")!= null && request.getParameter("faculty") != null){
				if(!"0".equals(request.getParameter("year_create"))){
					year = Integer.parseInt(request.getParameter("year_create"));
				}else{
					year = 0;
				}
				if(!"0".equals(request.getParameter("type_stat"))){
					type_stat = request.getParameter("type_stat");
					trangThai = LibraryConstant.ConvertTrangThai(type_stat);
				}else{
					type_stat = "0";
				}
				if(!"0".equals(request.getParameter("type_detai"))){
					type_detai =(String) request.getParameter("type_detai");
					cdt = cdtModel.getItem(Integer.parseInt(type_detai)).getTenCapDeTai();
				}else{
					type_detai = "0";
				}
				if(!"0".equals(request.getParameter("faculty"))){
					idFaculty_search = Integer.parseInt(request.getParameter("faculty"));
					tenKhoa = modelFaculty.getItem(idFaculty_search).getTenKhoa();
				}else{
					idFaculty_search = 0;
				}
				
			}
			
			if(idFaculty_search != 0){
				int DT_sum =  modelDT.getSumWithIdFacultyAndSearch(idFaculty_search, year,type_detai,type_stat);
				page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
				if (request.getParameter("page") != null) {
					current_page = Integer.parseInt(request.getParameter("page"));
				}
				int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
				request.setAttribute("alItem",modelDT.getSearchListByFaculty(idFaculty_search,year,type_detai, type_stat, offset, LibraryConstant.ROW_COUNT));
				list_export = modelDT.getSearchListByFaculty(idFaculty_search, year, type_detai, type_stat);
			}else{
				int DT_sum =  modelDT.getSumWithSearch(year,type_detai,type_stat);
				page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
				if (request.getParameter("page") != null) {
					current_page = Integer.parseInt(request.getParameter("page"));
				}
				int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
				request.setAttribute("alItem",modelDT.getSearchList(year,type_detai, type_stat, offset, LibraryConstant.ROW_COUNT));
				list_export = modelDT.getSearchList(year,type_detai, type_stat);
			}
			request.setAttribute("filter_year", year);
			request.setAttribute("filter_type_stat", type_stat);
			request.setAttribute("filter_type_detai", cdt);
			request.setAttribute("filter_faculty", modelFaculty.getItem(idFaculty_search));
			request.setAttribute("current_page", current_page);
			request.setAttribute("page_sum", page_sum);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/general_statistical.jsp");
			rd.forward(request, response);
		}else if ("export".equals(request.getParameter("type"))) {
			
			request.setAttribute("khoa", tenKhoa);
	       	request.setAttribute("tenCDT", cdt);
	       	request.setAttribute("nam", year);
	       	request.setAttribute("trangThai", trangThai);
	 		request.setAttribute("listDeTai", list_export);
	 		System.out.println(list_export.size());
	 		RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/nhanvien/export_file_thong_ke.jsp");
	        rd.forward(request, response);
			
		}
	}

}
