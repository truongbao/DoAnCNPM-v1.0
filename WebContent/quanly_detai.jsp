<%@page import="library.LibraryConstant"%>
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
									
										 <li class="active breadcrumb-title">Quản lý đề tài</li>
									
				                </ol>
				            </div>
        				</div>
    				</div>
				</div>
			    <div class="container">
			    	<div>
			    		<div class="col-xs-6">
			    			<h1 class="default_title"><a href="#">Thông tin đề tài</a></h1>
			    		</div>
			    		<div class="col-xs-6">
			    			
			    			<div class="col-xs-12 no-padding-lr">
								<div class="topbar">
									
									<div class="sub-menu-search hidden-xs hidden-sm pull-right col-xs-8">
											<form action="http://www.mamnonnumberone.edu.vn/search" method="get">

												<div class="input-group search_form_action">
													<input type="text" class="form-control" maxlength="70" name="query" id="search" placeholder="Tìm kiếm đề tài..." autocomplete="off">
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
								<td>Tên đề tài</td>
								<td>Chủ nhiệm đề tài</td>
								<td>TG đăng ký</td>
								<td>TG bắt đầu</td>
								<td>TG kết thúc</td>
								<td>Trạng thái</td>
								<td style="float:right;margin-right:14px;"><center>Cập nhật</center></td>
								
							</th>
							</thead>
							<tbody>
							
							<%
							  if(request.getAttribute("listDeTai")!=null){
		                            ArrayList<DeTai> listDeTai = (ArrayList<DeTai>)request.getAttribute("listDeTai");
		                              if(listDeTai.size() > 0){
		                            	   for(DeTai objDetai : listDeTai){
							%>
							
							<tr>
								<td><%=objDetai.getIdDeTai() %></td>
								<td>
									<a href="<%=request.getContextPath()%>/detail-detai?did=<%=objDetai.getIdDeTai()%>"> <%=objDetai.getTenDeTai() %> </a>
								</td>
								<td> <%=objDetai.getFullName() %> </td>
								<td> <%=objDetai.getThoiGianDangKy() %> </td>
								<td> <%=objDetai.getThoiGianBatDau() %> </td>
								<td> <%=objDetai.getThoiGianKetThuc() %> </td>
								<td> <%= LibraryConstant.ConvertTrangThai(objDetai.getTrangThai())  %> </td>
								<td>
									<center>
										<div class="pull-right" >
											<a href="<%=request.getContextPath()%>/update-detai?did=<%=objDetai.getIdDeTai()%>" class="btn btn-default" style=""> Sửa đề tài </a>
										</div>
									</center>
								</td>
								
							</tr>
							
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
										 
										   <a class="last" href="<%=request.getContextPath() %>/quanly-detai?page=<%=current_page-1%>"> 
										       <span style="font-size: 20px;">  Back </span>
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
										  
										   <a  href="<%=request.getContextPath()%>/quanly-detai?page=<%=i%>"> 
										       <span <%=active %> style="font-size: 20px;"> [<%=i %>] </span>
										    </a>  
										     	
										   <%}//for %> 	
										    	
			                             
		                                   <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage >= 1){
										   %>
										  
										   <a class="last" href="<%=request.getContextPath() %>/quanly-detai?page=<%=current_page+1%>"> 
										      <span style="font-size: 20px;">  Next  </span> 
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
			
			