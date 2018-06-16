<%@page import="model.ThongBao"%>
<%@page import="dao.ThongBaoDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>




<%
	ThongBaoDAO thongbao_dao = new ThongBaoDAO();
	ArrayList<String> errors = new ArrayList<String>();
	ThongBao thongbao = null;
	try {
		thongbao = thongbao_dao.GetThongBaoByID(Integer.valueOf(request.getParameter("id")));
		if (thongbao == null) {
			errors.add("Không tìm thấy trang bạn yêu cầu!");
		}
	} catch (NumberFormatException e) {

		errors.add("Không tìm thấy trang bạn yêu cầu!");

	}
	

	if (errors.size() > 0) {
		request.setAttribute("errors", errors);
		request.getRequestDispatcher("403.jsp").forward(request, response);
	}
%>

<div class="panel panel-primary">

	<div class="panel-heading">
		<h3 class="panel-title">Chi tiết Thông báo</h3>
	</div>
	<div class="panel-body">
		<%
			if (errors.size() == 0) {
		%>
		<p class="media-heading" style="font-size: 17px">
			<strong><%=thongbao.getTieuDe()%></strong>
		</p>
		<i>Đăng tải ngày <%=new SimpleDateFormat("dd-MM-yyyy").format(thongbao.getThoiGianDang())%></i>
		<br> <br>
		<p style="text-indent: 20px; font-size: 16px"><%=thongbao.getNoiDung()%></p>
		<%
			}
			;
		%>

	</div>
</div>



<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>

