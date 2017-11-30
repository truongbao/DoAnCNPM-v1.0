<%@page import="model.bean.DeTaiThongBao"%>
<%@page import="library.LibraryConstant"%>
<%@page import="model.dao.DetaiDAO"%>
<%@page import="model.bean.DeTai"%>
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
									
										 <li class="active breadcrumb-title">Quản lý đăng ký đề tài</li>
									
				                </ol>
				            </div>
        				</div>
    				</div>
				</div>
			    <div class="container">
			    	<div>
			    		<div class="col-xs-6">
			    			<h1 class="default_title"><a href="#">Danh sách đề tài đăng ký</a></h1>
			    		</div>
			    		<div class="col-xs-6">
			    			
			    			<div class="col-xs-12 no-padding-lr">
								<div class="topbar">
									<div class="col-xs-4 pull-right">
						    				<a href="<%=request.getContextPath()%>/register-detai" class="btn btn-default" style=""> Đăng ký</a>
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
		            
		               <%
					        if(request.getParameter("msg")!=null){
					          int msg = Integer.parseInt( request.getParameter("msg") );
					          switch(msg){
					              case 0: out.print("<h4 style='color :red'>  Thất bại ! </h4> ");break; 
					              case 1: out.print("<h4 style='color :red'> Thêm thành công ! </h4> ");break; 
					              case 2: out.print("<h4 style='color :red'> Sửa thành công ! </h4> ");break; 
					             
					           	 }
					        }
				          %>       
		            
		            
						<table class="table">
							<thead>
							<th>
								<td>Tên đề tài</td>
								<td>Chủ nhiệm đề tài</td>
								<!-- <td>Thời gian đăng ký</td> -->
								<td><center> Sửa đề tài</center></td>
							    <td><center> Tạo thuyết minh </center></td>
							    <td><center> Sửa thuyết minh </center></td>
							    <td>Trạng thái</td>
							    <td><center> Thông báo mới </center></td>
							    <td><center> Thông báo cũ </center></td>
							</th>
							</thead>
							<tbody>
							
							
							<%
							    int demstt = 1 ;
							    if(request.getAttribute("listDeTaiDK")!=null){
							    	ArrayList<DeTai> listDeTaiDK = (ArrayList<DeTai>)request.getAttribute("listDeTaiDK");
							    	if(listDeTaiDK.size() > 0){
							    		for(DeTai objDT : listDeTaiDK){
							    			
							%>
							<tr>
								<td><%=demstt %></td>
								<td>
									<a href="<%=request.getContextPath()%>/detail-dkdt?did=<%=objDT.getIdDeTai()%>"> <%=objDT.getTenDeTai() %> </a>
								</td>
								<td>
									 <%=objDT.getFullName() %>
								</td>
								<%-- <td> <%=objDT.getThoiGianDangKy() %> </td> --%> 
								
							    
								<td>
									<center>
										<a href="<%=request.getContextPath()%>/update-detai-dk?did=<%=objDT.getIdDeTai()%>" class="btn btn-primary" style="">  Sửa </a>
									</center>
								</td>
								
								<!-- <td>
									<center>
										<button class="btn btn-danger">
											Xóa
										</button>
									</center>
								</td> -->
								
								<td>
									<center>
										<a href="<%=request.getContextPath()%>/dangky-thuyetminh?did=<%=objDT.getIdDeTai()%>" class="btn btn-primary" style="">  Tạo </a>
									</center>
								</td>
								
								<td>
									<center>
										<a href="<%=request.getContextPath()%>/edit-thuyetminh?did=<%=objDT.getIdDeTai()%>" class="btn btn-primary" style="">  Sửa </a>
									</center>
								</td>
								
								<td>
								   <%= LibraryConstant.ConvertTrangThai(objDT.getTrangThai())%>
							    </td>
								
								<td>
									<center>  <!-- thong bao mơi -->
									  <%
									    //viet phuong thuc lay ra trangThai mới cập nhật ứng vs idDeTai vừa dk
									    DetaiDAO detaiDAO = new DetaiDAO();
									    DeTaiThongBao objDeTaiById =  detaiDAO.getTrangThaiUpdateByIdDeTaiDKAndWasRead(objDT.getIdDeTai());
									    if( objDeTaiById != null && objDeTaiById.getWasRead() == 0){
									  
									  %>
										<a href="<%=request.getContextPath()%>/thong-bao?id_detai=<%=objDT.getIdDeTai() %>" class="btn btn-danger" style="">  Xem </a>
									  <%}else{%> 
									  
									  <%} %>
									</center>
								</td> 
								
								
								<td>
									<center> <!-- thong bao old -->
									    <% if( objDeTaiById != null && objDeTaiById.getWasRead() == 1){ %>
										    <a href="<%=request.getContextPath()%>/thong-bao-old?id_detai=<%=objDT.getIdDeTai() %>" class="btn btn-primary" style="">  Xem </a>
									    <%} %>
									</center>
								</td> 
								
								
							</tr>
							
							  <% demstt = demstt  + 1; %>
							
							<%}}} %>
							
							</tbody>
						</table>
					</div>
					
					
					<div class="col-xs-12">
						<div class="blog-info-page text-right">
							<div class="filter-right">
		     
		                           <div class="collection-pagination pull-right pagination-wrapper">
		                               <ul class="pagination">
		                               
		                               
		                                   <%
											int sumPage = (Integer) request.getAttribute("sumPage");
										    int current_page = (Integer) request.getAttribute("current_page");
										    int pageFirst = 0;
										    int pageEnd = 0;
										    int numFix = 5;
										    int move = (int)Math.ceil( (float)numFix / 2);
										    //nếu current_page > 1 và sumPage > 1 thì thêm nút back
										    if(current_page > 1 && sumPage > 1){
										 %>  
										 
										   <a class="last" href="<%=request.getContextPath() %>/list-register-detai?page=<%=current_page-1%>"> 
										       <span style="font-size: 20px;">  [Back] </span>
										    </a>
										   
										 <%} %>  	
		                               
		                                
		                                   <%
										     //fix lại trang đầu và cuối
										     if(current_page >=numFix){
										    	 pageFirst = current_page-move;
										    	 if(sumPage > (current_page+move) ){
										    		 pageEnd = current_page+move;
										    	 }else if(current_page < sumPage && current_page > (sumPage-(numFix-1) ) ){
										    		 pageFirst = sumPage-(numFix-1);
										    		 pageEnd = sumPage;
										    	 }else{
										    		 pageEnd = sumPage;
										    	 }
										     }else{
												pageFirst=1;
												if(sumPage > numFix){
													pageEnd = numFix;
												}else{
													pageEnd = sumPage;
												}
										     }
										    //lặp khoản giữa và active trang người dùng click
										    String active="";
											for (int i = pageFirst; i <= pageEnd; i++){
												if(current_page==i){
													active=" style='color :red; font-size: 20px; '  ";
												}else{
													active="";
												}
										 
										   %>  
										  
										   <a  href="<%=request.getContextPath()%>/list-register-detai?page=<%=i%>"> 
										       <span <%=active %> style="font-size: 20px;"> [<%=i %>] </span>
										    </a>  
										     	
										   <%}//for %> 	
										    	
			                             
		                                   <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage >= 1){
										   %>
										  
										   <a class="last" href="<%=request.getContextPath() %>/list-register-detai?page=<%=current_page+1%>"> 
										      <span style="font-size: 20px;">  [Next]  </span> 
										   </a> 
										 	
										  <%} %>
		                                  
		                                  
		                                   
		                               </ul>
		                           </div>
		                           
		                       </div>
							
						</div>
					</div>
					
					
			</div>
			<!-- /SITE CONTENT -->
			
		</div>
	</div>


			<%@include file="/templates/public/inc/footer.jsp" %> 