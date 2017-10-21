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
			    <div class="container mb25">
			    	<div>
			    		<div class="col-xs-12">
			    			<h1 class="default_title">Thêm thành viên</h1>
			    		</div>
			    	</div>
					
		            <div class="page_content">
						<form class="form">
							
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Tên thành viên</label>
								</div>
								<div class="col-xs-10">
									<input type="text" name="" class="form-control" placeholder="Vui lòng nhập tên thành viên  ...">
								</div>
								
							</div>
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Đơn vị</label>
								</div>
								<div class="col-xs-10">
									<input type="text" name="" class="form-control" placeholder="Vui lòng nhập tên đơn vị ...">
								</div>
								
							</div>
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Nội dung nghiên cứu</label>
								</div>
								<div class="col-xs-10">
									<textarea class="form-control" rows="5">
										
									</textarea>
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Mã đề tài</label>
								</div>
								<div class="col-xs-10">
									<input type="text" name="" class="form-control" >
								</div>
								
							</div>
							 
							
							
							
							<div class="form-group row">
								<div class="col-xs-2">
									
								</div>
								<div class="col-xs-10 ">
									
									<a href="javascript:void()" class="btn btn-lg btn-primary pull-right ml10" >Hủy</a>
									<a href="javascript:void()" class="btn btn-lg btn-primary pull-right" onclick="showMessage()">Thêm</a>
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
			
			