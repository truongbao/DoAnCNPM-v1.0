<%@page import="model.bean.ThongBao"%>
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
										
										<li class="active breadcrumb-title">Thông báo</li>
										
					                </ol>
					            </div>
					        </div>
					    </div>
					</div>

					



<section class="mb25">
    <div class="container">
    
       <div class="row">
        
	    <div class="sidebar-collection col-md-5">	
			
	      <div style="margin-bottom:15px">
	        <h2 class="default_title"><a href="tin-tuc.html">Thông báo mới</a></h2>
	      </div>

                <%
				    if(request.getAttribute("listThongBaoByIdDeTai")!=null){
				    	ArrayList<ThongBao> listThongBao = (ArrayList<ThongBao>)request.getAttribute("listThongBaoByIdDeTai");
				    	if(listThongBao.size() > 0){
				    		for(ThongBao objThongBao : listThongBao){
				    			
				%>
	
				<div class="new-item col-xs-12 no-padding-lr first ">
					<div class="new-item-brbt">
						<div class="col-xs-12 col-sm-5 no-padding-l home-blog-content-left">
							<div class="new-img">
								
								<a href="ebook-nhung-em-be-khon-ngoan-day-con-tu-bao-ve-minh.html" title="">
								   <img src="<%=request.getContextPath()%>/templates/public/images/thongbao.jpg" alt="">
								</a>
								
							</div>
						</div>
						<div class="col-xs-12 col-sm-7 no-padding-r home-blog-content-right">
							<div class="home-blog-content-right-div">
			
								<h3 class="new-name"><a href="" title=""> <span style="color : red;">Thông báo: </span> 
								<%
								   if(objThongBao.getNoiDung().length() > 50){
								%>
								   <%=objThongBao.getNoiDung().substring(0,50) %>... </a>
								<%}else{ %>
								   <%=objThongBao.getNoiDung() %> </a>
								<%} %>
								
								</h3>
								
								<p class="new-info"><span><i class="fa fa-calendar"></i> <%=objThongBao.getThoiGian() %> </span> 
								<%-- <p class="new-description">
									 <%=objThongBao. %>
									<br>
								</p> --%>
							</div>
						</div>
					</div>	
			
				</div>
				
				<%}}} %>
				
	
			</div>		
			
					
			<div class="col-md-7">
				<div class="row">					
					<section class="section-blog">
						<div class="col-xs-12" style="margin-bottom:15px">
							<h1 class="default_title"><a href="#">Thông báo cũ</a></h1>
						</div>
						
							
							<div class="new-item col-xs-12 first ">
								<div class="new-item-brbt">
									<div class="col-xs-12 col-sm-5 no-padding-l home-blog-content-left">
										<div class="new-img">
											
												<a href="" title=""><img src="<%=request.getContextPath()%>/templates/public/images/thongbao.jpg" alt=""></a>
											
										</div>
									</div>
									<div class="col-xs-12 col-sm-7 no-padding-r home-blog-content-right">
										<div class="home-blog-content-right-div">

											<h3 class="new-name"><a href="" title="">Thông báo: Chỉnh sửa đề tài Thuật toán nhánh cận</a></h3>
											<p class="new-info"><span><i class="fa fa-calendar"></i> 29/ 09/ 2017</span> 
											<p class="new-description">
												 Thuật toán nhánh cận ... 
												<br>
											</p>
											<a class="theme_default_btn" href="">Xem chi tiết </a>
										</div>
									</div>
								</div>	

							</div>
							<!--End. blog-item-->
							
							
							
						
						
						
						<div class="col-xs-12">
							<div class="blog-info-page text-right">
								<div class="filter-right">
		      
		                            
		                            <div class="collection-pagination pull-right pagination-wrapper">
		                                <ul class="pagination">
		                                    
				                          <li class="active"><span>1</span></li>
			                                    
			                             <li><a href="tin-tuc4658.html?page=2">2</a></li>
		                                    
		                                 <li>
		                                     <a class="last " aria-label="Next" href="tin-tuc4658.html?page=2"></a>
		                                  </li>
		                                    
		                                </ul>
		                            </div>
		                            
		                        </div>
								
							</div>
						</div>
										
							
					</section>
				</div>

			</div>
			
        </div>
    </div>
</section>


				</div>
			</div>
			<!-- /SITE CONTENT -->

         <%@include file="/templates/public/inc/footer.jsp" %> 


