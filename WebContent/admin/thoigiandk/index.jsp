<%@page import="java.util.Calendar"%>
<%@page import="model.bean.ThoiGianDK"%>
<%@page import="model.dao.ThoiGianDKDAO"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   
   <script type="text/javascript">
			     
			</script>

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
	                            <h4 class="title text-center" style="margin-bottom: 50px;">Quản lý thời gian đăng ký đề tài và thuyết minh</h4>
                                                               
                               <div class="text-center text-danger col-md-12" style="font-size: 18px;font-weight: bold;">
                            	 <%
							      if(request.getParameter("msg")!=null){
							          int msg = Integer.parseInt( request.getParameter("msg") );
							          switch(msg){
							            case 1: out.print("Xử lý thành công !!");break;
							            case 0: out.print("Không thành công vui lòng thử lại !!");break;
							            case 3: out.print("Tên cấp đề tài đã tồn tại !!");break;
							            case 4: out.print("Cấp đề tài không tồn tại !!");break;
								        }
							      }
		     			       	%>  
                            	</div>
                            	<div class="row">
                            		<div class="col-md-6 text-center">
                            			<button data-action="<%=request.getContextPath() %>/admin/thoigiandk/edit?uid=1" class="change-time-action btn btn-info">Thay đổi thời gian đăng ký</button>
                            		</div>
                            		
                            		<div class="col-md-6 text-center">
                            			<button data-action="<%=request.getContextPath() %>/admin/thoigiandk/edit?uid=2" class="change-time-action btn btn-info">Thay đổi thời gian thuyết minh</button>
                            		</div>
                            	</div>
                            	<form class="row" method="post" action="" id="change-time" hidden>
                            		<div class="text-center col-md-8 col-md-offset-2" style="margin-top: 50px;">
                            			Thời gian bắt đầu: <input type="text" class="form-control" style="border: 1px solid black;" />
                            		</div>
                            		<div class="text-center col-md-8 col-md-offset-2" style="margin-top: 50px;">
                            			Thời gian kết thúc: <input type="text" class="form-control" style="border: 1px solid black;" />
                            		</div>
                            		
                            		<div class="text-center col-md-12">
                            			<button type="submit" class="btn btn-success">Lưu</button>
                            		</div>
                            	</form>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>
                          

   <%@include file="/templates/admin/inc/modal.jsp" %>
  <%@include file="/templates/admin/inc/footer.jsp" %>
       