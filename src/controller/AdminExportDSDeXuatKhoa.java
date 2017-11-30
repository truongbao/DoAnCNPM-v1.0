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

public class AdminExportDSDeXuatKhoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminExportDSDeXuatKhoa() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
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
		User objUser = null;
		 if(session.getAttribute("quanLyNCKHKhoa")!=null){
			 objUser = (User)session.getAttribute("quanLyNCKHKhoa");
		 }
		 
       	CapDeTaiDAO cdtDAO = new CapDeTaiDAO();
       	int idcapDeTai = 0;
       	if(request.getParameter("capDeTai") != null){
       		idcapDeTai = Integer.parseInt(request.getParameter("capDeTai"));
       	}
       	CapDeTai cdt = cdtDAO.getItem(idcapDeTai);
       	
       	ArrayList<DeTai> listDeTai = detaiDAO.getListDeTaiWithCapDeTai(LibraryConstant.DangChoXetCapTruong,idcapDeTai,objUser.getIdKhoa());
       	request.setAttribute("khoa", objUser.getTenKhoa());
       	request.setAttribute("objCDT", cdt);
 		 request.setAttribute("listDeTai", listDeTai);
 		 System.out.println("XUAT FILE DS: "+listDeTai.size());
  		RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/export.jsp");
          rd.forward(request, response);
	}

}
