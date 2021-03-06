<%@page import="library.LibraryConstant"%>
<%@page import="model.bean.DeTai"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">NỘI DUNG ĐỀ TÀI</h4>
                            </div>
                            <%
					    		if(request.getAttribute("objDeTai") != null){
					    		 DeTai objDeTai =(DeTai)request.getAttribute("objDeTai");
					    		 String tenCDTQly = (String) request.getAttribute("tenCapDeTaiQly");
							%>
                            <div class="content">
                                <form action="" method="post">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Tên đề tài</label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getTenDeTai() %>">
                                            </div>
                                        </div>
                                     </div>
                                     <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Chủ nhiệm</label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getFullName() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Lĩnh vực nghiên cứu</label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getTenLinhVucNghienCuu()%>">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Loại hình nghiên cứu</label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getTenLoaiHinhNghienCuu() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Cấp đề tài</label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getTenCapDeTai()%>">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Đơn vị chủ trì</label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getDonViChuTri() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Tính cấp thiết</label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getTinhCapThiet() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Mục tiêu </label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getMucTieu() %>">
                                            </div>
                                        </div>
                                    		</div>
                                    		<div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Nội dung chính</label>
                                                <textarea rows="6" class="form-control border-input" readonly value="<%=objDeTai.getNoiDung() %> "></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Tổng quan</label>
                                                <textarea rows="3" class="form-control border-input" readonly value="<%=objDeTai.getTongQuan() %>"></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Sản phẩm dự kiến</label>
                                                <textarea rows="3" class="form-control border-input" readonly value="<%=objDeTai.getSanPham() %>"></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Hiệu quả dự kiến</label>
                                                <textarea rows="3" class="form-control border-input" readonly value="<%=objDeTai.getHieuQua() %>"></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Kinh phí dự kiến </label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%=objDeTai.getKinhPhiThucHien() %>">
                                            </div>
                                    	   </div>
                                    	   </div>
                                    	   <div class="row">
                                    <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Thời gian dự kiến </label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="1 năm">
                                            </div>
                                    </div>
                                    </div>
                                    <div class="row">
                                    <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Trạng thái </label>
                                                <input type="text" name="id" class="form-control border-input" readonly value="<%= LibraryConstant.ConvertTrangThai(objDeTai.getTrangThai()) %>">
                                            </div>
                                    </div>
                                    </div>
                                    <% if(LibraryConstant.DangChoDuyetCapKhoa.equals(objDeTai.getTrangThai())){ %>
                                    <div class="row">
	                                    <div class="text-center">
	                                    	<input type="submit" class="btn btn-info btn-fill btn-wd" name="submit" value="Duyệt đề xuất" />
	                                        <input type="submit" class="btn btn-info btn-fill btn-wd" name="cancel" value="Huỷ đề xuất" />
	                                        <a href="<%=request.getContextPath()%>/admin/qldangkydetai/danh_gia_dx_khoa?did=<%=objDeTai.getIdDeTai()%>" class="btn btn-info btn-fill btn-wd" >Yêu cầu chỉnh sửa </a>
	                                    </div>
                       				</div>
                       				<%} %>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
					<%} %>

                </div>
            </div>
        </div>


   <%@include file="/templates/admin/inc/footer.jsp" %>
