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
								"thoi_gian_ket_thuc":{
									required: true
								},
								"emai_KH":{
									 required:true,
									  email:true
									
								},
								"kinh_phi":{
									required: true
								}
							},
							messages:{
								"ten_KH":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào tên khách hàng</span> </p>"
									
								},
								"de_tai":{
									required: "<p> <span style='color:red' > Vui lòng chọn đề tài </span> </p>"
								},
								"thoi_gian_bat_dau":{
									required: "<p> <span style='color:red' > Vui lòng nhập vào thời gian bắt đầu</span> </p>"
								},
								"email_KH":{
								    required:"<p style='color:red'> Vui lòng nhập email </p>",	
			                        email: "<p style='color:red'> Vui lòng nhập đúng định dạng email : ai_do@example.com </p> "	
								},
								"thoi_gian_ket_thuc":{
								    required:"<p style='color:red'> Vui lòng nhập thời gian kết thúc </p>",	
			                     },
								"kinh_phi":{
								    required:"<p style='color:red'> Vui lòng nhập kinh phí </p>",	
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
                            <h4 class="title" style="text-align: center; color : white ;border: 1px solid;padding: 4px 4px;border-radius: 4px;  background: #58a808; color: #FFF; font-weight: bold">Thêm Hợp Đồng</h4>
                        </div>
                              
                        <div class="content">
                        	<%
                        		UserDAO userDao = new UserDAO();
                        		DetaiDAO detaiDao = new DetaiDAO();
                           	%>
                            <form id="frmHopDong" action="<%=request.getContextPath() %>/admin/hopdong/add" method="post">
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Tên khách hàng</label>
		                                 <input type="text" name="ten_KH" class="form-control border-input" placeholder="Họ tên">
		                             </div>
		                         </div>
		                         <div class="col-md-2">
		                             <div class="form-group">
		                                 <div class="form-group">
		                                 <label>Chức vụ khách hàng</label>
		                                 <input type='text' name="chuc_vu_KH"  class="form-control border-input"/>
		                                 </div>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Địa chỉ khách hàng</label>
		                                 <input type="text" name="dia_chi_KH" class="form-control border-input" style="height: 40px!important">
		                             </div>
		                         </div>
		                         <div class="col-md-4">
		                             <div class="form-group">
		                                 <label>Email khách hàng</label>
		                                 <input type="text" name="emai_KH" class="form-control border-input" style="height: 40px!important">
		                             </div>
		                         </div>
		                             <div class="col-md-3">
		                                 <div class="form-group">
		                                     <label for="read">Giảng viên</label>
		                                     <select name="giang_vien" class="form-control border-input" id="giangvien-hopdong">
		                                     
		                                     <option class="text-center" value="0">---</option>
		                                     <%
		                                    	ArrayList<User > listGiangVien = userDao.getListGiangVienDetai();
		                                    	for(int i = 0; i < listGiangVien.size(); i++) {
	                                    	%>
                                               	<option value="<%= listGiangVien.get(i).getIdUser() %>"><%=listGiangVien.get(i).getFullName() + "___" + listGiangVien.get(i).getUserName() %></option>
                                               	<%} %>
		                                 	</select>
		                                 </div>
		                             </div>
		                             <div class="col-md-3">
		                                 <div class="form-group">
		                                     <label for="read">Người đại diện</label>
		                                     <select name="nguoi_dai_dien" class="form-control border-input">
                                               	
		                                     <%
		                                    	ArrayList<User > listUser = userDao.getItems();
		                                    	for(int i = 0; i < listUser.size(); i++) {
	                                    	%>
                                               	<option value="<%= listUser.get(i).getIdUser() %>"><%=(listUser.get(i).getFullName() + "___" + listUser.get(i).getUserName()) %></option>
                                               	<%} %>
		                                 	</select>
		                             </div>
		                         </div>
		                          <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Đề tài</label>
		                                 <div id="list-detai-follow-giangvien"></div>
		                                 
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Thời gian kí hợp đồng</label>
		                                 <input type="text" name="thoi_gian_ki_HD" class="form-control border-input" id="datetimepicker">
		                                 <span class="glyphicon glyphicon-calendar form-control-feedback" style="font-size: 20px;"></span>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Thời gian bắt đầu</label>
		                                 <input type="text" name="thoi_gian_bat_dau" class="form-control border-input" id="datetimepicker">
		                                 <span class="glyphicon glyphicon-calendar form-control-feedback" style="font-size: 20px;"></span>
		                             </div>
		                         </div>
		                         
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Thời gian kết thúc</label>
		                                 <input type="text" name="thoi_gian_ket_thuc" class="form-control border-input" id="datetimepicker">
		                                 <span class="glyphicon glyphicon-calendar form-control-feedback" style="font-size: 20px;"></span>
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Kinh phí</label>
		                                 <input type="text" name="kinh_phi" class="form-control border-input">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Số điện thoai khách hàng</label>
		                                 <input type="text" name="sdt_KH" class="form-control border-input">
		                             </div>
		                         </div>
		                         <div class="col-md-3">
		                             <div class="form-group">
		                                 <label>Trạng thái Hơp đồng</label>
		                                 <select name="trang_thai_HD" class="form-control border-input">
		                                    <option value="Đã ký">Đã ký</option>
		                                    <option value="Chưa ký">Chưa ký</option>
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
                        </div>
                    </div>
            	</div>
        	</div>
                          

  <%@include file="/templates/admin/inc/footer.jsp" %>
       