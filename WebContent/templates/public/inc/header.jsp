<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Cat"%>
<%@page import="model.dao.CatDAO"%>    
    
<!DOCTYPE html>

<html lang="en">
	
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<head>
        <title>Trang Chủ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=edge">
		<meta http-equiv="content-language" content="vi" />
		<meta name="robots" content="noodp,index,follow" />
		<meta name='revisit-after' content='1 days' />
		<meta name="description" content="Trường Đại Học Bách Khoa">
		
		  <meta property="og:type" content="website">
		  <meta property="og:title" content="Trường Đại Học Bách Khoa">
		  <meta property="og:image" content="<%=request.getContextPath()%>/templates/public/assets/logo8b77.png?1492665207586">
		  <meta property="og:image:secure_url" content="<%=request.getContextPath()%>/templates/public/assets/logo8b77.png?1492665207586">


		  <meta property="og:description" content="Trường Đại Học Bách Khoa">

		  <meta property="og:url" content="index.html">
		  <meta property="og:site_name" content="Trường Đại Học Bách Khoa">
		  <link rel="canonical" href="index.html">
		  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

		<!-- Favorite Icons
============================================================================== -->
		<link rel="icon" href="<%=request.getContextPath()%>/templates/public/assets/favicon8b77.png?1492665207586" type="image/x-icon" />

		<!-- Web Fonts & Font Awesome
============================================================================== -->
		<link href='' rel='stylesheet' type='text/css'>

		<link href='<%=request.getContextPath()%>/templates/public/assets/font-awesome8b77.css?1492665207586' rel='stylesheet' type='text/css' />
		<!-- CSS styles | Thứ tự bootstrap.css trước custom.css sau
============================================================================== -->

		<link href='<%=request.getContextPath()%>/templates/public/assets/normalize8b77.css?1492665207586' rel='stylesheet' type='text/css' />
		<link href='<%=request.getContextPath()%>/templates/public/assets/owl.carousel8b77.css?1492665207586' rel='stylesheet' type='text/css' />	
		<link href='<%=request.getContextPath()%>/templates/public/assets/owl.theme8b77.css?1492665207586' rel='stylesheet' type='text/css' />
		<link href='<%=request.getContextPath()%>/templates/public/assets/slick8b77.css?1492665207586' rel='stylesheet' type='text/css' />
		<link href='<%=request.getContextPath()%>/templates/public/assets/bootstrap8b77.css?1492665207586' rel='stylesheet' type='text/css' />
		<link href='<%=request.getContextPath()%>/templates/public/assets/main8b77.css?1492665207586' rel='stylesheet' type='text/css' />
		<link href='<%=request.getContextPath()%>/templates/public/assets/bootstrap-select8b77.css?1492665207586' rel='stylesheet' type='text/css' />



		<script src='<%=request.getContextPath()%>/templates/public/assets/modernizr-2.8.3.min8b77.js?1492665207586' type='text/javascript'></script>
		<script src='<%=request.getContextPath()%>/templates/public/assets/jquery-1.9.1.min8b77.js?1492665207586' type='text/javascript'></script>	
		<script src='<%=request.getContextPath()%>/templates/public/assets/bootstrap-select8b77.js?1492665207586' type='text/javascript'></script>	

		
		<!-- <script>
			var Bizweb = Bizweb || {};
			Bizweb.store = 'daotao.dut.udn.vn';
			Bizweb.theme = {"id":170983,"name":"Sunshine School","role":"main","previewable":true,"processing":false,"created_on":"2016-09-29T06:58:25Z","modified_on":"2016-09-29T06:59:02Z"}
			Bizweb.template = 'index';
		</script>

                <script>
               
                      (function() {
                        var s = document.createElement('script'); s.type = 'text/javascript'; s.async = true;
                        s.src = 'js/bizweb_statsc164.js?v=9';
                        var x = document.getElementsByTagName('script')[0]; x.parentNode.insertBefore(s, x);
                      })();

              
                </script> -->

				<script>
						(function() {
						function asyncLoad() {
						var urls = ["js/widgets3316.js?store=daotao.dut.udn.vn","js/widgets3316.js?store=daotao.dut.udn.vn","js/widgets3316.js?store=daotao.dut.udn.vn","js/widgets3316.js?store=daotao.dut.udn.vn","js/widgets3316.js?store=daotao.dut.udn.vn","js/widgets.js?store=daotao.dut.udn.vn"];
						for (var i = 0; i < urls.length; i++) {
						var s = document.createElement('script');
						s.type = 'text/javascript';
						s.async = true;
						s.src = urls[i];
						s.src = urls[i];
						var x = document.getElementsByTagName('script')[0];
						x.parentNode.insertBefore(s, x);
						}
						}
						window.attachEvent ? window.attachEvent('onload', asyncLoad) : window.addEventListener('load', asyncLoad, false);
						})();
				</script>

