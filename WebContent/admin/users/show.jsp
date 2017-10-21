
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
                            <h4 class="title" style="text-align: center; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">Xem thông tin người dùng</h4>
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
                                <div class="col-md-5 mt-20">
                                    <div class="col-md-12 text-center">
                                        <img class="img-circle"
                                       src="<%=request.getContextPath() %>/templates/admin/img/faces/face-3.jpg" style="width: 300px;height: 300px;background-size: cover;margin-top: 20px;">
                                    </div>
                                    <div class="col-md-12">
                                        <div>
                                            <div class="col-md-4 mt-10"> Tài khoản:</div>
                                            <div class="col-md-8 mt-10"><input type="text" style="color: black; font-weight: bold;" class="form-control text-center" name="username" value="anhhuy" readonly></div>
                                        </div>
                                        <div>
                                            <div class="col-md-4 mt-10">Mật khẩu</div>
                                            <div class="col-md-8 mt-10"><input type="text" style="color: black; font-weight: bold;" class="form-control text-center" name="matKhau" value="1234" readonly></div>
                                        </div>
                                        <div>
                                            <div class="col-md-4 mt-10">Email</div>
                                            <div class="col-md-8 mt-10"><input type="text" style="color: black; font-weight: bold;" class="form-control text-center" name="email" value="huy@gmail.com" readonly></div>
                                        </div>

                                        <div class="text-center">
                                          <a href="">
                                            <button type="button" class="btn btn-warning mt-10">Trở về</button>
                                          </a>
                                          <a href="">
                                            <button type="button" class="btn btn-primary mt-10">Sửa</button>
                                          </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-7">
                                    <h3 class="text-success text-center">Thông tin thêm</h3>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>ID</label>
                                            <input style="color: black" type="text" name="id" class="form-control border-input" disabled value="1">
                                        </div>
                                    </div>
                                    <div class="col-md-10">
                                        <div class="form-group">
                                            <label>Họ tên</label>
                                            <input style="color: black" type="text" name="fullname" class="form-control border-input" placeholder="Họ tên" value="Nguyễn Thế Hùng" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label for="read">Địa chỉ cơ quan</label>
                                            <textarea  name="diaChiCoQuan" class="form-control border-input" placeholder="Địa chỉ cơ quan" readonly></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Điện thoại cơ quan</label>
                                            <input style="color: black" type="text" name="dienThoaiCoQuan" class="form-control border-input" placeholder="Số điện thoại" value="0977 665 886" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label for="read">Địa chỉ nhà riêng</label>
                                            <textarea  name="diaChiNhaRieng" class="form-control border-input" placeholder="Địa chỉ nhà riêng" readonly></textarea>
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label>Đi động</label>
                                            <input style="color: black" type="text" name="diDong" class="form-control border-input" placeholder="Số điện thoại" value="0977 665 886" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Năm sinh</label>
                                            <input style="color: black" type="text" name="namSinh" class="form-control border-input" value="1987" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Fax</label>
                                            <input style="color: black" type="text" name="fax" class="form-control border-input" value="" readonly>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <label>Học vị</label>
                                        Thạc sĩ
                                    </div>
                                    <div class="col-md-6">
                                        <label>Chức danh khoa học</label>
                                        --------
                                    </div>
                                    <div class="col-md-6">
                                         <label>Khoa: </label>
                                        Công nghệ thông tin
                                    </div>
                                    <div class="col-md-6">
                                        <label>Loại tài khoản: </label>
                                        Giảng viên
                                    </div>
                                    <div class="col-md-12 text-center mt-20 mb-20"><button class="btn btn-link" id="show-list-topic">Danh sách đề tài</button></div>
                                </div>
                            <div class="col-md-12" id="list-topic" hidden>
	                            <h5 class="mt-20 text-center text-success">DANH SÁCH ĐỀ TÀI</h5>
	                            <div class="content table-responsive table-full-width">
	                                <table class="table table-striped">
	                                    <thead>
	                                        <th>ID</th>
	                                    	<th>Tên đề tài</th>
	                                    	<th>Chủ nhiệm</th>
	                                    	<th>Cấp đề tài</th>
	                                    	<th>Trạng thái</th>
	                                    	<th>Chức năng</th>
	                                    </thead>
	                                    <tbody>
	                                        <tr>
	                                        	<td>1</td>
	                                            <td><a href="#">Nghiên cứu đề xuất phương pháp tính xói trụ cầu</a></td>
	                                            <td>Nguyễn Thế Hùng</td>
	                                        	<td>Cấp cơ sở</td>
	                                        	<td>Đang thực hiện</td>
	                                        	<td>
	                                               <a href="edit.html"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /> Sửa</a>
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                            <td>2</td>
	                                            <td><a href="#">Nghiên cứu đề xuất phương pháp tính xói trụ cầu</a></td>
	                                            <td>Nguyễn Thế Hùng</td>
	                                            <td>Cấp cơ sở</td>
	                                            <td>Đang thực hiện</td>
	                                            <td>
	                                               <a href="edit.html"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /> Sửa</a>
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                            <td>3</td>
	                                            <td><a href="#">Nghiên cứu đề xuất phương pháp tính xói trụ cầu</a></td>
	                                            <td>Nguyễn Thế Hùng</td>
	                                            <td>Cấp cơ sở</td>
	                                            <td>Đang thực hiện</td>
	                                            <td>
	                                               <a href="edit.html"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /> Sửa</a>
	                                            </td>
	                                        </tr>
	                                        <tr>
	                                            <td>4</td>
	                                            <td><a href="#">Nghiên cứu đề xuất phương pháp tính xói trụ cầu</a></td>
	                                            <td>Nguyễn Thế Hùng</td>
	                                            <td>Cấp cơ sở</td>
	                                            <td>Đang thực hiện</td>
	                                            <td>
	                                               <a href="edit.html"><img src="<%=request.getContextPath() %>/templates/admin/img/edit.gif" alt="" /> Sửa</a>
	                                            </td>
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
	                        <div class="clearfix"></div>
        				</div>
        			</div>
        		</div>
        	</div>
                          

  <%@include file="/templates/admin/inc/footer.jsp" %>
       