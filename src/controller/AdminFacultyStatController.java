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
import model.bean.CapDeTai;
import model.bean.DeTai;
import model.bean.User;
import model.dao.CapDeTaiDAO;
import model.dao.DetaiDAO;

public class AdminFacultyStatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int year = 0;
	String type_stat = "0";
	String type_detai = "0";
	String cdt = null;
	
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
		
		
		// kiá»ƒm tra Ä‘Ã£ Ä‘Äƒng nháº­p chÆ°a
		 if( !LibraryAuth.CheckQuanLyKhoa(request, response)){
			 return;
		 }
		 HttpSession session = request.getSession();
		 User objUser = (User) session.getAttribute("quanLyNCKHKhoa");
		 int idFaculty = objUser.getIdKhoa();

//		int idFaculty = 6;
		DetaiDAO model = new DetaiDAO();
		CapDeTaiDAO cdtModel  =  new CapDeTaiDAO();
		request.setAttribute("alCDT", cdtModel.getItemsByPage());
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
			RequestDispatcher rd = request.getRequestDispatcher("/admin/faculty_statistical.jsp");
			rd.forward(request, response);
		} else if ("search".equals(request.getParameter("type"))) {
			if(request.getParameter("year_create") != null && request.getParameter("type_stat")!= null && request.getParameter("type_detai")!= null){
				if(!"0".equals(request.getParameter("year_create"))){
					year = Integer.parseInt(request.getParameter("year_create"));
				}else{
					year = 0;
				}
				if(!"0".equals(request.getParameter("type_stat"))){
					type_stat = request.getParameter("type_stat");
				}else{
					type_stat = "0";
				}
				if(!"0".equals(request.getParameter("type_detai"))){
					type_detai =(String) request.getParameter("type_detai");
					cdt = cdtModel.getItem(Integer.parseInt(type_detai)).getTenCapDeTai();
				}else{
					type_detai = "0";
				}
			}
			
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
			request.setAttribute("filter_year", year);
			request.setAttribute("filter_type_stat", type_stat);
			request.setAttribute("filter_type_detai", cdt);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/faculty_statistical.jsp");
			rd.forward(request, response);
		}
	}

}
