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
	                            <h4 class="title" style="text-align: center;margin-bottom: 20px; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">Danh sách tài khoản</h4>
	                        
                                <div class="col-md-10 col-md-offset-1">
                                    <form action="" method="post">
                                    	<div class="row">
                                    		
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <input type="text" name="fullname" class="form-control border-input" placeholder="Họ tên" value="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <select name="loai_tai_khoan" class="form-control border-input">
                                                    	<option value="0">-- Tất cả loại tài khoản--</option>
                                                    	<% 

	                                                    	UserDAO userDao = new UserDAO();
	                                                    	ArrayList<User> listUsers = userDao.getItemsByPage(0);
					                                    	ArrayList<LoaiTaiKhoan> listLoaiTaiKhoan = userDao.getListLoaiTK();
					                                    	for(int i = 0; i < listLoaiTaiKhoan.size(); i++) {
					                                    %>
                                                    	<option value="<%= listLoaiTaiKhoan.get(i).getIdLoaiTaiKhoan() %>"><%= listLoaiTaiKhoan.get(i).getTenLoaiTaiKhoan() %></option>
                                                    	<%} %>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                            	<div class="form-group">
    		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-primary" />
    		                                        <input type="submit" name="reset" value="Hủy tìm kiếm" class="btn btn-primary" />
    	                                        </div>
                                            </div>
                                        </div>
                                        
                                    </form>
                                </div>
                                
                                <a href="<%=request.getContextPath() %>/admin/user/form-add" class="addtop"><img src="../assets/img/add.png" alt="" /> Thêm</a>
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
                                                <a href="<%=request.getContextPath() %>/admin/user/form-edit?uid=<%=listUsers.get(i).getIdUser() %>"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /></a> &nbsp;||&nbsp;
                                                <form id="form-xoa" method="post" action="<%=request.getContextPath() %>/admin/user/del?uid=<%=listUsers.get(i).getIdUser() %>" style="display: inline">
                                                	
						                           <a href="" class="link-xoa"
						                              data-title="Confirm deletion!"
						                              data-confirm="Are you sure you want to delete?">
						                              <img src="<%= request.getContextPath() %>/templates/admin/img/del.gif" alt="" />
						                            </a>
                         						 </form> 
                                            </td>
                                        </tr>
                    			<% } %>
                                        <tr class="text-center text-danger mt-20 no-result-search" hidden>
                                             <td colspan="7"><h5>Không tìm thấy kết quả</h5></td>
                                        </tr>
                                    </tbody>
 
                                </table>

								<div class="text-center">
								    <ul class="pagination">
								        <li><a href="?p=0" title="">1</a></li> 
								        <li><a href="?p=1" title="">2</a></li> 
								        <li><a href="?p=1" title="">3</a></li> 
								        <li><a href="?p=1" title="">4</a></li> 
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
       