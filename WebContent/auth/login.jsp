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
					
						 <li class="active breadcrumb-title">Đăng nhập</li>
					
                </ol>
            </div>
        </div>
    </div>
</div>

					



					<section class="section-contact">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
            
             <%
		        if(request.getParameter("msg")!=null){
		          int msg = Integer.parseInt( request.getParameter("msg") );
		          switch(msg){
		           // case 1: out.print("<h4 style='color : white ;border: 1px solid;padding: 4px 6px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold'>Xóa thành công !!</h4> ");break;
		            case 0: out.print("<h4 style='color :red'> Username hoặc password không hợp lệ ! </h4> ");break;
		           	     }
		           }
	          %>       
            
				<h1 class="default_title">Đăng nhập</h1>
				
                <div class="section-article contactpage">
				
                   	<form accept-charset='UTF-8' action="<%=request.getContextPath() %>/auth/public/login" method="post" id='contact' >
						
						<div class="form-inline form-comment">
						
							<div class="col-xs-12 col-sm-12 col-md-12 no-padding-lr form-group">
								<input placeholder="Tên Đăng Nhập:" id="username" name="username" class="form-control" type=text />
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 no-padding-lr form-group">
								<input placeholder="Mật Khẩu:" id="password" name="password" class="form-control" type="password" />
							</div>
							
							<input type="submit"  class="btn btn-cart btn-default"  value="Đăng nhập" />
							
						</div>
					
                   </form>
				   
                </div>
            </div>	
			
			
            <div class="col-md-5 col-md-offset-1">
                <div class="widget-item">
					<a href="index.html" class="logo" title="">			
							<img src="images/logo.png" alt="">													
					</a>
					
                    <ul class="widget-menu">
                        <li class="widget-address">
						    <span style="text-align: justify;">
						       Là nơi đào tạo nguồn nhân lực kỹ thuật, công nghệ chất lượng cao 
							   và cung cấp các dịch vụ khoa học, công nghệ đáp ứng nhu cầu phát triển bền vững 
							   kinh tế - xã hội của khu vực miền Trung – Tây Nguyên và cả nước.
						   </span>
						</li>
						 <li class="widget_contact_social">
							<ul class="widget-menu contact_social">
										<li><a target="_blank" title="Facebook" href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a></li>
										<li><a target="_blank" title="Linkedin" href="https://www.linkedin.com/#"><i class="fa fa-linkedin"></i></a></li>
										<li><a target="_blank" title="vk" href="https://vk.com/"><i class="fa fa-vk"></i></a></li>
										<li><a target="_blank" title="Youtube" href="https://twitter.com/"><i class="fa fa-twitter"></i></a></li>									
						   </ul>
						</li>							 
                        <li class="i_color"><i class="fa fa-map-marker"></i> <span>Trường Đại Học Bách Khoa</span></li>
                        <li class="i_color"><i class="fa fa-phone"></i> <span>(+84) 2203 848 999</span> - <span>(+84) 2203 848 999</span></li>
                        <li class="i_color"><i class="fa fa-envelope"></i> <span>daotao.dut.udn.vn</span></li>
						<li class="i_color"><i class="fa fa-fax"></i> <span>(+84) 9478 456 89</span></li>
						
                    </ul>

                    <!-- End .widget-menu -->

                </div>
                <!-- End .widget-item -->
            </div>

        </div>
    </div>
</section>


				</div>
			</div>
			<!-- /SITE CONTENT -->

               <%@include file="/templates/public/inc/footer.jsp" %>
        