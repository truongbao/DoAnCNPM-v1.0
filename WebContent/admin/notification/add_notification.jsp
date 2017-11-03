<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/header.jsp"%>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Danh sách giảng viên</h4>
                                <form action="<%=request.getContextPath()%>/admin/add-notif?type=search" method="post">
                                	<div class="row">
                                        <div class="col-md-6 col-offset-1">
                                            <div class="form-group">
                                                 <input type="text" name="key_words" class="form-control border-input" value=""  placeholder="Nhập thông tin...">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                        	<div class="form-group">
		                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-default" />
	                                        </div>
                                        </div>
                                    </div>
                                    
                                </form>
                                <div>
                                	<%
										if(request.getParameter("msg")!=null){
											String msg =  request.getParameter("msg");
											if("add1".equals(msg)){
												out.print("<p style=' color:green; font-weight: bold '>Thêm thành công<p>");
											}else if("add0".equals(msg)){
												out.print("<p style=' color:red; font-weight: bold '>Có lỗi xảy ra<p>");
											}
										}		
									%>
                                </div>
                                <!-- <a href="edit.html" class="addtop"><img src="assets/img/add.png" alt="" /> Thêm</a> -->
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped">
                                    <thead>
                                        <th>ID</th>
                                    	<th>Họ và tên</th>
                                    	<th>Khoa</th>
                                    	<th>Ngày sinh</th>
                                    	<th>Chức năng</th>
                                    </thead>
                                    <tbody>
                                    <%
                                    	ArrayList<User> alTeacher = (ArrayList<User>) request.getAttribute("alTeacher");
                                    	for(User obj : alTeacher){
                                    %>
                                        <tr>
                                        	<td><%=obj.getIdUser() %></td>
                                        	<td><%=obj.getFullName() %></td>
                                        	<td><%=obj.getTenKhoa() %></td>
                                        	<td><%=obj.getNamSinh() %></td>
                                        	<td>
                                                <button class="btn btn-default" onclick="showModal(<%=obj.getIdUser()%>,'<%=obj.getFullName()%>')">Thông báo</button>
                                            </td>
                                        </tr>
                                    <%} %>
                                    </tbody>
 
                                </table>

								<div class="text-right pagination-div">
									<ul class="pagination">
										<%
											int page_sum = (Integer) request.getAttribute("page_sum");
											int current_page = (Integer) request.getAttribute("current_page");
											String active = "";
											for (int i = 1; i < page_sum; i++) {
												if (i == current_page) {
													active = "class=\"current\"";
												} else {
													active = "";
												}
										%>
										<li><a <%=active%>
											href="<%=request.getContextPath()%>/admin/add-notif?type=load&page=<%=i%>"><%=i%></a><li>
										<%
											}
											if (current_page == page_sum) {
												active = "class=\"current\"";
											} else {
												active = "";
											}
										%>
										<li><a <%=active%>
											href="<%=request.getContextPath()%>/admin/add-notif?type=load&page=<%=page_sum%>"><%=page_sum%></a></li>
									</ul>
								</div>
                            </div>
                            <div id="myModal" class="modal">
                            	<!-- Modal content -->
								<div class="modal-content">
									<div class="modal-header">
										<span class="close">&times;</span>
										<h4>Gửi thông báo</h4>
									</div>
									<div class="modal-body">
										<form action="<%=request.getContextPath()%>/admin/add-notif?type=submit" method="post" name="formNotif">
		                                	<div class="row">
		                                		<input name="id_GV" value="" hidden="True"  />
		                                		<input name="idQuaTrinhThucHien" value="" hidden="True"  />
		                                        <div class="col-md-6">
		                                            <div class="form-group">
		                                            	Tên chủ nhiệm: 
		                                                 <input type="text" name="name" class="form-control border-input" value="" disabled="disabled">
		                                            </div>
		                                        </div>
		                                        <div class="col-md-6">
		                                            <div class="form-group">
		                                            	Đề tài:
		                                                 <select name="deTai" id="deTai" class="form-control border-input">
		                                                 	<option>abc</option>
		                                                 </select>
		                                            </div>
		                                        </div>
		                                        <div class="col-md-12">
		                                            <div class="form-group">
		                                            Nội dung:
		                                                 <textarea class="form-control border-input" rows="5" placeholder="Nhập nội dung..." name="noiDung"></textarea>
		                                            </div>
		                                        </div>
		                                        <div class="text-center">
		                                        	<div class="form-group">
				                                        <input type="submit" name="submit" value="Gửi" class="btn btn-default" />
			                                        </div>
		                                        </div>
		                                    </div>
		                                </form>
									</div>
								</div>
							</div>
							<script type="text/javascript">
								function showModal(id,name) {
									var modal = document.getElementById('myModal');
									var span = document.getElementsByClassName("close")[0];
									$.ajax({
										url: '<%= request.getContextPath()%>/admin/ajax-get-list', //url địa chỉ của 1 controller
										type: 'POST',
										cache: false,
										data: {
											//Dữ liệu gửi đi
											idUser: id,
										},							// đây là 1 đối tượng trong javascript
										success: function(data){
											// Xử lý thành công
											$("#deTai").html(data);
										},							// đây là 1 hàm
										error: function (){
											// Xử lý nếu có lỗi
											alert("Xảy ra lỗi trong quá trình xử lý.");
										}
									});
									document.forms["formNotif"]["id_GV"].value = id;
									document.forms["formNotif"]["name"].value = name;
									modal.style.display = "block";
									// When the user clicks on <span> (x), close the modal
									span.onclick = function() {
									    modal.style.display = "none";
									}

									// When the user clicks anywhere outside of the modal, close it
									window.onclick = function(event) {
									    if (event.target == modal) {
									        modal.style.display = "none";
									    }
									}
								}
							</script>
                        </div>
                    </div>


                </div>
            </div>
        </div>

<%@include file="/templates/admin/inc/footer.jsp"%>
