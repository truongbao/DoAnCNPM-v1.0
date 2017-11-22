<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="/">Dự án được phát triển bởi: Nhóm SE_03</a>
                        </li>
                    </ul>
                </nav>
                <div class="copyright pull-right">
                    &copy; <script>document.write(new Date().getFullYear())</script>, made with <i class="fa fa-heart heart"></i> by <a href="http://vinaenter.edu.vn">Nhóm SE_03</a>
                </div>
            </div>
        </footer>


    </div>
</div>


</body>

    
	<script src="<%=request.getContextPath() %>/templates/admin/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="<%=request.getContextPath() %>/templates/admin/js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="<%=request.getContextPath() %>/templates/admin/js/demo.js"></script>
	<script src="<%=request.getContextPath() %>/templates/admin/bootstrap/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script type="text/javascript">
            // Bootstrap DateTimePicker v4
            $('#datetimepicker').datetimepicker({
                pickTime: false,
                minView: 2,
                format: 'dd/mm/yyyy',
                autoclose: true,
            });
            $('.date-picker').datetimepicker({
                pickTime: false,
                minView: 2,
                format: 'dd/mm/yyyy',
                autoclose: true,
            });
    </script>

   

</html>
