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
									
										 <li class="active breadcrumb-title">Giới thiệu</li>
									
				                </ol>
				            </div>
        				</div>
    				</div>
				</div>
			    <div class="container mb25">
			    	<div>
			    		<div class="col-xs-12">
			    			<h1 class="default_title"> Sửa đề tài</h1>
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
		            
		            
		                 <%
						    if(request.getAttribute("objDeTaiByIdDeTai") != null){
						    	 DeTai objDeTaiByIdDeTai =(DeTai)request.getAttribute("objDeTaiByIdDeTai");
						    	 
						 %>
		            
						<form class="form" action="<%=request.getContextPath() %>/update-detai-dk?did=<%=objDeTaiByIdDeTai.getIdDeTai()%>" method="post">
						
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Tên đề tài:</label>
								</div>
								<div class="col-xs-9">
									<input value="<%=objDeTaiByIdDeTai.getTenDeTai() %>" type="text" name="tenDeTai" class="form-control">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Lĩnh vực nghiên cứu:</label>
								</div>
								
								<div class="col-xs-9">
								    <select name="idLinhVucNghienCuu" class="form-control border-input">
									     <option  value="0">-------------------------------- Chọn lĩnh vực nghiên cứu --------------------------------</option>
									    
									    <%
										if (request.getAttribute("listLinhVucNC") != null){
											ArrayList<LinhVucNC> listLinhVucNC = (ArrayList<LinhVucNC>) request.getAttribute("listLinhVucNC");
											String selected = "";
											if (listLinhVucNC.size() > 0){
												for (LinhVucNC objLVNC : listLinhVucNC){
													
													if (objLVNC.getIdLinhVucNghienCuu() == objDeTaiByIdDeTai.getIdLinhVucNghienCuu() ){
														selected = "selected='selected'";
													} else {
														selected="";
													}
													 
										%>
										
										<option <%=selected %> value="<%=objLVNC.getIdLinhVucNghienCuu()%>"> <%=objLVNC.getTenLinhVucNghienCuu() %>   </option>
										
										<%}}} %>
									    
									</select>
								</div>
							</div>
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Cấp đề tài:</label>
								</div>
								
								<div class="col-xs-9">
								    <select name="idCapDeTai" class="form-control border-input">
									    <option  value="0">-------------------------------- Chọn cấp đề tài  ---------------------------------------------</option>
									    
									   <%
										if (request.getAttribute("listCapDeTai") != null){
											ArrayList<CapDeTai> listCapDeTai = (ArrayList<CapDeTai>) request.getAttribute("listCapDeTai");
											String selected = "";
											if (listCapDeTai.size() > 0){
												for (CapDeTai objCDT : listCapDeTai){
													
													if (objCDT.getIdCapDeTai() == objDeTaiByIdDeTai.getIdCapDeTai() ){
														selected = "selected='selected'";
													} else {
														selected="";
													}
													 
										%>
										
										<option <%=selected %> value="<%=objCDT.getIdCapDeTai()%>"> <%=objCDT.getTenCapDeTai() %>   </option>
										
										<%}}} %>
									    
									</select>
								</div>
							</div>
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Tính cấp thiết:</label>
								</div>
								<div class="col-xs-9">
									<input value="<%=objDeTaiByIdDeTai.getTinhCapThiet() %>" type="text" name="tinhCapThiet" class="form-control">
								</div>
							</div>
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Mục tiêu:</label>
								</div>
								<div class="col-xs-9">
									<textarea id="cktext1" name="mucTieu" class="form-control" rows="4">
										<%=objDeTaiByIdDeTai.getMucTieu() %>
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
										<%=objDeTaiByIdDeTai.getNoiDung() %>
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
										<%=objDeTaiByIdDeTai.getSanPham() %>
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
									<input value="<%=objDeTaiByIdDeTai.getHieuQua() %>" type="text" name="hieuQuaDukien" class="form-control">
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Nhu cầu kinh phí dự kiến:</label>
								</div>
								<div class="col-xs-9">
									<input value="<%=objDeTaiByIdDeTai.getKinhPhiThucHien() %>" type="number" name="kinhPhiThucHien" class="form-control">
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
									
									<a href="<%=request.getContextPath() %>/quanly-detai" class="btn btn-lg btn-primary pull-right ml10" >Hủy</a>
									<!-- <a href="javascript:void()" class="btn btn-lg btn-primary pull-right" onclick="showMessage()">Đăng ký</a> -->
									<button class="btn btn-lg btn-primary pull-right">  Cập nhật</button>
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
		
		