<script type='text/javascript'>
(function() {
var log = document.createElement('script'); log.type = 'text/javascript'; log.async = true;
log.src = 'js/82010e034.js?lang=vi';
var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(log, s);
})();
</script>

<!-- Google Tag Manager -->
<noscript>
<iframe src='js/gtm5445' height='0' width='0' style='display:none;visibility:hidden'></iframe>
</noscript>
<script>
(function (w, d, s, l, i) {
w[l] = w[l] || []; w[l].push({
'gtm.start':
new Date().getTime(), event: 'gtm.js'
}); var f = d.getElementsByTagName(s)[0],
j = d.createElement(s), dl = l != 'dataLayer' ? '&l=' + l : ''; j.async = true; j.src ='js/gtm5445.js?id=' + i + dl; f.parentNode.insertBefore(j, f);
})(window, document, 'script', 'dataLayer', 'GTM-MS77Z9');
</script>
<!-- End Google Tag Manager -->

</head>
	
	
	
<body id="top">

		<script src='<%=request.getContextPath()%>/templates/public/assets/owl.carousel8b77.js?1492665207586' type='text/javascript'></script>

		<div id="page-wrapper">
			<!-- SITE HEADER
=========================================================================== -->
			<div id="site-header">
				<header id="header" class="header">


					<!-- main header -->
					<div class="header-main">
						<div class="container">
							<div class="row ">
								<div class="col-xs-12 col-sm-12 hidden-md hidden-lg sub-menu-account">

								</div>
								<div class="col-xs-12 col-sm-12 col-md-2 logo ">
									<div class="logo_content">
										<a href="index.html" class="logo" title="Trường Đại Học">			
																				
											<img src="<%=request.getContextPath()%>/templates/public/images/logo.png" alt="Trường Đại Học Bách Khoa">													
											
										</a>
									</div>
								</div>	
								<div class="hidden-xs hidden-sm col-xs-12 col-sm-12 pull-right col-md-10 no-padding-l">
									<div class="col-xs-12 no-padding-lr">
										<div class="topbar">
											<div class="sub-menu-search hidden-xs hidden-sm pull-right">
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
									<div class="col-xs-12 no-padding-lr">
										<section class="menubar pull-right">
											<div class="menu hidden-sm hidden-xs">
												<ul class="list-unstyled">
													
													
													<li class="first ">
														<a href="<%=request.getContextPath()%>/home" class="menu_active"><span>Trang chủ</span></a>
													</li>
													
													<li class=" sub-menu"><a href="<%=request.getContextPath()%>/gioi-thieu" class=""><span>Giới thiệu</span></a>
														<ul class="list-unstyled">
															
															
															<li class="first ">
																<a href="<%=request.getContextPath()%>/gioi-thieu"><span>Giới thiệu về trường</span></a>	
															</li>
															
														</ul>

													</li>
													
												
													<%  
													  if(session.getAttribute("sobjUserPublic") != null ){
													 %>
													<li class="">
														<a href="<%=request.getContextPath()%>/thong-bao" class=""><span>Thông báo</span></a>
													</li>
													
													
													<li class=" sub-menu"><a href="" class=""><span>Quản Lý</span></a>
														<ul class="list-unstyled">
															
															<li class="first ">
																<a href="<%=request.getContextPath()%>/list-register-detai"><span>Quản lý đăng ký đề tài</span></a>	
															</li>
															
															<li class="">
																<a href="<%=request.getContextPath()%>/quanly-taikhoan"><span>Quản lý tài khoản</span></a>	
															</li>
															
															<li class="last ">
																<a href="<%=request.getContextPath()%>/quanly-detai"><span>Quản lý đề tài</span></a>	
															</li>
															
															<li class="last ">
																<a href="<%=request.getContextPath()%>/quanly-thanhvien"><span>Quản lý thành viên</span></a>	
															</li>
															
														</ul>

													</li>
													
													<%}%>
													
													<li class="last ">
														<a href="<%=request.getContextPath()%>/lien-he" class=""><span>Liên Hệ</span></a>
													</li>
													
													
													<%
													if(session.getAttribute("sobjUserPublic") != null ){
														 User sobjUserPublic = (User)session.getAttribute("sobjUserPublic");
													%>
													<li class="last ">
														 <a href="<%=request.getContextPath()%>/auth/public/logout" class=""><span>Đăng Xuất</span></a>
													</li>
													<li class="last " style="margin-left: -22px;margin-right: -25px;"><a><span>>></span></a> </li>
													<li class="last ">
													   <a href="<%=request.getContextPath()%>/quanly-taikhoan"> <span> <%=sobjUserPublic.getFullName() %> </span></a>
													</li>
													<%}else{ %>
													
													<li class="last ">
														<a href="<%=request.getContextPath()%>/auth/public/login" class=""><span>Đăng nhập</span></a>
													</li>
													<%} %>
													
													
													
													
													

												</ul>
											</div>


										</section>
									</div>
								</div>


								<div class="col-xs-12 col-sm-12 hidden-md hidden-lg mobile-menu">
									<div class="no-padding-l mb-menu-title hidden-md hidden-lg">
										<a href="javascript:void(0);" class="bars-navigation"><i class="fa fa-bars"></i></a>

									</div>
									<div class="no-padding-r hidden-md hidden-lg tablet_search">
										<div class="sub-menu-search pull-right">
											<form action="http://www.google.com" method="get">
												<div class="input-group search_form_action">
													<input type="text" class="form-control" maxlength="70" name="query" id="search" placeholder="Tìm kiếm..." autocomplete="off">
													<span class="input-group-btn">
														<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
													</span>
												</div>
												<!-- /input-group -->
											</form>
										</div>
									</div>
									<div class="col-xs-12 col-sm-12">
										<ul class="list-unstyled mobimenu" style="display: none">
											
											
											<li class="first ">
												<a href="index.html"><span>Trang chủ</span></a>
											</li>
											
											
											
											<li class=" sub-menu"><a href="gioi-thieu.html"><span>Giới thiệu</span></a>
												<ul class="list-unstyled" style="display: none">
													
													
													<li class="first ">
														<a href="gioi-thieu.html"><span>Giới thiệu về trường</span></a>	
													</li>
													
													<li class="">
														<a href="co-so-vat-chat.html"><span>Cơ sở vật chất</span></a>	
													</li>
													
													
													
													<li class="last ">
														<a href="co-so-vat-chat.html"><span>Đội ngũ giảng viên</span></a>	
													</li>
													
													
												</ul>
											</li>
											
											
											
											<li class="">
												<a href="tin-tuc.html"><span>Tin tức sự kiện</span></a>
											</li>
											
											
											
											<li class=" sub-menu"><a href="tuyen-dung.html"><span>Tuyển Sinh</span></a>
												<ul class="list-unstyled" style="display: none">
													
													
													<li class="first ">
														<a href="tuyen-dung.html"><span>Thông tin tuyển sinh</span></a>	
													</li>
													
													
													
													<li class="">
														<a href="tuyen-dung.html"><span>Hướng dẫn tuyển sinh</span></a>	
													</li>
													
													
													
													<li class="last ">
														<a href="co-so-vat-chat.html"><span>Đăng ký nhập học trực tuyến</span></a>	
													</li>
													
													
												</ul>
											</li>
											
											<li class="last ">
												<a href="lien-he.html"><span>Liên Hệ</span></a>
											</li>
											
											

										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- /main header -->

					<!-- top header bar -->

					<!-- End .topbar -->
					<!-- /top header bar -->
				</header>
			</div>
			<!-- /SITE HEADER -->



    