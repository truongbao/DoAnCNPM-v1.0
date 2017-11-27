<%@page import="model.bean.DeTai"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/public/inc/header.jsp"%>


			
			<div id="site-content">
				<div id="main">

					<div class="header-breadcrumb">
    <div class="container">
        <div class="row ">
            <div class="col-xs-12">
                <ol class="breadcrumb">
					
                    <li><a href="index.html">Trang chủ </a> </li>
					
						 <li class="active breadcrumb-title">Quản lý đề tài</li>
						 <li class="active breadcrumb-title">Quản lý đề tài</li>
					
                </ol>
            </div>
        </div>
    </div>
</div>

					



					<section class="mb25 section-page">
    <div class="container">
			
			
				 <div>
					<div class="col-xs-6">
						<h1 class="default_title"><a href="#">Đăng ký thuyết minh đề tài</a></h1>
					</div>
					<div class="col-xs-6">
						
						<div class="col-xs-12 no-padding-lr">
							<div class="topbar">
								
								<div class="sub-menu-search hidden-xs hidden-sm pull-right col-xs-8">
										<form action="http://www.mamnonnumberone.edu.vn/search" method="get">

											<div class="input-group search_form_action">
												<input type="text" class="form-control" maxlength="70" name="query" id="search" placeholder="Tìm kiếm " autocomplete="off">
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
			              case 0: out.print("<h3 style='color :red;'> Upload file thất bại </h3> ");break; 
			              case 1: out.print("<h3 style='color :red;'> Upload file thành công </h3> ");break; 
			           	     }
			           }
		          %>      	
            
			<table align="center" border="1" cellpadding="1" cellspacing="1" style="width: 800px; height:20px;">
			
				<tbody>
					<tr>
						<td width="4%;" colspan="3">Thông tin đề tài </td>
					</tr>
					
					<%
				    if(request.getAttribute("objDeTaiByIdDeTaiDK") != null){
				    	 DeTai objDeTaiByIdDeTaiDK =(DeTai)request.getAttribute("objDeTaiByIdDeTaiDK");
				    	 
				    	 
				    	 
					%>
					
					<tr>
						<td width="4%;">1</td> <td width="25%;" style="font-weight:bold;">Tên đề tài</td>  <td> <%=objDeTaiByIdDeTaiDK.getTenDeTai() %> </td>
					</tr>
					<tr>
						<td width="4%;">2</td> <td width="25%;" style="font-weight:bold;">Chủ nhiệm đề tài</td>  <td> <%=objDeTaiByIdDeTaiDK.getFullName() %> </td>
					</tr>
					<tr>
						<td width="4%;">3</td> <td width="25%;" style="font-weight:bold;">Lĩnh vực nghiên cứu</td>  <td> <%=objDeTaiByIdDeTaiDK.getTenLinhVucNghienCuu() %> </td>
					</tr>
					<tr>
						<td width="4%;">4</td> <td width="25%;" style="font-weight:bold;">Tính cấp thiết</td>  <td> <%=objDeTaiByIdDeTaiDK.getTinhCapThiet() %> </td>
					</tr>
					<tr>
						<td width="4%;">5</td> <td width="25%;" style="font-weight:bold;">Mục tiêu</td>  <td> <%=objDeTaiByIdDeTaiDK.getMucTieu() %> </td>
					</tr>
					<tr>
						<td width="4%;">6</td> <td width="25%;" style="font-weight:bold;">Nội dung chính</td>  <td> <%=objDeTaiByIdDeTaiDK.getNoiDung() %>  </td>
					</tr>
					
					 <tr>
						<td width="4%;">7</td> <td width="25%;" style="font-weight:bold;">Cấp đề tài</td>  <td>  <%=objDeTaiByIdDeTaiDK.getTenCapDeTai() %> </td>
					</tr>
					
					<!--
					<tr>
						<td width="4%;">8</td> <td width="25%;" style="font-weight:bold;">Kết quả dự kiến</td>  <td>  </td>
					</tr> -->
					
					<tr>
						<td width="4%;">8</td> <td width="25%;" style="font-weight:bold;">Hiệu quả dự kiến</td>  <td> <%=objDeTaiByIdDeTaiDK.getHieuQua() %> </td>
					</tr>
					<tr>
						<td width="4%;">9</td> <td width="25%;" style="font-weight:bold;">Kinh phí dự kiếm</td>  <td>  <%=objDeTaiByIdDeTaiDK.getKinhPhiThucHien() %> Đồng </td>
					</tr>
					<tr>
						<td width="4%;">10</td> <td width="25%;" style="font-weight:bold;">Thời gian dư kiến</td>  <td> 1 năm</td>
					</tr>
					
					
					
				</tbody>
			</table>
			
			<h3 style="margin-top: 15px;font-size: 18px;"> Tải mẫu đăng ký thuyết minh 
			   <a href="">:  Tại đây </a> 
			</h3>
			
			<form class="form"  action="<%=request.getContextPath() %>/dangky-thuyetminh?did=<%=objDeTaiByIdDeTaiDK.getIdDeTai()%>" method="post"  enctype="multipart/form-data">
					
				
							
				<div>
						<label> Upload file thuyết minh (Sau khi đã điền đầy đủ thông tin theo mẫu trên)</label>
				</div>
							
				<div style="padding-top: 5px;">
					<input type="file" name="upfiles" id="">
				</div>
				
				<div style="float: left;padding-top: 41px;">
					<button class="btn btn-lg btn-primary pull-right"> Tạo thuyết minh </button>
				</div>
				
			</form>
			 
			<p>&nbsp;</p>
			
			<%} %>
			
			<!-- <div class="form-group row"> 
				<div class="col-xs-2">
					 
				</div>
				<div class="col-xs-10 ">
					<a href="" class="btn btn-lg btn-primary pull-right">Tạo thuyết minh</a>
				</div>
			</div> -->



<p>&nbsp;</p>
			</div>
    </div>

	
</section>


				</div>
			</div>
			<!-- /SITE CONTENT -->

          <%@include file="/templates/public/inc/footer.jsp" %> 
			
			