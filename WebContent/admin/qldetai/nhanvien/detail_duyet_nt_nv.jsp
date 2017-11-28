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
							%>
                            <div class="content">
                                <form action="" method="post">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Tên đề tài</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getTenDeTai() %>">
                                            </div>
                                        </div>
                                     </div>
                                     <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Chủ nhiệm</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getFullName() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Lĩnh vực nghiên cứu</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getTenLinhVucNghienCuu()%>">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Loại hình nghiên cứu</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getTenLoaiHinhNghienCuu() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Cấp đề tài</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getTenCapDeTai()%>">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Đơn vị chủ trì</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getDonViChuTri() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Tính cấp thiết</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getTinhCapThiet() %>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Mục tiêu </label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getMucTieu() %>">
                                            </div>
                                        </div>
                                    		</div>
                                    		<div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Nội dung chính</label>
                                                <textarea rows="6" class="form-control border-input" disabled="" value="<%=objDeTai.getNoiDung() %> "></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Tổng quan</label>
                                                <textarea rows="3" class="form-control border-input" disabled="" value="<%=objDeTai.getTongQuan() %>"></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Sản phẩm dự kiến</label>
                                                <textarea rows="3" class="form-control border-input" disabled="" value="<%=objDeTai.getSanPham() %>"></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Hiệu quả dự kiến</label>
                                                <textarea rows="3" class="form-control border-input" disabled=" " value="<%=objDeTai.getHieuQua() %>"></textarea>
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Kinh phí dự kiến </label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getKinhPhiThucHien() %>">
                                            </div>
                                    	   </div>
                                    	   </div>
                                    	   <div class="row">
                                    <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Thời gian dự kiến </label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="1 năm">
                                            </div>
                                    </div>
                                    </div>
                                    <div class="row">
	                                    <div class="col-md-12">
	                                            <div class="form-group">
	                                                <label>Trạng thái </label>
	                                                <input type="text" name="id" class="form-control border-input" disabled value="<%= LibraryConstant.ConvertTrangThai(objDeTai.getTrangThai()) %>">
	                                            </div>
	                                    </div>
                                    </div>
                                    <div class="row">
	                                    <div class="col-md-12">
	                                            <div class="form-group">
	                                                <label>Điểm </label>
	                                                <input type="text" name="score" class="form-control border-input" value="<% if (objDeTai.getDiem() != 0){ %><%=objDeTai.getDiem() %><%} %>">
	                                            </div>
	                                    </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Nội dung đánh giá</label>
                                                <textarea id="cktext1" name="content" class="form-control border-input"><% if (objDeTai.getKetQuaNghiemThu() != null){ %><%=objDeTai.getKetQuaNghiemThu() %><%} %></textarea>
                                                 <script type="text/javascript">
							               			var editor = CKEDITOR.replace('cktext1');
							               			CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         			</script>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
	                                    <div class="text-center">
	                                    <%
	                                    	if(isNhanVienQLNCKHTruong){
	                                    %>
	                                        <input type="submit" class="btn btn-info btn-fill btn-wd" name="update" value="Cập nhật"/>
	                                    <%
	                                    	}else if(isAdmin){
	                                    %>
	                                    	 <input type="submit" class="btn btn-info btn-fill btn-wd" name="confirm" value="Duyệt"/>
	                                    	 <input type="submit" class="btn btn-info btn-fill btn-wd" name="cancel" value="Không duyệt"/>
	                                    <%	} %>
	                                    </div>
                       				</div>
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
