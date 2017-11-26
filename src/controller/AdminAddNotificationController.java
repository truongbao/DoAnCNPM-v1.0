package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.bean.ThongBao;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.ThongBaoDAO;
import model.dao.UserDAO;

public class AdminAddNotificationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminAddNotificationController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
       
         
      // kiá»ƒm tra Ä‘Ã£ Ä‘Äƒng nháº­p chÆ°a
  		 if( !LibraryAuth.CheckLogin(request, response)){
  			 return;
  		 }
  		 HttpSession session = request.getSession();
  		 User objUser = new User();
  		 int idFaculty = 0;
  		 if(session.getAttribute("quanLyNCKHKhoa") != null){
  			objUser = (User) session.getAttribute("quanLyNCKHKhoa");
  			idFaculty = objUser.getIdKhoa();
  		 }else if(session.getAttribute("nhanVienQLNCKHTruong") != null){
  			objUser = (User) session.getAttribute("nhanVienQLNCKHTruong");
  		 }else if(session.getAttribute("admin") != null){
  			objUser = (User) session.getAttribute("admin");
  		 }


  		UserDAO modelUser = new UserDAO();
  		DetaiDAO modelDT = new DetaiDAO();
  		ThongBaoDAO modelTB = new ThongBaoDAO();
  		if ("load".equals(request.getParameter("type"))) {
  			int DT_sum = modelUser.getSumGV(idFaculty,"");
  			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
  			int current_page = 1;
  			if (request.getParameter("page") != null) {
  				current_page = Integer.parseInt(request.getParameter("page"));
  			}
  			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
  			request.setAttribute("current_page", current_page);
  			request.setAttribute("page_sum", page_sum);
  			request.setAttribute("alTeacher",modelUser.getListGV(idFaculty,"", offset, LibraryConstant.ROW_COUNT));
  			RequestDispatcher rd = request.getRequestDispatcher("/admin/notification/add_notification.jsp");
  			rd.forward(request, response);
  		} else if ("search".equals(request.getParameter("type"))) {
  			String key_word = request.getParameter("key_words");
  			int DT_sum =  modelUser.getSumGV(idFaculty,key_word);
  			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
  			int current_page = 1;
  			if (request.getParameter("page") != null) {
  				current_page = Integer.parseInt(request.getParameter("page"));
  			}
  			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
  			request.setAttribute("current_page", current_page);
  			request.setAttribute("page_sum", page_sum);
  			request.setAttribute("alTeacher",modelUser.getListGV(idFaculty,key_word, offset, LibraryConstant.ROW_COUNT));
  			request.setAttribute("filter_key_words", key_word);
  			RequestDispatcher rd = request.getRequestDispatcher("/admin/notification/add_notification.jsp");
  			rd.forward(request, response);
  		} else if("submit".equals(request.getParameter("type"))){
  			try{
	  			int id_userDen = Integer.parseInt((String)request.getParameter("id_GV"));
	  			String noiDung = (String) request.getParameter("noiDung");
	  			int id_deTai = Integer.parseInt((String)request.getParameter("deTai"));
	  			DeTai objDT = modelDT.getObjDeTai(id_deTai);
	  			Date date = new Date();

	  			ThongBao objTB = new ThongBao(0, objUser.getIdUser(), id_userDen, noiDung, new Timestamp(date.getTime()), id_deTai, objDT.getTenDeTai(), objDT.getFullName(), objUser.getFullName(), 0);

	  			//ThongBao objTB = new ThongBao(0, objUser.getIdUser(), id_userDen, noiDung, new Timestamp(date.getTime()), id_deTai, "", "", "", 0);

	  			if(modelTB.addItem(objTB) > 0){ // thêm thành công
					response.sendRedirect(request.getContextPath()+"/admin/add-notif?type=load&msg=add1");
					return;
				}else { // thêm thất bại
					response.sendRedirect(request.getContextPath()+"/admin/add-notif?type=load&msg=add0");
				}
  			}catch (NumberFormatException e) {
	  			System.err.println(e);
			}
  		}
	}

}
