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
					
						 <li class="active breadcrumb-title">Quản lý tài khoản</li>
					
                </ol>
            </div>
        </div>
    </div>
</div>

					



					<section class="mb25 section-page">
    <div class="container">
			
			
				 <div>
					<div class="col-xs-6">
						<h1 class="default_title"><a href="#">Thông tin tài khoản</a></h1>
					</div>
					<div class="col-xs-6">
						
						<div class="col-xs-12 no-padding-lr">
							<div class="topbar">
								
								<div class="sub-menu-search hidden-xs hidden-sm pull-right col-xs-8">
										<form action="http://www.mamnonnumberone.edu.vn/search" method="get">

											<div class="input-group search_form_action">
												<input type="text" class="form-control" maxlength="70" name="query" id="search" placeholder="Tìm kiếm tài khoản..." autocomplete="off">
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
            
            <%
		        if(request.getParameter("msg")!=null){
		          int msg = Integer.parseInt( request.getParameter("msg") );
		          switch(msg){
		              case 0: out.print("<h4 style='color :red'> Cập nhật thất bại ! </h4> ");break; 
		              case 2: out.print("<h4 style='color :red'> Cập nhật thành công ! </h4> ");break; 
		           	     }
		           }
	          %>       
				
			<table align="center"  cellpadding="1" cellspacing="1" style="width: 800px; height:20px;">
			 <%
			   User objUser = null;
			   if(request.getAttribute("objUser")!=null){
				     objUser =  (User)request.getAttribute("objUser");
			   }
			 %>
				<tbody>
					<tr>
						<td width="4%;" colspan="3">Lý lịch khoa học</td>
					</tr>
					
					<tr>
						<td width="4%;">1</td> <td width="25%;" style="font-weight:bold;">Họ tên</td>  <td> <%=objUser.getFullName() %> </td>
					</tr>
					
					<%-- <tr>
						<td width="4%;">2</td> <td width="25%;" style="font-weight:bold;">Chức danh khoa học</td>  <td> <%=objUser.getChucDanhKhoaHoc() %> </td>
					</tr> --%>
					
					<tr>
						<td width="4%;">3</td> <td width="25%;" style="font-weight:bold;">Địa chỉ cơ quan</td>  <td> <%=objUser.getDiaChiCoQuan() %> </td>
					</tr>
					<tr>
						<td width="4%;">4</td> <td width="25%;" style="font-weight:bold;">Điện thoại cơ quan</td>  <td> <%=objUser.getDienThoaiCoQuan() %> </td>
					</tr>
					<tr>
						<td width="4%;">5</td> <td width="25%;" style="font-weight:bold;">Học vị</td>  <td> <%=objUser.getTenHocViHocHam() %> </td>
					</tr>
					<tr>
						<td width="4%;">6</td> <td width="25%;" style="font-weight:bold;">Năm sinh</td>  <td> <%=objUser.getNamSinh() %> </td>
					</tr>
					<tr>
						<td width="4%;">7</td> <td width="25%;" style="font-weight:bold;">Địa chỉ nhà riêng</td>  <td> <%=objUser.getDiaChiNhaRieng() %> </td>
					</tr>
					<tr>
						<td width="4%;">8</td> <td width="25%;" style="font-weight:bold;">Số điện thoại di động</td>  <td> <%=objUser.getDienThoaiNhaRieng() %> </td>
					</tr>
					<tr>
						<td width="4%;">9</td> <td width="25%;" style="font-weight:bold;">Email</td>  <td> <%=objUser.getEmail() %></td>
					</tr>
					<tr>
						<td width="4%;">10</td> <td width="25%;" style="font-weight:bold;">Tên tài khoản</td>  <td> <%=objUser.getUserName() %> </td>
					</tr>
					<tr>
						<td width="4%;">11</td> <td width="25%;" style="font-weight:bold;">Loại tài khoản</td>  <td> <%=objUser.getTenLoaiTaiKhoan() %> </td>
					</tr>
					<tr>
						<td width="4%;">12</td> <td width="25%;" style="font-weight:bold;">Đơn vị công tác(Khoa)</td>  <td> <%=objUser.getTenKhoa() %> </td>
					</tr>
					<tr>
						<td width="4%;">13</td> <td width="25%;" style="font-weight:bold;">Fax</td>  <td> <%=objUser.getFax() %> </td>
					</tr>
					
				</tbody>
			 
			</table>
			
			

			<p>&nbsp;</p>
			
			<div class="form-group row">
				<div class="col-xs-2">
					
				</div>
				<div class="col-xs-10 ">
					<a href="<%=request.getContextPath()%>/update-infor-person" class="btn btn-lg btn-primary pull-right">Cập nhật thông tin</a>
				</div>
			</div>



<p>&nbsp;</p>
			</div>
    </div>

	
</section>


				</div>
			</div>
			<!-- /SITE CONTENT -->


          <%@include file="/templates/public/inc/footer.jsp" %> 



