<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/public/inc/header.jsp"%>

<!-- SITE CONTENT
=========================================================================== -->
<div id="site-content">
	<div id="main">

		<h1 style="display: none;">Trường Đại Học Bách Khoa</h1>
		<div class="home_slide_banner">
			<div class="container">
				<div class="row ">
					<div class="col-xs-12 pull-left col-md-9">
						<div class="home-slide">

							<div id="owl-demo-slide" class="owl-carousel owl-theme">

								<div class="item">
									<a href="#" title="Slide"><img src="<%=request.getContextPath()%>/templates/public/images/slide5.jpg"
										alt="Slide"> </a>

								</div>


								<div class="item">
									<a href="#" title="Slide"><img src="<%=request.getContextPath()%>/templates/public/images/slide4.jpg"
										alt="Slide"> </a>
								</div>


								<div class="item">
									<a href="#" title=""><img src="<%=request.getContextPath()%>/templates/public/images/slide2.jpg" alt="">
									</a>
								</div>


								<div class="item">
									<a href="#" title=""><img src="<%=request.getContextPath()%>/templates/public/images/slide1.jpg" alt="">
									</a>
								</div>

							</div>
						</div>
						<!--End. Home-slide-->
					</div>
					<div class="time_work col-xs-12 pull-right col-md-3">
						<div class="col-md-12 col-sm-7 col-xs-12 no-padding-lr">
							<h2 class="time_work_title">Thời gian làm việc :</h2>
							<div
								class="time_work_content col-xs-6 col-sm-6 col-md-12 no-padding-lr">
								<div class="policy-item">
									<span class="policy-icon"> <img
										src="<%=request.getContextPath()%>/templates/public/assets/alarm8b77.png?1492665207586" alt="Thứ 2 - Thứ 7">
									</span>
									<!-- End .policy-icon -->
									<span class="policy-text">
										<h5>Thứ 2 - Thứ 6</h5>
										<p>7:00 am - 5:20 pm</p>
									</span>
									<!-- End .policy-text -->
								</div>
							</div>
							<div class="time_work_content">
								<div
									class="col-md-12 home_banner_1 col-sm-5 hidden-xs no-padding-lr">
									<a href="<%=request.getContextPath()%>/gioi-thieu" title="Banner"><img
										src="<%=request.getContextPath()%>/templates/public/images/slide3.jpg" alt="Banner" /></a>
								</div>
							</div>
						</div>
						<div
							class="col-md-12 home_banner_1 col-sm-5 hidden-xs no-padding-lr">
							<a href="<%=request.getContextPath()%>/gioi-thieu" title="Banner"><img
								src="<%=request.getContextPath()%>/templates/public/images/slide6.jpg" alt="Banner" /></a>
						</div>
					</div>
				</div>
			</div>
		</div>


		<section class="home_about">
			<div class="container">
				<div class="row ">
					<div class="col-xs-12 col-sm-12 col-md-6">
						<h2 class="default_title">
							<a href="gioi-thieu.html">Giới thiệu</a>
						</h2>
						<p>Sứ mệnh của trường là nơi đào tạo nguồn nhân lực kỹ thuật,
							công nghệ chất lượng cao và cung cấp các dịch vụ khoa học, công
							nghệ đáp ứng nhu cầu phát triển bền vững kinh tế - xã hội của khu
							vực miền Trung – Tây Nguyên và cả nước.</p>
						<button href="gioi-thieu.html"
							class="theme_default_btn btn_cl_newtab">
							Khám phá <img alt="Khám phá"
								src="<%=request.getContextPath()%>/templates/public/assets/theme_default_btn_bg-min8b77.png?1492665207586">
						</button>
						<div class="row hidden-xs hidden-sm">
							<div class="small_about_thumbnail">
								<div class="col-md-4">
									<div class="ov">
										<img src="<%=request.getContextPath()%>/templates/public/images/slide1.jpg" alt="banner">
									</div>
								</div>
								<div class="col-md-4">
									<div class="ov">
										<img src="<%=request.getContextPath()%>/templates/public/images/slide2.jpg" alt="banner">
									</div>
								</div>
								<div class="col-md-4">
									<div class="ov">
										<img src="<%=request.getContextPath()%>/templates/public/images/slide3.jpg" alt="Banner">
									</div>
								</div>
							</div>

						</div>
					</div>
					<div class="home_about_big_banner ff col-xs-12 col-sm-12 col-md-6">
						<a href="<%=request.getContextPath()%>/gioi-thieu" title="Banner"><img height="350px;"
							src="<%=request.getContextPath()%>/templates/public/images/slide7.jpg" alt="Banner" /></a>
					</div>



				</div>
			</div>
		</section>



		<section class="stydy_hard">
			<div class="container">
				<div class="row">
					<h2 class="default_title text-center">Giảng Viên</h2>
					<div
						class="col-xs-12 col-sm-12 col-md-8 col-md-offset-3 no-padding-lr">
						<p class="text-center">
						<div class="col-md-4">
							<input type="text" name="" class="form-control"
								placeholder="Tên giảng viên">
						</div>
						<div class="col-md-4">
							<select class="form-control">
								<option>Khoa Cơ Khí</option>
								<option>Khoa Công nghệ Thông tin</option>
								<option>Khoa Xây dựng Cầu - Đường</option>
							</select>
						</div>
						<div class="col-md-4">
							<button class="btn">Tìm kiếm</button>
						</div>
						</p>
					</div>
					
					
					
					<div class="col-xs-12 col-sm-12 col-md-12 col-md-offset-0 std_icon text-center">
					
					<%
					 if(request.getAttribute("listUser")!=null){
					    	ArrayList<User> listUser = (ArrayList<User>)request.getAttribute("listUser");
					    	if(listUser.size() > 0){
					    		for(User objUser : listUser){
					
					%>
					
						<div class="col-sm-6 col-xs-6 col-md-3 std_ic_ct_pr no-padding-lr">
							<div class="">
								<a href="chuong-trinh-chat-luong-cao.html"> <img
									src="<%=request.getContextPath()%>/templates/public/images/thanhViet.jpg" alt="">
								</a>
							</div>
							<a href="chuong-trinh-chat-luong-cao.html" class="std_ic_title">
							     <% if(objUser.getIdHocVi() == 1) { %> 
							        GS. <%=objUser.getFullName() %>
							     <%}else if(objUser.getIdHocVi() == 2){ %>   
							        PGS. <%=objUser.getFullName() %>
							     <%}else if(objUser.getIdHocVi() == 3){ %>   
							        TS. <%=objUser.getFullName() %>
							     <%}else if(objUser.getIdHocVi() == 4){ %>   
							        ThS. <%=objUser.getFullName() %>
							     <%}else{ %>   
							        GV. <%=objUser.getFullName() %>
							      <%} %>  
						     </a><br> 
						    <a href="">
								<p>Khoa Cơ Khí</p>
								<p>Trường Đại học Bách khoa</p>
							</a>
						</div>
						
						<!-- hôi lỗi thì thêm vao 3 tên nữa -->
						<%}}} %>
					</div>
					
					
					
				</div>
			</div>
		</section>


		<!-- /SITE CONTENT -->



		<%@include file="/templates/public/inc/footer.jsp" %> 
		
		
		