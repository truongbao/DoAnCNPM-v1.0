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
                                <h4 class="title">ĐÁNH GIÁ ĐỀ XUẤT</h4>
                            </div>
                            <div class="content">
                            <%
					    		if(request.getAttribute("objDeTai") != null){
					    		 DeTai objDeTai =(DeTai)request.getAttribute("objDeTai");
							%>
                                <form action="<%=request.getContextPath() %>/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?did=<%=objDeTai.getIdDeTai() %>" method="post">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Tên đề tài</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getTenDeTai()%>">
                                            </div>
                                        </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Chủ nhiệm</label>
                                                <input type="text" name="id" class="form-control border-input" disabled value="<%=objDeTai.getFullName()%>"">
                                            </div>
                                        </div>
                                        
                                    </div>
                                   <%} %>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Nội dung đánh giá</label>
                                                <textarea id="cktext1" class="form-control border-input" name = "noidung"></textarea>
                                                 <script type="text/javascript">
							               			var editor = CKEDITOR.replace('cktext1');
							               			CKFinder.setupCKEditor(editor,'<%=request.getContextPath()%>/ckfinder/')
							         			</script>
                                            </div>
                                        </div>
                                    </div>
							<div class="row">
								<div class="text-center">
									<input type="submit" name="huy"
										class="btn btn-info btn-fill btn-wd" value="Huỷ đề xuất" />
									<input type="submit" name="duyet"
										class="btn btn-info btn-fill btn-wd" value="Duyệt đề xuất" />
									<input type="submit" name="chinhsua"
										class="btn btn-info btn-fill btn-wd" value="Đề nghị chỉnh sửa đề xuất" />
								</div>
								</div>
								<div class="clearfix"></div>

						</form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>


      <%@include file="/templates/admin/inc/footer.jsp" %>
