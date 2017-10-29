<%@page import="java.util.Calendar"%>
<%@page import="model.bean.HopDong"%>
<%@page import="model.dao.HopDongDAO"%>
<%@page import="model.dao.UserDAO"%>
<%@page import="model.dao.DetaiDAO"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
	                            <h4 class="title">Quản lý hơp đồng</h4>
                                
                                <a href="<%=request.getContextPath() %>/admin/hopdong/form-add" class="addtop"><img src="<%=request.getContextPath() %>/templates/admin/img/add.png" alt="" /> Thêm</a>
                            	<div class="text-center text-danger col-md-12" style="font-size: 18px;font-weight: bold;">
                            	 <%
							      if(request.getParameter("msg")!=null){
							          int msg = Integer.parseInt( request.getParameter("msg") );
							          switch(msg){
							            case 1: out.print("Xử lý thành công !!");break;
							            case 0: out.print("Không thành công vui lòng thử lại !!");break;
							            case 4: out.print("Hợp đồng không tồn tại !!");break;
								        }
							      }
		     			       	%>  
                            	</div>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped" id="table-contain">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Tên khách hàng</th>
                                    	<th>Giảng viên</th>
                                        <th>Đề tài</th>
                                    	<th>Trạng thái</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                    <% 
                                    UserDAO userDAO = new UserDAO();
                                    DetaiDAO detaiDAO = new DetaiDAO();
                                    if(request.getAttribute("listHopDong")!=null){
		                            	ArrayList<HopDong> listHopDong = (ArrayList<HopDong>)request.getAttribute("listHopDong");
                                    	for(int i = 0; i < listHopDong.size(); i++) {
                                    %>
                                        <tr>
                                        	<td><%= listHopDong.get(i).getIdHopDong() %></td>
                                        	<td><%=listHopDong.get(i).getTenKhachHang() %></td>
                                        	<td><%= userDAO.getObjUser(listHopDong.get(i).getIdGiangVien()).getFullName() %></td>
                                        	<td><%= detaiDAO.getObjDeTai(listHopDong.get(i).getIdDeTai()).getTenDeTai() %></td>
                                            <td><%=listHopDong.get(i).getTrangThaiHopDong() %></td>
                                        	<td>
                                                <a href="<%=request.getContextPath() %>/admin/hopdong/form-edit?uid=<%=listHopDong.get(i).getIdHopDong() %>"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /></a> &nbsp;||&nbsp;
                                                <form id="form-xoa" method="post" action="<%=request.getContextPath() %>/admin/hopdong/del?uid=<%=listHopDong.get(i).getIdHopDong() %>" style="display: inline">
                                                	
						                           <a href="" class="link-xoa"
						                              data-title="Confirm deletion!"
						                              data-confirm="Are you sure you want to delete?">
						                              <img src="<%= request.getContextPath() %>/templates/admin/img/del.gif" alt="" />
						                            </a>
                         						 </form> &nbsp;||&nbsp;
                         						 <form method="post" target="_blank"  action="<%=request.getContextPath() %>/admin/hopdong/export?uid=<%=listHopDong.get(i).getIdHopDong() %>" style=" display: inline;">
                         						 	<button class="btn btn-info btn-sm">Xuất</button>
                         						 </form>
                                            </td>
                                        </tr>
                    			<% } 
                    			}%>
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
										   <a href="<%=request.getContextPath() %>/admin/hopdong/index?page=<%=current_page-1%>">Back</a> 
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
										   <a <%=active %> href="<%=request.getContextPath() %>/admin/hopdong/index?page=<%=i%>"><%=i %></a>
										     	
										   <%}//for %> 	
										    	
										
										  <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage > 1){
										  %>
										  
										 	 <a href="<%=request.getContextPath() %>/admin/hopdong/index?page=<%=current_page+1%>">Next</a> 
										 	
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
                          

   <%@include file="/templates/admin/inc/modal.jsp" %>
  <%@include file="/templates/admin/inc/footer.jsp" %>
       