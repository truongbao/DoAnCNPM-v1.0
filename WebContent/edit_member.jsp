<%@page import="model.bean.DeTai"%>
<%@page import="model.bean.ThanhVien"%>
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
			    			<h1 class="default_title">Sửa thành viên</h1>
			    		</div>
			    	</div>
					
		            <div class="page_content">
		            
		            
	            		 <%--  <%
					        if(request.getParameter("msg")!=null){
					          int msg = Integer.parseInt( request.getParameter("msg") );
					          switch(msg){
					              case 5: out.print("<h4 style='color :red; text-align:center;'> Bạn chỉ được nhâp thông tin 1 trong 2 trường 'Tên thành viên'  </h4> ");break; 
					           	     }
					           }
				          %>       --%>
		            
		            
			               <%
			                   ThanhVien objThanhVien = null;
			                   if(request.getAttribute("objThanhVien")!=null){
			                	    objThanhVien = (ThanhVien)request.getAttribute("objThanhVien");
			                   }
			               
			               %>
		            
		            
						<form class="form"  action="<%=request.getContextPath() %>/edit-member?tidTV=<%=objThanhVien.getIdThanhVien()%>&tnameTV=<%=objThanhVien.getTenThanhVien() %>&tidDeTai=<%=objThanhVien.getIdDeTai() %>" method="post">
							
							<div class="form-group row">
							   
								<div class="col-xs-3">
									<label class="p-center">Tên thành viên(Trong trường)</label>
								</div>
								<div class="col-xs-9">
									 <select name="idUser" class="form-control border-input">
									    <option  value="0">------------------------- Chọn ------------------------</option>
										<%
										if (request.getAttribute("listUserInSchool") != null){
											ArrayList<User> listUserInSchool = (ArrayList<User>) request.getAttribute("listUserInSchool");
											String selected = "";
											if (listUserInSchool.size() > 0){
												for (User objUser : listUserInSchool){
													if (objUser.getIdUser() == objThanhVien.getIdThanhVien() ){
														selected = "selected='selected'";
													} else {
														selected="";
													}
													
										%>
										<option  <%=selected %> value="<%=objUser.getIdUser()%>">
										    Tên : <%=objUser.getFullName() %>&nbsp;--&nbsp; 
										   <%--  Ngày Sinh : <%=objUser.getNamSinh() %>&nbsp;/&nbsp; --%>
										    Khoa : <%=objUser.getTenKhoa() %>
										</option>
										
										<%}}} %>
												
									</select>
								</div>
								
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Tên thành viên(Ngoài trường) </label>
								</div>
								<div class="col-xs-9">
								    <%if(objThanhVien.getIdThanhVien() == 0){ %>
									    <input type="text" value="<%=objThanhVien.getTenThanhVien() %>" name="nameTV" class="form-control" placeholder="Vui lòng nhập tên thành viên (nếu có)  ...">
									<%}else{ %>
									     <input type="text" value="" name="nameTV" class="form-control" placeholder="Vui lòng nhập tên thành viên (nếu có)  ...">
									<%} %>
								</div>
								
							</div>
							
							
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Đơn vị</label>
								</div>
								<div class="col-xs-9">
									<input type="text" value="<%=objThanhVien.getDonVi() %>" name="donVi" class="form-control" placeholder="Vui lòng nhập tên đơn vị ...">
								</div>
								
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Nội dung nghiên cứu</label>
								</div>
								<div class="col-xs-9">
									<textarea class="form-control" rows="5" name="noiDungNghienCuu">
										 <%=objThanhVien.getNoiDungNghienCuu() %>
									</textarea>
								</div>
							</div>
							
							<div class="form-group row">
								<div class="col-xs-3">
									<label class="p-center">Mã đề tài</label>
								</div>
								<div class="col-xs-9">
									 <select name="idDeTai" class="form-control border-input">
									   <%
									     String maDT = null;
										if (request.getAttribute("ListMaSoDeTaiByIdUser") != null){
											ArrayList<DeTai> ListMaSoDeTaiByIdUser = (ArrayList<DeTai>) request.getAttribute("ListMaSoDeTaiByIdUser");
											String selected = "";
											if (ListMaSoDeTaiByIdUser.size() > 0){
												for (DeTai objDT : ListMaSoDeTaiByIdUser){
													if (objDT.getIdDeTai() == objThanhVien.getIdDeTai() ){
														selected = "selected='selected'";
													} else {
														selected="";
													}
										%>
										
										<% if(!objDT.getMaSoDeTai().equalsIgnoreCase(maDT) ){ %>
										    <option <%=selected %> value="<%=objDT.getIdDeTai()%>" > <%=objDT.getMaSoDeTai() %> </option>
										<%} %>
										
                                         <% maDT = objDT.getMaSoDeTai();  %>	
                                          									
										<%}}} %>
									</select>
								</div>
								
							</div>
							 
							
							
							
							<div class="form-group row">
								<div class="col-xs-2">
									
								</div>
								<div class="col-xs-10 ">
									<a href="javascript:void()" class="btn btn-lg btn-primary pull-right ml10" >Hủy</a>
									<!-- <a href="javascript:void()" class="btn btn-lg btn-primary pull-right" onclick="showMessage()">Thêm</a> -->
									<button class="btn btn-lg btn-primary pull-right"> Sửa </button>
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
			
			