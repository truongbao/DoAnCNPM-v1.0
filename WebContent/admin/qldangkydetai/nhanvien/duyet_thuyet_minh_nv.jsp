<%@page import="library.LibraryConstant"%>
<%@page import="model.bean.DeTai"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Khoa"%>
<%@page import="model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <div class="col-md-12">

                                    <form action="<%=request.getContextPath() %>/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv" method="get">
                                    	<div class="row">
                                    		
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <input type="text" 
	                                                    name="key" class="form-control border-input"
	                                                    value="<%=request.getParameter("key") != null ? request.getParameter("key") : "" %>" 
	                                                    placeholder="Nhập tên đề tài, tên chủ nhiệm cần tìm kiếm" >
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <select name="khoa" class="form-control border-input">
                                                    	<option value="0">-- Tất cả các khoa--</option>
                                                    	<% 

	                                                    	UserDAO userDao = new UserDAO();
                                                    		
					                                    	ArrayList<Khoa> listKhoa = userDao.getListKhoa();
					                                    	for(int i = 0; i < listKhoa.size(); i++) {
					                                    		if (request.getParameter("khoa") != null) {
					                                    		if (Integer.parseInt(request.getParameter("khoa")) == listKhoa.get(i).getIdKhoa()) {
					                                    %>
					                                    <option value="<%= listKhoa.get(i).getIdKhoa() %>" selected><%= listKhoa.get(i).getTenKhoa() %></option>
                                                    	        <%} else {%>
                                                    	<option value="<%= listKhoa.get(i).getIdKhoa() %>"><%= listKhoa.get(i).getTenKhoa() %></option>
                                                    		<%	  }
					                                    	} else {%>
					                                    	<option value="<%= listKhoa.get(i).getIdKhoa() %>"><%= listKhoa.get(i).getTenKhoa() %></option>
                                                    		
					                                    	<%}
					                                    	}%>
                                                    </select>
                                                </div>
                                            </div>
                                            
                                            <div class="col-md-3">
                                            	<div class="form-group">
    		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-primary btn-search" />
    		                                        <input type="submit" name="cancel" value="Hủy tìm kiếm" class="btn btn-primary btn-cancel-search" />
    	                                        </div>
                                            </div>
                                        </div>
                                        
                                    </form>
                                </div>
                                
                            </div>
                           <div class="text-center text-danger col-md-12" style="font-size: 18px;font-weight: bold;">
                            	 <%
							      if(request.getParameter("msg")!=null){
							          int msg = Integer.parseInt( request.getParameter("msg") );
							          switch(msg){
							            case 1: out.print("Xử lý thành công !!");break;
							            case 0: out.print("Không thành công vui lòng thử lại !!");break;
								        }
							      }
		     			       	%>  
                            	</div>
                            <div class="content table-responsive table-full-width">
                            <h3>DANH SÁCH THUYẾT MINH CẦN DUYỆT</h3>
                                <table class="table table-striped" id="table-contain">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên đề tài</th>
                                    	<th>Chủ nhiệm</th>
                                    	<th>Cấp đề tài</th>
                                    	<th>Trạng thái</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                    <%
                                       if(request.getAttribute("listDeTaiNhanVien") != null) {
                                    	   ArrayList<DeTai> listDeTaiByIdKhoa = (ArrayList<DeTai>)request.getAttribute("listDeTaiNhanVien");
                                    	   if (listDeTaiByIdKhoa.size() > 0) {
                                    		   for (DeTai objDeTai : listDeTaiByIdKhoa){
                                    
                                    %>
                                        <tr>
                                        	<td><%=objDeTai.getIdDeTai() %></td>
                                            <td><a href="<%=request.getContextPath()%>/admin/qldangkydetai/nhanvien/detail_duyet_tm_nv?did=<%=objDeTai.getIdDeTai()%>"><%=objDeTai.getTenDeTai() %></a></td>
                                            <td><%=objDeTai.getFullName() %></td>
                                        	<td><%=objDeTai.getCapDeTai() %></td>
                                        	<td><%=LibraryConstant.ConvertTrangThai(objDeTai.getTrangThai()) %></td>
                                        	<td>
                                        		<a href="<%=request.getContextPath()%>/admin/qldangkydetai/nhanvien/detail_duyet_tm_nv?did=<%=objDeTai.getIdDeTai()%>"><img src="assets/img/edit.gif" alt="" /> Xem</a>
                                        	</td>
                                        </tr>
                                        
                                      <%}}} %>  
                                      <tr class="text-center text-danger mt-20 no-result-search" hidden>
                                             <td colspan="7"><h5>Không tìm thấy kết quả</h5></td>
                                        </tr>                             
                                    </tbody>
                                </table>

								<div class="text-center">
								    <ul class="pagination">
								       <li>
								    	<%
											int sumPage = (Integer) request.getAttribute("sumPage");
										    int current_page = (Integer) request.getAttribute("current_page");
										    int pageFirst = 0;
										    int pageEnd = 0;
										    int numFix = 5;
										    int move = (int)Math.ceil( (float)numFix / 2);
										    //nếu current_page > 1 và sumPage > 1 thì thêm nút back
										    if(current_page > 1 && sumPage > 1){
										 %>  
										   <a href="<%=request.getContextPath() %>/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?page=<%=current_page-1%>">Back</a> 
										 <%} %>  	
										    	
										    	
										 <%
										     //fix lại trang đầu và cuối
										     if(current_page >=numFix){
										    	 pageFirst = current_page-move;
										    	 if(sumPage > (current_page+move) ){
										    		 pageEnd = current_page+move;
										    	 }else if(current_page < sumPage && current_page > (sumPage-(numFix-1) ) ){
										    		 pageFirst = sumPage-(numFix-1);
										    		 pageEnd = sumPage;
										    	 }else{
										    		 pageEnd = sumPage;
										    	 }
										     }else{
												pageFirst=1;
												if(sumPage > numFix){
													pageEnd = numFix;
												}else{
													pageEnd = sumPage;
												}
										     }
										    //lặp khoản giữa và active trang người dùng click
										    String active="";
											for (int i = pageFirst; i <= pageEnd; i++){
												if(current_page==i){
													active=" style='color :red; font-weight: bold' ";
												}else{
													active="";
												}
										 
										 %>   	
										   <a <%=active %> href="<%=request.getContextPath() %>/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?page=<%=i%>"><%=i %></a>
										     	
										   <%}//for %> 	
										    	
										
										  <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage > 1){
										  %>
										  
										 	 <a href="<%=request.getContextPath() %>/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?page=<%=current_page+1%>">Next</a> 
										 	
										  <%} %>
								        
								        </li> 
								    </ul>
								</div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

       <%@include file="/templates/admin/inc/footer.jsp" %>
