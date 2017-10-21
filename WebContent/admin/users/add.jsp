
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
                 <div class="col-lg-12 col-md-12">
                    <div class="card">
                       <div class="header">
                            <h4 class="title" style="text-align: center; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">Thêm Người Dùng</h4>
                        </div>
                        
                           <%
						      if(request.getParameter("msg")!=null){
						          int msg = Integer.parseInt( request.getParameter("msg") );
						          switch(msg){
						            case 4: out.print("</br>&nbsp;&nbsp;&nbsp;&nbsp;<strong style='color : red ;font-weight: bold'>User Đã Tồn Tại !!</strong> ");break;
						           }
						      }
	     			       %>       
                        <div class="content">
                            <form id="frmUser" action="<%=request.getContextPath() %>/admin/addUser" method="post">
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Họ tên</label>
		                                 <input type="text" name="fullname" class="form-control border-input" placeholder="Họ tên">
		                             </div>
		                         </div>
		                         <div class="col-md-2">
		                             <div class="form-group">
		                                 <div class="form-group">
		                                 <label>Năm sinh</label>
		                                 <input type='text' name="namSinh"  class="form-control border-input" data-format="yyyy-MM-dd" id="datetimepicker"/><span class="glyphicon glyphicon-calendar form-control-feedback" style="font-size: 20px;"></span>
		                                 </div>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Học vị</label>
		                                 <select name="hocVi" class="form-control border-input">
		                                     <option>Thạc sĩ</option>
		                                     <option>Tiến sĩ</option>
		                                     <option>Giáo sư</option>
		                                     <option>Phó giáo sư</option>
		                                 </select> 
		                             </div>
		                         </div>
		                         <div class="col-md-4">
		                             <div class="form-group">
		                                 <label>Chức danh khoa học</label>
		                                 <select name="chucDanhKhoaHoc" class="form-control border-input">
		                                     <option> --</option>
		                                     <option>---</option>
		                                     <option>---</option>
		                                 </select>
		                             </div>
		                         </div>
		                             <div class="col-md-3">
		                                 <div class="form-group">
		                                     <label for="read">Địa chỉ cơ quan</label>
		                                     <textarea  name="diaChiCoQuan" class="form-control border-input" placeholder="Địa chỉ cơ quan"></textarea>
		                                 </div>
		                             </div>
		                             <div class="col-md-3">
		                                 <div class="form-group">
		                                     <label for="read">Địa chỉ nhà riêng</label>
		                                     <textarea  name="diaChiNhaRieng" class="form-control border-input" placeholder="Địa chỉ nhà riêng"></textarea>
		                             </div>
		                         </div>
		                          <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Email</label>
		                                 <input type="text" name="email" class="form-control border-input" placeholder=" yourmail@gmail.com" style="height: 62px!important">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Fax</label>
		                                 <input type="text" name="fax" class="form-control border-input" style="height: 62px!important">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Điện thoại cơ quan</label>
		                                 <input type="text" name="dienThoaiCoQuan" class="form-control border-input" placeholder="Số điện thoại">
		                             </div>
		                         </div>
		                         
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Đi động</label>
		                                 <input type="text" name="diDong" class="form-control border-input" placeholder="Số điện thoại">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Tên tài khoản</label>
		                                 <input type="text" name="taiKhoan" class="form-control border-input">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Mật khẩu</label>
		                                 <input type="text" name="matKhau" class="form-control border-input">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Khoa</label>
		                                 <select name="idKhoa" class="form-control border-input">
		                                     <option>Công nghệ thông tin</option>
		                                     <option>Điện tử viễn thông</option>
		                                 </select>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Loại tài khoản</label>
		                                 <select name="friend_lít" class="form-control border-input">
		                                     <option>Giảng viên</option>
		                                     <option>Chủ nhiệm khoa</option>
		                                     <option>Nhân viên</option>
		                                 </select>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Hình ảnh</label>
		                                 <input type="file" name="picture" class="form-control" placeholder="Chọn ảnh"/>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label> Xem ảnh</label>
		                                 <div id="show-image" style="border-top: 0.5px solid #BFB8B8"></div>
		                             </div>
		                         </div>
		                     
		                         <div class="col-md-12">
		                             <div class="text-center">
		                                 <input type="button" class="btn btn-default" value="Hủy" />
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
       