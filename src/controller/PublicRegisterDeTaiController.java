package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import library.StringLibrary;
import model.bean.DeTai;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.ThoiGianDKDAO;
import model.dao.UserDAO;

public class PublicRegisterDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicRegisterDeTaiController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    //kiểm tra đã đăng nhập ở public chưa
		if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		}
		
		UserDAO userDAO = new UserDAO();
	    DetaiDAO detaiDAO =new DetaiDAO();
		
		//kiem tra có phải trong thời gian đăng ký ko
		ThoiGianDKDAO thoiGianDKDAO = new ThoiGianDKDAO();
		Date thoiGianKetThucDKDeXuat = thoiGianDKDAO.getItem(1).getThoiGianKetThuc();
		
		Date thoiGianKetThucThuyetMinh = thoiGianDKDAO.getItem(2).getThoiGianKetThuc();
		Date now = new Date();
		if (now.after(thoiGianKetThucDKDeXuat)) {
			
			//detaiDAO.changeTrangThaiDangChoDuyetDeXuat();
			
			 //lấy thông tin đối tượng sobjUserPublic 
		     User objUser = null;
			 HttpSession session = request.getSession();
			
	         if(session.getAttribute("sobjUserPublic")!=null){
	            objUser = (User)session.getAttribute("sobjUserPublic");
	         }
	        
	        //phân trang
	         int current_page = 1;		
			 int row_count = LibraryConstant.ROW_COUNT; //5 tin trên 1 page
			
			 
			 //tong so danh muc
			 int sumDeTaiDK = detaiDAO.countDeTaiDKPublic(objUser.getIdUser());
			 
			 //tong so trang
			int sumPage = (int) Math.ceil((float)sumDeTaiDK / row_count) ;
			request.setAttribute("sumPage", sumPage);
			
			//lấy số trang hiện tại
			if (request.getParameter("page") != null){
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			 
			// tính offset
			int offset = (current_page - 1) * row_count;
			request.setAttribute("current_page", current_page);
			

			 //lấy danh sach đề tai do user đang login đăng ký
			 request.setAttribute("listDeTaiDK", detaiDAO.getListDeTaiDK( objUser.getIdUser(), offset,row_count ) );
			 
			 request.setAttribute("tb1", "Hiện không phải thời gian đề xuất đề tài !");
			
			 RequestDispatcher rd = request.getRequestDispatcher("/list_register_detai.jsp");
	         rd.forward(request, response);     
			
		}else{
		    request.setAttribute("listLinhVucNC", detaiDAO.getListLinhVucNC());
		    
		    request.setAttribute("listCapDeTai", detaiDAO.getListCapDeTai());
		    
			//lấy thông tin đối tượng sobjUserPublic 
		    User objUser = null;
			HttpSession session = request.getSession();
			
	        if(session.getAttribute("sobjUserPublic")!=null){
	            objUser = (User)session.getAttribute("sobjUserPublic");
	        }
	        
	        request.setAttribute("objUser", objUser);
			
			 RequestDispatcher rd = request.getRequestDispatcher("/register_detai.jsp");
	         rd.forward(request, response);
		}
		
		
	
         
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
		
		//kiểm tra đã đăng nhập ở public chưa
		if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		}
		
		DetaiDAO detaiDAO = new DetaiDAO();
		
		User objUser = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
		
		String tenDeTai = request.getParameter("tenDeTai");
		int idLinhVucNghienCuu = Integer.parseInt(request.getParameter("idLinhVucNghienCuu"));
		int idCapDeTai = Integer.parseInt(request.getParameter("idCapDeTai"));
		String tinhCapThiet = request.getParameter("tinhCapThiet");
		String mucTieu = request.getParameter("mucTieu");
		String noiDung = request.getParameter("noiDungChinh");
		String sanPham = request.getParameter("sanPham");
		String hieuQua = request.getParameter("hieuQuaDukien");
		int kinhPhiThucHien = Integer.parseInt(request.getParameter("kinhPhiThucHien"));
		int idUser = objUser.getIdUser();
		
		
		Timestamp thoiGianBatDau = null;
		Timestamp thoiGianKetThuc = null;
		
		String  tgbd = request.getParameter("thoiGianBatDau");
		String  tgkt = request.getParameter("thoiGianKetThuc");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			thoiGianBatDau = new Timestamp(((java.util.Date)df.parse(tgbd)).getTime());
			thoiGianKetThuc = new Timestamp(((java.util.Date)df.parse(tgkt)).getTime());
			System.out.println("thoi gian bat dau : "+thoiGianBatDau+"--thoi gian ket thuc : "+thoiGianKetThuc);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		DeTai objDeTai = new DeTai( 0, tenDeTai, "", idLinhVucNghienCuu,
									"", 0, "", thoiGianBatDau, thoiGianKetThuc, "Đại học bách khoa - Đại học đà nẵng", idUser, "", "", "",
									tinhCapThiet, mucTieu, "", "",
									noiDung, sanPham, hieuQua, kinhPhiThucHien, 
									LibraryConstant.DangThoiGianDK, idCapDeTai,"", null, objUser.getIdKhoa());
        
		 if(detaiDAO.addDeTaiPublic(objDeTai) >  0){
				response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=1");
				return;
		 }else{
				response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=0");
				return;
		 }
		
		
		
		
	}
	
	
	

}
