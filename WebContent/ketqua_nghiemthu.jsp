<%@page import="model.bean.ThanhVien"%>
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

					



					<section class="mb25 section-page">
    <div class="container">
			
			
				 <div>
					<div class="col-xs-6">
						<h1 class="default_title"><a>Kết quả nghiệm thu đề tài</a></h1>
					</div>
					<div class="col-xs-6">
						
						<div class="col-xs-12 no-padding-lr">
							<div class="topbar">
								
								<div class="sub-menu-search hidden-xs hidden-sm pull-right col-xs-8">
										
								</div>									
								<div class="sub-menu-account hidden-xs hidden-sm">
									
								</div>
							</div>
						</div>
					</div>
				</div>
			
			
            <div class="page_content">
				
			<table align="center"  cellpadding="1" cellspacing="1" style="width: 800px; height:20px;">
				<tbody>
					<tr>
						<td width="4%;" colspan="3">Kết quả nghiệm thu đề tài</td>
					</tr>
					
					<%
					    if(request.getAttribute("objDeTai") != null){
					    	 DeTai objDeTai =(DeTai)request.getAttribute("objDeTai");
					%>
					
					<tr>
						<td width="4%;">1</td> <td width="25%;" style="font-weight:bold;">Mã đề tài</td>  <td>  <%=objDeTai.getMaSoDeTai() %>  </td>
					</tr>
					<tr>
						<td width="4%;">2</td> <td width="25%;" style="font-weight:bold;">Tên đề tài</td>  <td> <%=objDeTai.getTenDeTai() %> </td>
					</tr>
					<tr>
						<td width="4%;">3</td> <td width="25%;" style="font-weight:bold;">Điểm</td>  <td>  <%=objDeTai.getDiem() %> </td>
					</tr>
					<tr>
						<td width="4%;">4</td> <td width="25%;" style="font-weight:bold;">Xếp loại</td>  <td>  <%=objDeTai.getXepLoai() %> </td>
					</tr>
					<tr>
						<td width="4%;">5</td> <td width="25%;" style="font-weight:bold;">Đánh giá</td>  <td>  <%=objDeTai.getKetQuaNghiemThu() %> </td>
					</tr>
					
					
					<%} %>
					
				</tbody>
			</table>

			<p>&nbsp;</p>
			
			<%--  <div class="form-group row">
				    <div class="col-xs-2">
					
				   </div>
				   <div class="col-xs-10 ">
					  <a href="<%=request.getContextPath() %>/quanly-detai" class="btn btn-lg btn-primary pull-right">Quay lại</a>
				  </div>
			</div> --%>
            


<p>&nbsp;</p>
			</div>
    </div>

	
</section>


				</div>
			</div>
			<!-- /SITE CONTENT -->

          <%@include file="/templates/public/inc/footer.jsp" %>

			