<%@page import="model.bean.HopDong"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.DeTai"%>
<%@page import="model.dao.UserDAO"%>
<%@page import="model.dao.HopDongDAO"%>
<%@page import="model.dao.DetaiDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   
   <script type="text/javascript">
			     $(document).ready(function(){
						$("#frmHopDong").validate({
							rules:{
								"ten_KH":{
									required: true
								},
								"de_tai":{
									required: true
								},
								"thoi_gian_bat_dau":{
									required: true
								},
								"giang_vien":{
									min:1
								},
								"email_KH":{
									 required:true,
									  email:true
									
								},
								"thoi_gian_ket_thuc":{
									required: true
								},
								"kinh_phi":{
									required: true
								}
							},
							messages:{
								"ten_KH":{
									required: "<p> <span style='color:red; font-size: 15;' > Vui lòng nhập vào tên khách hàng</span> </p>"
								},
								"de_tai":{
									required: "<p> <span style='color:red; font-size: 15;' > Giảng viên chưa có đề tài được duyệt, vui lòng chọn lại! </span> </p>"
								},
								"giang_vien":{
									min: "<p> <span style='color:red; font-size: 15;' > Vui lòng chọn giảng viên </span> </p>"
								},
								"email_KH":{
								    required:"<p style='color:red; font-size: 15;'> Vui lòng nhập email </p>",	
			                        email: "<p style='color:red; font-size: 15;'> Vui lòng nhập đúng định dạng email : ai_do@example.com </p> "	
								},
								"thoi_gian_bat_dau":{
									required: "<p> <span style='color:red; font-size: 15;' > Vui lòng nhập vào thời gian bắt đầu</span> </p>"
								},
								"thoi_gian_ket_thuc":{
								    required:"<p style='color:red; font-size: 15;'> Vui lòng nhập thời gian kết thúc </p>",	
			                     },
								"kinh_phi":{
								    required:"<p style='color:red; font-size: 15;'> Vui lòng nhập kinh phí </p>",	
			                     }
							}
						});// ham tren
			     });
			</script>
    

        <div class="content">
           <div class="container-fluid">
              <div class="row">
                 <div class="col-lg-12 col-md-12">
                    <div class="card">
                       <div class="header">
                            <h4 class="title" style="text-align: center; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">Sửa Hợp Đồng</h4>
                        </div>
                              
                        <div class="content">
                        	<%
                        		UserDAO userDao = new UserDAO();
                        		DetaiDAO detaiDao = new DetaiDAO();
                        		HopDong objHopDong = (HopDong) request.getAttribute("objHopDong");
                        		if (objHopDong != null) {
                           	%>
                            <form id="frmHopDong" action="<%=request.getContextPath() %>/admin/hopdong/edit?uid=<%=objHopDong.getIdHopDong() %>" method="post">
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Tên khách hàng</label>
		                                 <input type="text" name="ten_KH" class="form-control border-input" placeholder="Họ tên" value="<%=objHopDong.getTenKhachHang()%>">
		                             </div>
		                         </div>
		                         <div class="col-md-2">
		                             <div class="form-group height-100">
		                                 <div class="form-group height-100">
		                                 <label>Chức vụ khách hàng</label>
		                                 <input type='text' name="chuc_vu_KH"  class="form-control border-input" value="<%=objHopDong.getChucVuKH()%>" >
		                                 </div>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Địa chỉ khách hàng</label>
		                                 <input type="text" name="dia_chi_KH" class="form-control border-input" style="height: 40px!important" value="<%=objHopDong.getDiaChiKH()%>" >
		                             </div>
		                         </div>
		                         <div class="col-md-4">
		                             <div class="form-group height-100">
		                                 <label>Email khách hàng</label>
		                                 <input type="text" name="email_KH" class="form-control border-input" style="height: 40px!important" value="<%=objHopDong.getEmailKH()%>" >
		                             </div>
		                         </div>
		                             <div class="col-md-5">
		                                 <div class="form-group height-100">
		                                     <label for="read">Giảng viên</label>
		                                     <select name="giang_vien" class="form-control border-input" id="giangvien-hopdong">
		                                     
		                                     <option class="text-center" value="0">---</option>
		                                     <%
		                                    	ArrayList<User > listGiangVien = userDao.getListGiangVienDetai();
		                                    	for(int i = 0; i < listGiangVien.size(); i++) {
		                                    		if(objHopDong.getIdGiangVien() == listGiangVien.get(i).getIdUser()) {
	                                    	%>
	                                    		<option selected value="<%= listGiangVien.get(i).getIdUser() %>"><%=listGiangVien.get(i).getFullName() + "___" + listGiangVien.get(i).getUserName() %></option>
                                               <%} else { %>
                                               	<option value="<%= listGiangVien.get(i).getIdUser() %>"><%=listGiangVien.get(i).getFullName() + "___" + listGiangVien.get(i).getUserName() %></option>
                                               	<%} 
                                               	}%>
		                                 	</select>
		                                 </div>
		                             </div>
		                          <div class="col-md-4">
		                             <div class="form-group height-100">
		                                 <label>Đề tài</label>
		                                 <div id="list-detai-follow-giangvien">
		                                 	<select name='de_tai' class='form-control border-input'>
			                                 <%
			                                 ArrayList<DeTai> listDeTai= userDao.getListDeTaiByIdUserCanCreateHopDong(objHopDong.getIdGiangVien());
			                                 	for(int i = 0; i < listDeTai.size(); i++) {
		                                    		if(objHopDong.getIdDeTai() == listDeTai.get(i).getIdDeTai()) {
	                                 		%>
	                                 		<option selected value="<%= listDeTai.get(i).getIdDeTai() %>"><%=listDeTai.get(i).getTenDeTai()+"___"+ listDeTai.get(i).getMaSoDeTai() %></option>
	                                        <%} else { %>
	                                        	<option value="<%= listDeTai.get(i).getIdDeTai() %>"><%=listDeTai.get(i).getTenDeTai()+"___"+ listDeTai.get(i).getMaSoDeTai() %></option>
	                                       		<%} 
	                                        }%>
	                                        </select>
		                                 </div>
		                                 
		                             </div>
		                         </div>
		                          <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Kinh phí</label>
		                                 <input type="text" name="kinh_phi" class="form-control border-input" value="<%=objHopDong.getKinhPhi()%>">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100 has-feedback">
		                                 <label>Thời gian kí hợp đồng</label>
		                                <div class='input-group date datetime-choose'>
							                <span class="input-group-addon" style="border: 0.5px solid gray;">
							                    <span class="glyphicon glyphicon-calendar"></span>
							                </span>
							                <input type='text' class="form-control border-input" placeholder="yyyy-mm-dd HH:ii:ss" style="background: #F28B65;" name="thoi_gian_ki_HD" value='<%=objHopDong.getThoiGianKyHopDong() != null ? objHopDong.getThoiGianKyHopDong() : "" %>' />
							            </div>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100 has-feedback">
		                                <label>Thời gian bắt đầu</label>
							            <div class='input-group date datetime-choose'>  
							                <span class="input-group-addon" style="border: 0.5px solid gray; height: 40px!important">
							                    <span class="glyphicon glyphicon-calendar"></span>
							                </span>
							                <input type='text' class="form-control border-input" placeholder="yyyy-mm-dd HH:ii:ss" style="background: #F28B65;" name="thoi_gian_bat_dau" value="<%=objHopDong.getThoiGianBatDau() %>"/>
							            </div>
							        </div>
		                         </div>
		                         
		                         <div class="col-md-3">
		                             <div class="form-group height-100 has-feedback">
		                                 <label>Thời gian kết thúc</label>
		                                 <div class='input-group date datetime-choose'>  
							                <span class="input-group-addon" style="border: 0.5px solid gray;">
							                    <span class="glyphicon glyphicon-calendar"></span>
							                </span>
							                <input type='text' class="form-control border-input" placeholder="yyyy-mm-dd HH:ii:ss" style="background: #F28B65;" name="thoi_gian_ket_thuc" value="<%=objHopDong.getThoiGianKetThuc() %>"/>						                
							            </div>
							        </div>
		                         </div>
		                        
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Số điện thoai khách hàng</label>
		                                 <input type="text" name="sdt_KH" class="form-control border-input" value="<%=objHopDong.getDienThoaiKH() %>">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group height-100">
		                                 <label>Trạng thái Hơp đồng</label>
		                                 <select name="trang_thai_HD" class="form-control border-input" value="<%=objHopDong.getTrangThaiHopDong()%>">
		                                    <%if(objHopDong.getTrangThaiHopDong().equals("Đã ký")) {
		                                 		%>
		                                 		<option selected value="Đã ký">Đã ký</option>
		                                    	<option value="Chưa ký">Chưa ký</option>
		                                    
		                                    <%} else { %>
		                                        <option value="Đã ký">Đã ký</option>
		                                    	<option selected value="Chưa ký">Chưa ký</option>
											<%}%>
		                                 </select>
		                             </div>
		                         </div>
		                         </div>

		                         <div class="col-md-12">
		                             <div class="text-center">
		                                 <a href="<%=request.getContextPath() %>/admin/hopdong/index"> 
		                                 	<input type="button" class="btn btn-default" value="Hủy" />
		                                 </a>
		                                 <input type="submit" class="btn btn-info btn-fill btn-wd" value="Thực hiện" />
		                             </div>
		                         </div>
		                         <div class="clearfix"></div>
                            </form>
                            <%} else { %>
                            <div class="text-center text-danger" style="font-size: 20px">Hợp đồng không tồn tại!</div>
                            <%} %>
                        </div>
                    </div>
            	</div>
        	</div>
                          

  <%@include file="/templates/admin/inc/footer.jsp" %>
       