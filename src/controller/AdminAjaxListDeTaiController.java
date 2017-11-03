package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.DeTai;
import model.dao.UserDAO;


/**
 * Servlet implementation class ControllerAjaxActive
 */
@WebServlet("/ControllerAjaxActive")
public class AdminAjaxListDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAjaxListDeTaiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
		PrintWriter out = response.getWriter();
		try{
			UserDAO model = new UserDAO();
			int idUser = Integer.parseInt((String) request.getParameter("idUser"));
			ArrayList<DeTai> alDeTai = (ArrayList<DeTai>) model.getListDeTaiByIdUser(idUser);
			String result = "";
			for (DeTai deTai : alDeTai) {
				result += "<option value='"+deTai.getIdDeTai()+"'>"+deTai.getTenDeTai()+"</option>";
			}
			out.print(result);
		}catch (NumberFormatException e) {
			System.err.println(e);
		}
	}

}
