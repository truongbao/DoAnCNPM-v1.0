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
			    <div class="container">
			            
			    
			    
			    	<div>
			    	  
			    	
			    		<div class="col-xs-6">
			    			<h1 class="default_title"><a href="#">Danh sách thành viên</a></h1>
			    		</div>
			    		<div class="col-xs-6">
			    			
			    			<div class="col-xs-12 no-padding-lr">
								<div class="topbar">
									<div class="col-xs-4 pull-right">
						    				<a href="<%=request.getContextPath()%>/add-member" class="btn btn-default" style=""> Thêm thành viên</a>
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
					              case 0: out.print("<h4 style='color :red'> Thêm thất bại ! </h4> ");break; 
					              case 1: out.print("<h4 style='color :red'> Thêm thành công ! </h4> ");break; 
					              case 2: out.print("<h4 style='color :red'> Sửa thành công ! </h4> ");break; 
					              case 3: out.print("<h4 style='color :red'> Sửa thất bại ! </h4> ");break; 
					              case 5: out.print("</br><p style='color :red;float:left;margin-left:-210px;'>Sửa thất bại ! &nbsp; Bạn chỉ được nhâp thông tin 1 trong 2 trường 'Tên thành viên' </p> ");break; 
					           	     }
					           }
				          %>       
			    	
		            
		            
		            
						<table class="table">
							<thead>
							<th>
								<td>Tên thành viên</td>
								<td>Đơn vị</td>
								<td>Nội dung nghiên cứu</td>
								<td>Mã số đề tài</td>
								<td style="float:right;margin-right:14px;"><center>Sửa</center></td>
								<!-- <td><center> Xóa</center></td> -->
							</th>
							</thead>
							<tbody>
							
							<%
							    int demstt = 1 ;
							    if(request.getAttribute("ListThanhVienByIdUser")!=null){
							    	ArrayList<ThanhVien> ListThanhVienByIdUser = (ArrayList<ThanhVien>)request.getAttribute("ListThanhVienByIdUser");
							    	if(ListThanhVienByIdUser.size() > 0){
							    		for(ThanhVien objTV : ListThanhVienByIdUser){
							    			
							%>
							
							<tr>
								<td> <%=demstt %> </td>
								<td>
									<a> <%=objTV.getTenThanhVien() %> </a>
								</td>
								<td>
									<a> <%=objTV.getDonVi() %> </a>
								</td>
								<td> <%=objTV.getNoiDungNghienCuu() %> </td>
								<td> <%=objTV.getMaSoDeTai() %> </td>
								<td>
								<center>
									<div class="pull-right" >
										<a href="<%=request.getContextPath()%>/edit-member?tidTV=<%=objTV.getIdThanhVien()%>&tnameTV=<%=objTV.getTenThanhVien() %>&tidDeTai=<%=objTV.getIdDeTai() %>" class="btn btn-default" style=""> Sửa </a>
									</div>
								</center>
								</td>
								
								<!-- <td>
									<center>
										<div >
											<a href="" class="btn btn-danger " style=""> Xóa </a>
										</div>
									</center>
								</td> -->
								
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
										 
										   <a class="last" href="<%=request.getContextPath() %>/quanly-thanhvien?page=<%=current_page-1%>"> 
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
										  
										   <a  href="<%=request.getContextPath()%>/quanly-thanhvien?page=<%=i%>"> 
										       <span <%=active %> style="font-size: 20px;"> [<%=i %>] </span>
										    </a>  
										     	
										   <%}//for %> 	
										    	
			                             
		                                   <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage >= 1){
										   %>
										  
										   <a class="last" href="<%=request.getContextPath() %>/quanly-thanhvien?page=<%=current_page+1%>"> 
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


