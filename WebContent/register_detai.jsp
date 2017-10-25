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
					              case 1: out.print("<h4 style='color :red'> Thêm thành công ! </h4> ");break; 
					             
					           	 }
					        }
				          %>       
		            
		            
						<form class="form" action="<%=request.getContextPath() %>/register-detai" method="post">
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Tên đề tài:</label>
								</div>
								<div class="col-xs-9">
									<input type="text" name="tenDeTai" class="form-control">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Lĩnh vực nghiên cứu:</label>
								</div>
								
								<div class="col-xs-9">
								    <select name="idLinhVucNghienCuu" class="form-control border-input">
									    <option  value="0">--------------------------------------- Chọn -------------------------------------</option>
									    
									    <%
										if (request.getAttribute("listLinhVucNC") != null){
											ArrayList<LinhVucNC> listLinhVucNC = (ArrayList<LinhVucNC>) request.getAttribute("listLinhVucNC");
											if (listLinhVucNC.size() > 0){
												for (LinhVucNC objLVNC : listLinhVucNC){
													 
										%>
										<option value="<%=objLVNC.getIdLinhVucNghienCuu()%>"> <%=objLVNC.getTenLinhVucNghienCuu() %>   </option>
										
										<%}}} %>
									    
									</select>
								</div>
							</div>
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Tính cấp thiết:</label>
								</div>
								<div class="col-xs-9">
									<input type="text" name="tinhCapThiet" class="form-control">
								</div>
							</div>
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Mục tiêu:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext1" name="mucTieu" class="form-control" rows="4">
										
									</textarea>
									
									 <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext1');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Nội dung chính:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext2" name="noiDungChinh" class="form-control" rows="4">
										
									</textarea>
									
									 <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext2');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div>
							
							<%-- <div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Kết quả dự kiến:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext3" name="ket" class="form-control" rows="4">
										
									</textarea>
									
									 <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext3');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div> --%>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Sản phẩm:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext4" name="sanPham" class="form-control" rows="4">
										
									</textarea>
									
									  <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext4');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Hiệu quả dự kiến:</label>
								</div>
								<div class="col-xs-9">
									<input type="text" name="hieuQuaDukien" class="form-control">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Nhu cầu kinh phí dự kiến:</label>
								</div>
								<div class="col-xs-9">
									<input type="number" name="kinhPhiThucHien" class="form-control">
								</div>
								
							</div>	
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Thông tin liên lạc của người đề xuất (điện thoại/email):</label>
								</div>
								<div class="col-xs-9">
									<textarea disabled="disabled" id="cktext5" name="thongTinLienHe" class="form-control ckeditor" rows="4">
										Email :  <%=objUser.getEmail() %> </br>
										Phone : <%=objUser.getDienThoaiNhaRieng() %>
									</textarea>
									
									  <script type="text/javascript">
							               var editor = CKEDITOR.replace('cktext5');
							               CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         </script>
									
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									
								</div>
								<div class="col-xs-9 ">
									
									<a href="javascript:void()" class="btn btn-lg btn-primary pull-right ml10" >Hủy</a>
									<!-- <a href="javascript:void()" class="btn btn-lg btn-primary pull-right" onclick="showMessage()">Đăng ký</a> -->
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
		
		