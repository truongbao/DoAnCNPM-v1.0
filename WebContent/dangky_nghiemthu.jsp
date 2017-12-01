<%@page import="model.bean.CapDeTai"%>
<%@page import="model.bean.DeTai"%>
<%@page import="model.bean.LinhVucNC"%>
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
									
										 <li class="active breadcrumb-title">Đăng ký nghiệm thu</li>
									
				                </ol>
				            </div>
        				</div>
    				</div>
				</div>
			    <div class="container mb25">
			    	<div>
			    		<div class="col-xs-12">
			    			<h1 class="default_title"> Đăng ký nghiệm thu </h1>
			    		</div>
			    	</div>
			    	
			    	<%
			    	   User objUser = null;
			    	   if(request.getAttribute("objUser") !=null){
			    		   objUser =(User)request.getAttribute("objUser");
			    	   }
			    	
			    	%>
					
		            <div class="page_content">
		                  <%
					        if(request.getParameter("msg")!=null){
					          int msg = Integer.parseInt( request.getParameter("msg") );
					          switch(msg){
					              case 0: out.print("<h4 style='color :red'>  Thất bại ! </h4> ");break; 
					              case 1: out.print("<h4 style='color :red'>  thành công ! </h4> ");break; 
					             
					           	 }
					        }
				          %>       
		            
		            
		                 <%
						    if(request.getAttribute("objDeTaiByIdDeTaiDK") != null){
						    	 DeTai objDeTaiByIdDeTaiDK =(DeTai)request.getAttribute("objDeTaiByIdDeTaiDK");
						    	 
						 %>
		            
						<form class="form" action="<%=request.getContextPath() %>/dangky-nghiemthu?did=<%=objDeTaiByIdDeTaiDK.getIdDeTai()%>" method="post">
						
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Tên đề tài:</label>
								</div>
								<div class="col-xs-9">
									<input value="<%=objDeTaiByIdDeTaiDK.getTenDeTai() %>" disabled="disabled" type="text" name="tenDeTai" class="form-control">
								</div>
							</div>
							 
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Mã số đề tài:</label>
								</div>
								<div class="col-xs-9">
									<input value="<%=objDeTaiByIdDeTaiDK.getMaSoDeTai() %>" type="text" disabled="disabled" name="maSoDeTai" class="form-control">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Chủ nhiệm đề tài:</label>
								</div>
								<div class="col-xs-9">
									<input value="<%=objUser.getFullName() %>" type="text" disabled="disabled" name="chuNhiemDeTai" class="form-control">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Cơ quan chủ trì :</label>
								</div>
								<div class="col-xs-9">
									<input value="<%=objDeTaiByIdDeTaiDK.getDonViChuTri()  %>" type="text" disabled="disabled" name="donViChuTri" class="form-control">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center"> Thời gian thực hiện :</label>
								</div>
								<div class="col-xs-8">
								     	<span class="col-xs-4" style="margin-left: -15px;">
								     	     Từ <input value="<%=objDeTaiByIdDeTaiDK.getThoiGianBatDau() %>" type="text"  name="donViChuTri" class="form-control">   
								     	</span>
								     	
								     	<span class="col-xs-4">
								     	     Đến <input value="<%=objDeTaiByIdDeTaiDK.getThoiGianKetThuc() %>" type="text"  name="donViChuTri" class="form-control">    
								     	</span> 
									
								</div>
							</div>
							
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Mục tiêu:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext1" name="mucTieu" class="form-control" rows="4">
										<%=objDeTaiByIdDeTaiDK.getMucTieu() %>
									</textarea>
									
									 <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext1');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Nội dung của đề tài:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext2" name="noiDungChinh" class="form-control" rows="4">
										<%=objDeTaiByIdDeTaiDK.getNoiDung() %>
									</textarea>
									
									 <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext2');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Phương pháp nghiên cứu:</label>
								</div>
								
								<div class="col-xs-9">
									<textarea id="cktexta1" name="tinhCapThiet" class="form-control" rows="4">
										
									</textarea>
									
									 <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktexta1');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
								
							</div>
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Sản phẩm của đề tài:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext4" name="sanPham" class="form-control" rows="4">
										<%=objDeTaiByIdDeTaiDK.getSanPham() %>
									</textarea>
									
									  <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext4');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div>
							
							
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									
								</div>
								<div class="col-xs-9 ">
									
									<a href="<%=request.getContextPath() %>/dangky-nghiemthu" class="btn btn-lg btn-primary pull-right ml10" >Hủy</a>
									<!-- <a href="javascript:void()" class="btn btn-lg btn-primary pull-right" onclick="showMessage()">Đăng ký</a> -->
									<button class="btn btn-lg btn-primary pull-right">  Đăng ký nghiệm thu</button>
								</div>
							</div>
							
						</form>
						
						<%} %>
						
					</div>
				</div>
			</div>
			<!-- /SITE CONTENT -->
		</div>
	</div>


		<%@include file="/templates/public/inc/footer.jsp" %> 
		
		