package library;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LibraryAuth {
	
      public static boolean CheckLogin(HttpServletRequest request ,HttpServletResponse response) throws IOException{
    	//kiểm tra đã đăng nhập chưa
    	//true : login roi
      	//fail : chua
          HttpSession session = request.getSession();
          if(session.getAttribute("sobjUserAdmin")==null){//chưa đăng nhâp 
         	 //chuyen huong
         	 response.sendRedirect(request.getContextPath()+"/auth/admin/login");
         	 return false;
          }
          
          return true;
      
      }
      
      
      
      public static boolean CheckLoginPublic(HttpServletRequest request ,HttpServletResponse response) throws IOException{
      	//kiểm tra đã đăng nhập chưa
      	//true : login roi
        	//fail : chua
            HttpSession session = request.getSession();
            if(session.getAttribute("sobjUserPublic")==null){//chưa đăng nhâp 
           	 //chuyen huong
           	 response.sendRedirect(request.getContextPath()+"/auth/public/login");
           	 return false;
            }
            
            return true;
        
        }
      
      
}
