<%@page import="model.bean.DeTai"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   <script type="text/javascript">
	   $(document).ready(function(){
			$("#formDanhGia").validate({
				rules:{
					"noidung":{
						required: true,
					},
				},
				messages:{
					"noidung":{
						required: "<p> <span style='color:red' > Vui lòng nhập nội dung đánh giá</span> </p>",
					},
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
                                <h4 class="title">ĐÁNH GIÁ ĐỀ XUẤT</h4>
                            </div>
                            <div class="content">
                            <%
					    		if(request.getAttribute("objDeTai") != null){
					    		 DeTai objDeTai =(DeTai)request.getAttribute("objDeTai");
							%>
                                <form action="" method="post" id="formDanhGia">
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
                                                <textarea id="cktext1" name="noidung" class="form-control border-input ckeditor"></textarea>
                                                 
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="text-center">
                                        <input type="submit" class="btn btn-info btn-fill btn-wd" value="Gửi đánh giá cho chủ nhiệm đề tài" />
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
