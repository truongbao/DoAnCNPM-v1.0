<%@page import="model.bean.DeTai"%>
<%@page import="model.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#frmUser")
								.validate(
										{
											rules : {
												"username" : {
													required : true,
													minlength : 6,
													maxlength : 32
												},
												"password" : {
													required : true,
													minlength : 6,
													maxlength : 15
												},
												"fullname" : {
													required : true,
													minlength : 6,
													maxlength : 32
												},
												"email" : {
													required : true,
													email : true

												}
											},
											messages : {
												"username" : {
													required : "<p> <span style='color:red' > Vui lòng nhập vào username</span> </p>",
													minlength : "<p><span style='color:red' >   username phải có ít nhất 6 ký tự  </span></p>",
													maxlength : "<p> <span style='color:red' >  username có nhiều nhất 32 ký tự  </span></p>"
												},
												"password" : {
													required : "<p> <span style='color:red' > Vui lòng nhập vào password </span> </p>",
													minlength : "<p><span style='color:red' > password phải có ít nhất 6 ký tự  </span></p>",
													maxlength : "<p> <span style='color:red' >  password có nhiều nhất 15 ký tự  </span></p>"
												},
												"fullname" : {
													required : "<p> <span style='color:red' > Vui lòng nhập vào fullname</span> </p>",
													minlength : "<p><span style='color:red' >  fullname phải có ít nhất 6 ký tự  </span></p>",
													maxlength : "<p> <span style='color:red' >  fullname có nhiều nhất 32 ký tự  </span></p>"
												},
												"email" : {
													required : "<p style='color:red'> Vui lòng nhập email </p>",
													email : "<p style='color:red'> Vui lòng nhập đúng định dạng email : ai_do@example.com </p> "
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
						<h4 class="title"
							style="text-align: center; color: white; border: 1px solid; padding: 4px 4px; border-radius: 4px; background: #58a808; color: #FFF; font-weight: bold">Xem
							thông tin người dùng</h4>
					</div>

					<div class="content">
						<%
								   if(request.getAttribute("objUser")!=null){
									    User objUser =  (User)request.getAttribute("objUser");
								 
								 %>
						<div class="col-md-5 mt-20">
							<div class="col-md-12 text-center">
								<img class="img-circle"
									src="<%=request.getContextPath() %>/templates/admin/img/faces/face-3.jpg"
									style="width: 300px; height: 300px; background-size: cover; margin-top: 20px;">
							</div>
							<div class="col-md-12">
								<div>
									<div class="col-md-4 mt-10">Tài khoản:</div>
									<div class="col-md-8 mt-10">
										<input type="text" style="color: black; font-weight: bold;"
											class="form-control text-center" name="username"
											value="<%= objUser.getUserName() %>" readonly>
									</div>
								</div>
								<div>
									<div class="col-md-4 mt-10">Loại TK:</div>
									<div class="col-md-8 mt-10">
										<input type="text" style="color: black; font-weight: bold;"
											class="form-control text-center" name="loai_tai_khoan" value="<%=objUser.getTenLoaiTaiKhoan() %>"
											readonly>
									</div>
								</div>
								<div>
									<div class="col-md-4 mt-10">Email</div>
									<div class="col-md-8 mt-10">
										<input type="text" style="color: black; font-weight: bold;"
											class="form-control text-center" name="email"
											value="<%= objUser.getEmail() %>" readonly>
									</div>
								</div>

								<div class="text-center">
									<a href="<%=request.getContextPath() %>/admin/user/index">
										<button type="button" class="btn btn-warning mt-10">Trở
											về</button>
									</a> 
									<div style="display: inline;" class='<%=request.getSession().getAttribute("admin") == null? "disabled" : "" %>'>
										<a
											href="<%=request.getContextPath() %>/admin/user/form-edit?uid=<%=objUser.getIdUser() %>">
											<button type="button" class="btn btn-primary mt-10">Sửa</button>
										</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-7">
							<h3 class="text-success text-center">Thông tin thêm</h3>
							<div class="col-md-2">
								<div class="form-group">
									<label>ID</label> <input style="color: black" type="text"
										name="id" class="form-control border-input" disabled
										value="<%= objUser.getIdUser() %>">
								</div>
							</div>
							<div class="col-md-10">
								<div class="form-group">
									<label>Họ tên</label> <input style="color: black" type="text"
										name="fullname" class="form-control border-input"
										placeholder="Họ tên" value="<%= objUser.getFullName() %>"
										readonly>
								</div>
							</div>
							<div class="col-md-8">
								<div class="form-group">
									<label for="read">Địa chỉ cơ quan</label>
									<textarea name="diaChiCoQuan" class="form-control border-input"
										placeholder="Địa chỉ cơ quan"
										value="<%= objUser.getDiaChiCoQuan() %>" readonly></textarea>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Điện thoại cơ quan</label> <input style="color: black"
										type="text" name="dienThoaiCoQuan"
										class="form-control border-input" placeholder="Số điện thoại"
										value="<%= objUser.getDienThoaiCoQuan() %>" readonly>
								</div>
							</div>
							<div class="col-md-8">
								<div class="form-group">
									<label for="read">Địa chỉ nhà riêng</label>
									<textarea name="diaChiNhaRieng"
										class="form-control border-input"
										placeholder="Địa chỉ nhà riêng"
										value="<%= objUser.getDiaChiNhaRieng() %>" readonly></textarea>
								</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label>Đi động</label> <input style="color: black" type="text"
										name="diDong" class="form-control border-input"
										placeholder="Số điện thoại"
										value="<%= objUser.getDienThoaiNhaRieng() %>" readonly>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Năm sinh</label> <input style="color: black" type="text"
										name="namSinh" class="form-control border-input"
										value="<%= objUser.getNamSinh() %>" readonly>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Fax</label> <input style="color: black" type="text"
										name="fax" class="form-control border-input"
										value="<%= objUser.getFax() %>" readonly>
								</div>
							</div>
							<div class="col-md-6">
								<label>Học vị học hàm</label>
								<%=objUser.getTenHocViHocHam()  %>
							</div>
							<div class="col-md-6">
								<label>Khoa: </label>
								<%=objUser.getTenKhoa()  %>
							</div>
							<div class="col-md-12 text-center mt-20 mb-20">
								<button class="btn btn-link" id="show-list-topic">Danh
									sách đề tài</button>
							</div>
						</div>
						<div class="col-md-12" id="list-topic" hidden>
							<h5 class="mt-20 text-center text-success">DANH SÁCH ĐỀ TÀI</h5>
							<%  ArrayList<DeTai> listDeTai = (ArrayList<DeTai>)request.getAttribute("listDeTai");
							    if (listDeTai != null && listDeTai.size() > 0) {
	                            		%>
							<div class="content table-responsive table-full-width table-bordered">
								<table class="table table-striped">
									<thead>
										<th>ID</th>
										<th>Tên đề tài</th>
										<th>Chủ nhiệm</th>
										<th>Cấp đề tài</th>
										<th>Trạng thái</th>
									</thead>
									<tbody>
										<%	for(int i = 0 ; i<listDeTai.size() ; i++) { %>

										<tr>
											<td>1</td>
											<td><a href="#">Nghiên cứu đề xuất phương pháp tính
													xói trụ cầu</a></td>
											<td>Nguyễn Thế Hùng</td>
											<td>Cấp cơ sở</td>
											<td>Đang thực hiện</td>
										</tr>
										<%} %>
									</tbody>
								</table>
								<%} else {%>
								<h3><div class="col-md-12 text-center text-danger">Chưa có đề tài nào!</div></h3>
								<%} %>
							</div>
						</div>
						<div class="clearfix"></div>
						<%} %>
					</div>
				</div>
			</div>
		</div>


		<%@include file="/templates/admin/inc/footer.jsp"%>