<%@page import="java.util.Calendar"%>
<%@page import="model.bean.User"%>
<%@page import="model.bean.LoaiTaiKhoan"%>
<%@page import="model.bean.Khoa"%>
<%@page import="model.bean.HocVi"%>
<%@page import="model.dao.UserDAO"%>

<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

   <%@include file="/templates/admin/inc/header.jsp" %>
   

        <div class="content">
            <div class="container-fluid">
                <div class="row">
                 <!-- Small boxes (Stat box) -->
		      <div class="row">
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div style="background: #dddddd;" class="box-dash">
		            <div class="inner">
		              <h3><%=request.getParameter("totalUser") %></h3>
		
		              <p>User</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-email-unread"></i>
		            </div>
		            <a href="<%=request.getContextPath() %>/admin/user/index" class="btn btn-info">Chi tiết <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div style="background: #311C98; color: white;" class="box-dash">
		            <div class="inner">
		              <div class="inner">
		              <h3><%=request.getParameter("totalCat") %></h3>
		
		              <p>Danh mục</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-email-unread"></i>
		            </div>
		            <a href="<%=request.getContextPath() %>/admin/cat/index" class="btn btn-info">Chi tiết <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div style="background: #dddddd;" class="box-dash">
		           <div class="inner">
		              <h3><%=request.getParameter("totalDeTai") %></h3>
		
		              <p>Đề tài</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-email-unread"></i>
		            </div>
		            <a href="<%=request.getContextPath() %>/admin/detai/index" class="btn btn-info">Chi tiết <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div style="background: #311C98; color: white;" class="box-dash">
		            <div class="inner">
		              <h3><%=request.getParameter("totalDangKiDeTai") %></h3>
		
		              <p>Đăng kí đề tài</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-email-unread"></i>
		            </div>
		            <a href="<%=request.getContextPath() %>/admin/dang-ki-detai/index" class="btn btn-info">Chi tiết <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		        </div>
		        <div class="row mt-20">
		         <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div style="background: #311C98; color: white;" class="box-dash">
		            <div class="inner">
		              <h3><%=request.getParameter("totalHopDong") %></h3>
		
		              <p>Hợp đồng</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-email-unread"></i>
		            </div>
		            <a href="<%=request.getContextPath() %>/admin/hopdong/index" class="btn btn-info">Chi tiết <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		          <!-- ./col -->
		        <div class="col-lg-3 col-xs-6">
		          <!-- small box -->
		          <div style="background: #dddddd ;" class="box-dash">
		            <div class="inner">
		              <h3><%=request.getParameter("totalThongBao") %></h3>
		
		              <p>Thông báo</p>
		            </div>
		            <div class="icon">
		              <i class="ion ion-email-unread"></i>
		            </div>
		            <a href="<%=request.getContextPath() %>/admin/thongbao/index" class="btn btn-info">Chi tiết <i class="fa fa-arrow-circle-right"></i></a>
		          </div>
		        </div>
		      </div>
		 <div style="height: 100px"></div>
 
  <%@include file="/templates/admin/inc/footer.jsp" %>
       