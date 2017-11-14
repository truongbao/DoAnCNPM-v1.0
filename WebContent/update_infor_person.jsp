<%@page import="model.bean.HocViHocHam"%>
<%@page import="model.bean.Khoa"%>
<%@page import="model.bean.LoaiTaiKhoan"%>
<%@page import="model.bean.HocVi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/public/inc/header.jsp"%>




			<!-- SITE CONTENT
=========================================================================== -->
		<div id="site-content">
			<div id="main">
				<div class="header-breadcrumb">
    				<div class="container">
        				<div class="row ">
				            <div class="col-xs-12">
				                <ol class="breadcrumb">
									
				                    <li><a href="index.html">Trang chủ </a> </li>
									
										 <li class="active breadcrumb-title">Cập nhật thông tin tài khoản</li>
									
				                </ol>
				            </div>
        				</div>
    				</div>
				</div>
			    <div class="container mb25">
			    	<div>
			    		<div class="col-xs-12">
			    			<h1 class="default_title">Cập nhật thông tin tài khoản</h1>
			    		</div>
			    	</div>
			    	
			    	 <%
					   User objUser = null;
					   if(request.getAttribute("objUser")!=null){
						     objUser =  (User)request.getAttribute("objUser");
					   }
					 %>
					
		            <div class="page_content">
						<form class="form"  action="<%=request.getContextPath() %>/update-infor-person?uid=<%=objUser.getIdUser() %>" method="post" >
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Họ Tên:</label>
								</div>
								<div class="col-xs-4">
									<input value="<%=objUser.getFullName() %>" type="text" name="hoten" class="form-control" placeholder="Vui lòng nhập họ tên ...">
								</div>
								
								<div class="col-xs-2">
									<label class="p-center">Tên đăng nhập  </label>
								</div>
								<div class="col-xs-4">
									<input value="<%=objUser.getUserName() %>" disabled="disabled" type="text" name="username" class="form-control">
								</div>
								
							</div>
							
						
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Mật khẩu </label>
								</div>
								<div class="col-xs-4">
									<input  type="password" name="matkhau" class="form-control" placeholder="Vui lòng nhập mật khẩu ...">
								</div>
								
								<div class="col-xs-2">
									<label class="p-center">Email</label>
								</div>
								<div class="col-xs-4">
									<input value="<%=objUser.getEmail() %>" type="text" name="email" class="form-control" placeholder="Vui lòng nhập Email ...">
								</div>
								
								
							</div>
							
							
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Khoa</label>
								</div>
								 <div class="col-xs-10">
									 <select name="idKhoa" class="form-control border-input">
									 
									  <%
										if (request.getAttribute("listKhoa") != null){
											ArrayList<Khoa> listKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
											String selected = "";
											if (listKhoa.size() > 0){
												for (Khoa objKhoa : listKhoa){
													if (objUser.getIdKhoa() == objKhoa.getIdKhoa() ){
														selected = "selected='selected'";
													} else {
														selected="";
													}
										%>
										<option <%=selected %> value="<%=objKhoa.getIdKhoa()%>"><%=objKhoa.getTenKhoa() %></option>
										<%
												}	
											}
										}
										%> 
									 
									</select>
								</div>
								
							</div>
							
							
							 <div class="form-group row">
								 <div class="col-xs-2">
									  <label class="p-center">Học vị</label>
								 </div>
								 
								 <div class="col-xs-10">
									 <select name="idHocVi" class="form-control border-input">
									 
									  <%
										if (request.getAttribute("listHocVi") != null){
											ArrayList<HocViHocHam> listHocVi = (ArrayList<HocViHocHam>) request.getAttribute("listHocVi");
											String selected = "";
											if (listHocVi.size() > 0){
												for (HocViHocHam objHocViHocHam : listHocVi){
													if (objUser.getIdHocViHocHam() == objHocViHocHam.getIdHocViHocHam()){
														selected = "selected='selected'";
													} else {
														selected="";
													}
										%>
										<option <%=selected %> value="<%=objHocViHocHam.getIdHocViHocHam()%>"><%=objHocViHocHam.getTenHocViHocHam() %></option>
										<%
												}	
											}
										}
										%> 
									 
									</select>
								</div>
                            </div>
							
							
							 <div class="form-group row">
								 <div class="col-xs-2">
									  <label class="p-center">Loại tài khoản</label>
								 </div>
								 
								 <div class="col-xs-10">
									 <select name="idLoaiTaiKhoan" class="form-control border-input">
									 
									   <%
										if (request.getAttribute("listLoaiTK") != null){
											ArrayList<LoaiTaiKhoan> listLoaiTK = (ArrayList<LoaiTaiKhoan>) request.getAttribute("listLoaiTK");
											String selected = "";
											if (listLoaiTK.size() > 0){
												for (LoaiTaiKhoan objLoaiTK : listLoaiTK){
													if (objUser.getIdLoaiTaiKhoan() == objLoaiTK.getIdLoaiTaiKhoan()){
														selected = "selected='selected'";
													} else {
														selected="";
													}
										%>
										<option <%=selected %> value="<%=objLoaiTK.getIdLoaiTaiKhoan()%>"><%=objLoaiTK.getTenLoaiTaiKhoan() %></option>
										<%
												}	
											}
										}
										%> 
									 
										
										
									</select>
								</div>
                            </div>
							
							<%-- <div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Chức danh khoa học</label>
								</div>
								<div class="col-xs-10">
									<input value="<%=objUser.getChucDanhKhoaHoc() %>" type="text" name="chucdanh_khoahoc" class="form-control" placeholder="Vui lòng nhập chức danh khoa học ...">
								</div>
								
							</div> --%>
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Địa chỉ cơ quan</label>
								</div>
								<div class="col-xs-10">
									<input value="<%=objUser.getDiaChiCoQuan() %>" type="text" name="diachi_coquan" class="form-control" placeholder="Vui lòng nhập địa chỉ cơ quan ...">
								</div>
								
							</div>
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Địa chỉ nhà riêng</label>
								</div>
								<div class="col-xs-5">
									<input value="<%=objUser.getDiaChiNhaRieng() %>" type="text" name="diachi_nharieng" class="form-control" placeholder="Vui lòng nhập địa chỉ nhà riêng ...">
								</div>
								
								<div class="col-xs-1">
									<label class="p-center"></label>
								</div>
								<div class="col-xs-4">
									<input value="<%=objUser.getFax() %>" type="text" name="fax" class="form-control" placeholder="Vui lòng nhập Fax ...">
								</div>
								
							</div>
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Điện thoại cơ quan</label>
								</div>
								<div class="col-xs-5">
									<input value="<%=objUser.getDienThoaiCoQuan() %>" type="text" name="phone_coquan" class="form-control" placeholder="Vui lòng nhập số điện thoại cơ quan ...">
								</div>
								
								<div class="col-xs-1" >
									<label class="p-center"></label>
								</div>
								<div class="col-xs-4">
									<input value="<%=objUser.getDienThoaiNhaRieng() %>"  type="text" name="phone_nharieng" class="form-control" placeholder="Vui lòng nhập số điện thoại di động ...">
								</div>
								
							</div>
							
							<div class="form-group row">
								
								<div class="col-xs-2">
									<label class="p-center">Ngày sinh:</label>
								</div>
								<div class="col-xs-10">
									<input value="<%=objUser.getNamSinh() %>" type="Date" name="ngaysinh" class="form-control" placeholder="Vui lòng nhập ngày sinh ...">
								</div>
							</div>	
							<div class="form-group row">
								<div class="col-xs-2">
									
								</div>
								<div class="col-xs-10 ">
									
									<a href="javascript:void()" class="btn btn-lg btn-primary pull-right ml10" >Hủy</a>
									<button class="btn btn-lg btn-primary pull-right">  Cập nhật</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- /SITE CONTENT -->
		</div>
	</div>

 
      <%@include file="/templates/public/inc/footer.jsp" %> 

