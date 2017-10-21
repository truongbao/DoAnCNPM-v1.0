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
									
										 <li class="active breadcrumb-title">Giới thiệu</li>
									
				                </ol>
				            </div>
        				</div>
    				</div>
				</div>
			    <div class="container mb25">
			    	<div>
			    		<div class="col-xs-12">
			    			<h1 class="default_title">Đăng ký</h1>
			    		</div>
			    	</div>
					
		            <div class="page_content">
						<form class="form">
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Tên đề tài:</label>
								</div>
								<div class="col-xs-10">
									<input type="text" name="" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Lĩnh vực nghiên cứu:</label>
								</div>
								<div class="col-xs-10">
									<div class="col-xs-10">
										<div class="col-xs-4">
											<span class="col-xs-9 s20"> Tự nhiên</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
										<div class="col-xs-4">
											<span class="col-xs-9 s20">Kỹ thuật</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
										<div class="col-xs-4">
											<span class="col-xs-9 s20">Môi trường</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
									</div>
									<div class="col-xs-10">
										<div class="col-xs-4">
											<span class="col-xs-9 s20">Kinh tế, XH-NV</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
										<div class="col-xs-4">
											<span class="col-xs-9 s20">Nông Lâm</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
										<div class="col-xs-4">
											<span class="col-xs-9 s20">ATLĐ</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
									</div>
									<div class="col-xs-10">
										<div class="col-xs-4">
											<span class="col-xs-9 s20">Giáo Dục</span>
											<input type="checkbox" name="" class="col-xs-3" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
										<div class="col-xs-4">
											<span class="col-xs-9 s20">Y Dược</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
										<div class="col-xs-4">
											<span class="col-xs-9 s20">Sở hửu trí tuệ</span>
											<input type="checkbox" name="" class="" value="" style="height: 20px; width: 20px; margin-left: 10px">
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Tính cấp thiết:</label>
								</div>
								<div class="col-xs-10">
									<input type="text" name="" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Mục tiêu:</label>
								</div>
								<div class="col-xs-10">
									<textarea class="form-control" rows="5">
										
									</textarea>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Nội dung chính:</label>
								</div>
								<div class="col-xs-10">
									<textarea class="form-control" rows="5">
										
									</textarea>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Kết quả dự kiến:</label>
								</div>
								<div class="col-xs-10">
									<textarea class="form-control" rows="5">
										
									</textarea>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Hiệu quả dự kiến:</label>
								</div>
								<div class="col-xs-10">
									<input type="text" name="" class="form-control">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-2">
									<label class="p-center">Kinh phí dự kiến:</label>
								</div>
								<div class="col-xs-4">
									<input type="number" name="" class="form-control">
								</div>
								<div class="col-xs-2">
									<label class="p-center">Thời gian dự kiến:</label>
								</div>
								<div class="col-xs-4">
									<input type="Date" name="" class="form-control">
								</div>
							</div>	
							<div class="form-group row">
								<div class="col-xs-2">
									
								</div>
								<div class="col-xs-10 ">
									
									<a href="javascript:void()" class="btn btn-lg btn-primary pull-right ml10" >Hủy</a>
									<a href="javascript:void()" class="btn btn-lg btn-primary pull-right" onclick="showMessage()">Đăng ký</a>
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
		
		