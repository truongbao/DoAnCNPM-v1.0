<%@page import="model.bean.Khoa"%>
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
						<button href="<%=request.getContextPath()%>/gioi-thieu"
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
						   
							<form action="<%=request.getContextPath()%>/search-giangvien"  method="get">
									
								<div class="col-md-4">
									<input type="text" name="txtSearch" class="form-control"  placeholder="Tên giảng viên">
								</div>
								
								<div class="col-md-4">
									<select name="idKhoa" class="form-control">
									      <option  value="0">-------------- Chọn -------------</option>
										  <%
											if (request.getAttribute("listKhoa") != null){
												ArrayList<Khoa> listKhoa = (ArrayList<Khoa>) request.getAttribute("listKhoa");
												
												if (listKhoa.size() > 0){
													for (Khoa objKhoa : listKhoa){
										  %>
											<option  value="<%=objKhoa.getIdKhoa()%>"><%=objKhoa.getTenKhoa() %></option>
										  <%
													}	
												}
											}
										  %>  
										 
									</select>
								</div>
								
								<div class="col-md-4">
								    <button class="btn">Tìm kiếm</button> 
									<!-- <input  class="btn" type="submit" value="" name="timkiem"/> -->
								</div>
								
							</form>
						
						</p>
						
					</div>
					
					
					
					<div class="col-xs-12 col-sm-12 col-md-12 col-md-offset-0 std_icon text-center">
					
					<%
					 if(request.getAttribute("listGiangVienPublic")!=null){
					    	ArrayList<User> listGiangVienPublic = (ArrayList<User>)request.getAttribute("listGiangVienPublic");
					    	if(listGiangVienPublic.size() > 0){
					    		for(User objUser : listGiangVienPublic){
					
					%>
					
						<div class="col-sm-6 col-xs-6 col-md-3 std_ic_ct_pr no-padding-lr">
							<div class="">
								<a href="<%=request.getContextPath()%>/thongtin-giangvien?uid=<%=objUser.getIdUser() %>"> <img
									src="<%=request.getContextPath()%>/templates/public/images/thanhViet.jpg" alt="">
								</a>
							</div>
							<a href="<%=request.getContextPath()%>/thongtin-giangvien?uid=<%=objUser.getIdUser() %>" class="std_ic_title">
							     <% if(objUser.getIdHocViHocHam() == 1) { %> 
							        GS. <%=objUser.getFullName() %>
							     <%}else if(objUser.getIdHocViHocHam() == 2){ %>   
							        PGS. <%=objUser.getFullName() %>
							     <%}else if(objUser.getIdHocViHocHam() == 3){ %>   
							        TS. <%=objUser.getFullName() %>
							     <%}else if(objUser.getIdHocViHocHam() == 4){ %>   
							        ThS. <%=objUser.getFullName() %>
							     <%}else{ %>   
							        GV. <%=objUser.getFullName() %>
							      <%} %>  
						     </a><br> 
						    <a>
								<p> Khoa : <%=objUser.getTenKhoa() %> </p>
								<p> <%=objUser.getDiaChiCoQuan() %> </p>
							</a></br>
						</div>
						
						<!-- hôi lỗi thì thêm vao 3 tên nữa -->
						<%}}} %>
					</div>
					 
					
				</div>
				
				
				
				<div class="col-xs-12">   <!-- phân trang  -->
						<div class="blog-info-page text-right">
							<div class="filter-right">
		     
		                           <div class="text-center">
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
										 
										   <a class="last" href="<%=request.getContextPath() %>/?page=<%=current_page-1%>"> 
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
										  
										   <a  href="<%=request.getContextPath()%>/?page=<%=i%>"> 
										       <span <%=active %> style="font-size: 20px;"> [<%=i %>] </span>
										    </a>  
										     	
										   <%}//for %> 	
										    	
			                             
		                                   <%
										    //nếu curren_Page  <sumPage và sumPage > 1 thì thêm Next
										    if(current_page < sumPage && sumPage >= 1){
										   %>
										  
										   <a class="last" href="<%=request.getContextPath() %>/?page=<%=current_page+1%>"> 
										      <span style="font-size: 20px;">  [Next]  </span> 
										   </a> 
										 	
										  <%} %>
		                                  
		                                  
		                                   
		                               </ul>
		                           </div>
		                           
		                       </div>
							
						</div>
					</div>   <!-- phân trang  -->
				
				
			</div>
		</section>


		<!-- /SITE CONTENT -->



		<%@include file="/templates/public/inc/footer.jsp" %> 
		
		
		