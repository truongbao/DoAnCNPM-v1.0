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
									
										 <li class="active breadcrumb-title">Quản lý thành viên</li>
									
				                </ol>
				            </div>
        				</div>
    				</div>
				</div>
			    <div class="container">
			    	<div>
			    		<div class="col-xs-6">
			    			<h1 class="default_title"><a href="#">Danh sách thành viên</a></h1>
			    		</div>
			    		<div class="col-xs-6">
			    			
			    			<div class="col-xs-12 no-padding-lr">
								<div class="topbar">
									<div class="col-xs-4 pull-right">
						    				<a href="<%=request.getContextPath()%>/add-member" class="btn btn-default" style=""> Thêm thành viên</a>
						    			</div>
									<div class="sub-menu-search hidden-xs hidden-sm pull-right col-xs-8">
											<form action="http://www.mamnonnumberone.edu.vn/search" method="get">

												<div class="input-group search_form_action">
													<input type="text" class="form-control" maxlength="70" name="query" id="search" placeholder="Tìm kiếm..." autocomplete="off">
													<span class="input-group-btn">
														<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
													</span>
												</div>
												<!-- /input-group -->
											</form>
									</div>									
									<div class="sub-menu-account hidden-xs hidden-sm">
										
									</div>
								</div>
							</div>
			    		</div>
			    	</div>
					
		            <div class="page_content">
						<table class="table">
							<thead>
							<th>
								<td>Tên thành viên</td>
								<td>Đơn vị</td>
								<td>Nội dung nghiên cứu</td>
								<td>Mã số đề tài</td>
								<td style="float:right;margin-right:14px;"><center>Sửa</center></td>
								<td><center> Xóa</center></td>
							</th>
							</thead>
							<tbody>
							<tr>
								<td>1</td>
								<td>
									<a href="#"> Trương Công Hoài Bảo</a>
								</td>
								<td>
									<a href="#">Khoa CNTT</a>
								</td>
								<td>Thị giác máy tính</td>
								<td>MS-001</td>
								<td>
									<center>
											<div class="pull-right" >
												<a href="<%=request.getContextPath()%>/edit-member" class="btn btn-default" style=""> Sửa </a>
											</div>
										</center>
								</td>
								<td>
									<center>
										<div >
											<a href="" class="btn btn-danger " style=""> Xóa </a>
										</div>
									</center>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>
									<a href="#"> Nguyễn Minh Long</a>
								</td>
								<td>
									<a href="#">Khoa CNTT</a>
								</td>
								<td>Thị giác máy tính</td>
								<td>MS-001</td>
								<td>
									<center>
										<div class="pull-right" >
											<a href="edit_member.html" class="btn btn-default" style=""> Sửa </a>
									   </div>
								   </center>
								</td>
								<td>
									<center>
										<div >
											<a href="" class="btn btn-danger " style=""> Xóa </a>
										</div>
									</center>
								</td>
							</tr>
							</tbody>
						</table>
					</div>
			</div>
			<!-- /SITE CONTENT -->
		</div>
	</div>

      <%@include file="/templates/public/inc/footer.jsp" %> 


