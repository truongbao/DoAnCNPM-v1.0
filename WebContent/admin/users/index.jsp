<%@page import="java.util.Calendar"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.LoaiTaiKhoan"%>
<%@page import="model.bean.Khoa"%>
<%@page import="model.bean.HocVi"%>
<%@page import="model.dao.UserDAO"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   
   <script type="text/javascript">
			     $(document).ready(function(){
						$("#frmUser").validate({
							rules:{
								"username":{
									required: true,
									minlength: 6,
									maxlength: 32
								},
								"password":{
									required: true,
									minlength: 6,
									maxlength: 15
								},
								"fullname":{
									required: true,
									minlength: 6,
									maxlength: 32
								},
								"email":{
									 required:true,
									  email:true
									
								}
							},
							messages:{
								"username":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào username</span> </p>",
									minlength:"<p><span style='color:red' >   username phải có ít nhất 6 ký tự  </span></p>",
									maxlength: "<p> <span style='color:red' >  username có nhiều nhất 32 ký tự  </span></p>"
								},
								"password":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào password </span> </p>",
									minlength:"<p><span style='color:red' > password phải có ít nhất 6 ký tự  </span></p>",
									maxlength: "<p> <span style='color:red' >  password có nhiều nhất 15 ký tự  </span></p>"
								},
								"fullname":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào fullname</span> </p>",
									minlength:"<p><span style='color:red' >  fullname phải có ít nhất 6 ký tự  </span></p>",
									maxlength: "<p> <span style='color:red' >  fullname có nhiều nhất 32 ký tự  </span></p>"
								},
								"email":{
								    required:"<p style='color:red'> Vui lòng nhập email </p>",	
			                        email: "<p style='color:red'> Vui lòng nhập đúng định dạng email : ai_do@example.com </p> "	
								}
							}
						});// ham tren
			     });
			</script>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
	                            <h4 class="title">Danh sách tài khoản</h4>
	                        
                                <div class="col-md-9 col-md-offset-1">
                                    <form action=""<%=request.getContextPath() %>/admin/user/index" method="get">
                                    	<div class="row">
                                    		
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <input type="text" 
	                                                    name="key" class="form-control border-input"
	                                                    value="<%=request.getParameter("key") != null ? request.getParameter("key") : "" %>">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <select name="loai_tai_khoan" class="form-control border-input">
                                                    	<option value="0">-- Tất cả loại tài khoản--</option>
                                                    	<% 

	                                                    	UserDAO userDao = new UserDAO();
                                                    		
					                                    	ArrayList<LoaiTaiKhoan> listLoaiTaiKhoan = userDao.getListLoaiTK();
					                                    	for(int i = 0; i < listLoaiTaiKhoan.size(); i++) {
					                                    		if (request.getParameter("loai_tai_khoan") != null) {
					                                    		if (Integer.parseInt(request.getParameter("loai_tai_khoan")) == listLoaiTaiKhoan.get(i).getIdLoaiTaiKhoan()) {
					                                    %>
					                                    <option value="<%= listLoaiTaiKhoan.get(i).getIdLoaiTaiKhoan() %>" selected><%= listLoaiTaiKhoan.get(i).getTenLoaiTaiKhoan() %></option>
                                                    	        <%} else {%>
                                                    	<option value="<%= listLoaiTaiKhoan.get(i).getIdLoaiTaiKhoan() %>"><%= listLoaiTaiKhoan.get(i).getTenLoaiTaiKhoan() %></option>
                                                    		<%	  }
					                                    	} else {%>
					                                    	<option value="<%= listLoaiTaiKhoan.get(i).getIdLoaiTaiKhoan() %>"><%= listLoaiTaiKhoan.get(i).getTenLoaiTaiKhoan() %></option>
                                                    		
					                                    	<%}
					                                    	}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                            	<div class="form-group">
    		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-primary btn-search" />
    		                                        <input type="submit" name="cancel" value="Hủy tìm kiếm" class="btn btn-primary btn-cancel-search" />
    	                                        </div>
                                            </div>
                                        </div>
                                        
                                    </form>
                                </div>
                                
                                <div style="display: inline;" class='<%=request.getSession().getAttribute("admin") == null? "disabled" : "" %>'><a href="<%=request.getContextPath() %>/admin/user/form-add" class="addtop"><img src="<%=request.getContextPath() %>/templates/admin/img/add.png" alt="" /> Thêm</a></div>
                            	<div class="text-center text-danger col-md-12" style="font-size: 18px;font-weight: bold;">
                            	 <%
							      if(request.getParameter("msg")!=null){
							          int msg = Integer.parseInt( request.getParameter("msg") );
							          switch(msg){
							            case 1: out.print("Xử lý thành công !!");break;
							            case 0: out.print("Không thành công vui lòng thử lại !!");break;
							            case 4: out.print("User không tồn tại !!");break;
								        }
							      }
		     			       	%>  
                            	</div>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped" id="table-contain">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Username</th>
                                    	<th>Họ tên</th>
                                    	<th>Chức danh khoa học</th>
                                    	<th>Email</th>
                                        <th>Loại tài khoản</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                    <% 
                                    if(request.getAttribute("listUsers")!=null){
		                            	ArrayList<User> listUsers = (ArrayList<User>)request.getAttribute("listUsers");
                                    	for(int i = 0; i < listUsers.size(); i++) {
                                    %>
                                        <tr>
                                        	<td><%= listUsers.get(i).getIdUser() %></td>
                                        	<td><a href="<%=request.getContextPath() %>/admin/user/show?uid=<%=listUsers.get(i).getIdUser() %>"><%=listUsers.get(i).getUserName() %></a></td>
                                        	<td><%= listUsers.get(i).getFullName() %></td>
                                        	<td><%= listUsers.get(i).getChucDanhKhoaHoc() %></td>
                                        	<td><%= listUsers.get(i).getEmail() %></td>
                                            <td><%=listUsers.get(i).getTenLoaiTaiKhoan() %></td>
                                        	<td>
                                                <div style="display: inline;" class='<%=request.getSession().getAttribute("admin") == null? "disabled" : "" %>'><a href="<%=request.getContextPath() %>/admin/user/form-edit?uid=<%=listUsers.get(i).getIdUser() %>"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /></a></div> &nbsp;||&nbsp;
                                                <form id="form-xoa" method="post" action="<%=request.getContextPath() %>/admin/user/del?uid=<%=listUsers.get(i).getIdUser() %>" style="display: inline" class='<%=request.getSession().getAttribute("admin") == null? "disabled" : "" %>'>
                                                	
						                           <a href="" class='link-xoa'
						                              data-title="Confirm deletion!"
						                              data-confirm="Are you sure you want to delete?">
						                              <img src="<%= request.getContextPath() %>/templates/admin/img/del.gif" alt="" />
						                            </a>
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
										   <a href="<%=request.getContextPath() %>/admin/user/index?page=<%=current_page-1%>">Back</a> 
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
										   <a <%=active %> href="<%=request.getContextPath() %>/admin/user/index?page=<%=i%>"><%=i %></a>
										     	
										   <%}//for %> 	
										    	
										
										  <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage > 1){
										  %>
										  
										 	 <a href="<%=request.getContextPath() %>/admin/user/index?page=<%=current_page+1%>">Next</a> 
										 	
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
       