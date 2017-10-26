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
								"namSinh":{
									required: true
								},
								"matKhau":{
									required: true,
									minlength: 6,
									maxlength: 20
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
								"matKhau":{
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
								},
								"namSinh":{
								    required:"<p style='color:red'> Vui lòng nhập ngày tháng năm sinh </p>",	
			                     }
							}
						});// ham tren
			     });
			</script>
    

        <div class="content">
           <div class="container-fluid">
              <div class="row">
                 <div class="col-lg-12 col-md-12">
                    <div class="card">
                       <div class="header">
                            <h4 class="title" style="text-align: center; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">Thêm Người Dùng</h4>
                        </div>
                        
                           <%
						      if(request.getParameter("msg")!=null){
						          int msg = Integer.parseInt( request.getParameter("msg") );
						          switch(msg){
						            case 3: out.print("</br>&nbsp;&nbsp;&nbsp;&nbsp;<strong style='color : red ;font-weight: bold'>User Đã Tồn Tại !!</strong> ");break;
						            case 2: out.print("</br>&nbsp;&nbsp;&nbsp;&nbsp;<strong style='color : red ;font-weight: bold'>Email Đã Tồn Tại !!</strong> ");break;
						          }
						      }
	     			       %>       
                        <div class="content">
                        	<%
                        		UserDAO userDao = new UserDAO();
                           	%>
                            <form id="frmUser" action="<%=request.getContextPath() %>/admin/user/add" method="post">
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Họ tên</label>
		                                 <input type="text" name="fullname" class="form-control border-input" placeholder="Họ tên">
		                             </div>
		                         </div>
		                         <div class="col-md-2">
		                             <div class="form-group height-100">
		                                 <div class="form-group">
		                                 <label>Năm sinh</label>
		                                 <input type='text' name="nam_sinh"  class="form-control border-input" data-format="yyyy-MM-dd" id="datetimepicker"/><span class="glyphicon glyphicon-calendar form-control-feedback" style="font-size: 20px; position:absolute; top:50px;"></span>
		                                 </div>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Học vị</label>
		                                 <select name="hoc_vi" class="form-control border-input">
		                                     <%
		                                    	ArrayList<HocVi> listHocVi = userDao.getListHocVi();
		                                    	for(int i = 0; i < listHocVi.size(); i++) {
	                                    	%>
                                               	<option value="<%= listHocVi.get(i).getIdHocVi() %>"><%= listHocVi.get(i).getTenHocVi() %></option>
                                               	<%} %>
		                                 </select> 
		                             </div>
		                         </div>
		                         <div class="col-md-4">
		                             <div class="form-group height-100">
		                                 <label>Chức danh khoa học</label>
		                                 <input type="text" name="chuc_danh_khoa_hoc" class="form-control border-input" style="height: 40px!important">
		                             </div>
		                         </div>
		                             <div class="col-md-3">
		                                 <div class="form-group height-100">
		                                     <label for="read">Địa chỉ cơ quan</label>
		                                     <textarea  name="dia_chi_co_quan" class="form-control border-input" placeholder="Địa chỉ cơ quan"></textarea>
		                                 </div>
		                             </div>
		                             <div class="col-md-3">
		                                 <div class="form-group height-100">
		                                     <label for="read">Địa chỉ nhà riêng</label>
		                                     <textarea  name="dia_chi_nha_rieng" class="form-control border-input" placeholder="Địa chỉ nhà riêng"></textarea>
		                             </div>
		                         </div>
		                          <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Email</label>
		                                 <input type="text" name="email" class="form-control border-input" placeholder=" yourmail@gmail.com" style="height: 62px!important">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Fax</label>
		                                 <input type="text" name="fax" class="form-control border-input" style="height: 62px!important">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Điện thoại cơ quan</label>
		                                 <input type="text" name="dien_thoai_co_quan" class="form-control border-input" placeholder="Số điện thoại">
		                             </div>
		                         </div>
		                         
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Điện thoại nhà riêng</label>
		                                 <input type="text" name="dien_thoai_nha_rieng" class="form-control border-input" placeholder="Số điện thoại">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Tên tài khoản</label>
		                                 <input type="text" name="username" class="form-control border-input">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Mật khẩu</label>
		                                 <input type="text" name="mat_khau" class="form-control border-input">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Khoa</label>
		                                 <select name="khoa" class="form-control border-input">
		                                     <%
		                                    	ArrayList<Khoa> listKhoa = userDao.getListKhoa();
		                                    	for(int i = 0; i < listKhoa.size(); i++) {
	                                    	%>
                                               	<option value="<%= listKhoa.get(i).getIdKhoa() %>"><%= listKhoa.get(i).getTenKhoa() %></option>
                                               	<%} %>
		                                 </select>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Loại tài khoản</label>
		                                 <select name="loai_tai_khoan" class="form-control border-input">
                                          	<%
		                                    	ArrayList<LoaiTaiKhoan> listLoaiTaiKhoan = userDao.getListLoaiTK();
		                                    	for(int i = 0; i < listLoaiTaiKhoan.size(); i++) {
	                                    	%>
                                               	<option value="<%= listLoaiTaiKhoan.get(i).getIdLoaiTaiKhoan() %>"><%= listLoaiTaiKhoan.get(i).getTenLoaiTaiKhoan() %></option>
                                               	<%} %>
		                                 </select>
		                             </div>
		                         </div>
		                         <!--<div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Hình ảnh</label>
		                                 <input type="file" name="picture" class="form-control" placeholder="Chọn ảnh"/>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label> Xem ảnh</label>
		                                 <div id="show-image" style="border-top: 0.5px solid #BFB8B8"></div>
		                             </div>
		                         </div> -->
		                     
		                         <div class="col-md-12">
		                             <div class="text-center">
		                                 <a href="<%=request.getContextPath() %>/admin/user/index"> 
		                                 	<input type="button" class="btn btn-default" value="Hủy" />
		                                 </a>
		                                 <input type="submit" class="btn btn-info btn-fill btn-wd" value="Thực hiện" />
		                             </div>
		                         </div>
		                         <div class="clearfix"></div>
                            </form>
                        </div>
                    </div>
            	</div>
        	</div>
                          

  <%@include file="/templates/admin/inc/footer.jsp" %>
       