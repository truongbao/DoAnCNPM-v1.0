<%@page import="model.dao.ThongBaoDAO"%>
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
        
	    <div class="sidebar-collection col-md-12">	
			
	      <div style="margin-bottom:15px">
	        <h2 class="default_title"><a href="tin-tuc.html">Thông báo</a></h2>
	      </div>

                <%
				    if(request.getAttribute("listThongBaoByIdDeTai")!=null){
				    	ArrayList<ThongBao> listThongBao = (ArrayList<ThongBao>)request.getAttribute("listThongBaoByIdDeTai");
				    	if(listThongBao.size() > 0){
				    		for(ThongBao objThongBao : listThongBao){
				    			
				%>
	
				<div class="new-item col-xs-12 no-padding-lr first ">
					<div class="new-item-brbt">
					
						<div class="col-xs-12 col-sm-12 no-padding-r home-blog-content-right">
							<div class="home-blog-content-right-div">
			
								<h3 class="new-name">
								
								<%
								   ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
								   if( thongBaoDAO.maxIdThongBao(objThongBao.getIdDeTai()) == objThongBao.getIdThongBao() && objThongBao.getWasRead() == 0
										   &&( (Integer)(request.getAttribute("idDeTai"))) == objThongBao.getIdDeTai() ){
								 
								%>
								
								  <a> <span>Thông báo mới: </span> 
								       <span style="color : red;">  <%=objThongBao.getNoiDung() %> </span> 
								  </a>
								
								
								
								<%}else{ %>
								      <a> <span>Thông báo : </span> 
									       <span>  <%=objThongBao.getNoiDung() %> </span> 
								      </a>
								 
								<%} %>
								
								
								</h3>
								
								<p class="new-info"><span><i class="fa fa-calendar"></i> <%=objThongBao.getThoiGian() %> </span> 
								
							</div>
						</div>
					</div>	
			
				</div>
				
				<%}}} %>
				
	
			</div>		
			
			
			<div class="form-group row">
				<div class="col-xs-2">
					
				</div>
				<div class="col-xs-10 ">
					<a href="<%=request.getContextPath() %>/quanly-detai" class="btn btn-lg btn-primary pull-right ml10" >Quay lại</a>
				</div>
			</div>
			 
			
			
        </div>
    </div>
</section>


				</div>
			</div>
			<!-- /SITE CONTENT -->

         <%@include file="/templates/public/inc/footer.jsp" %> 


