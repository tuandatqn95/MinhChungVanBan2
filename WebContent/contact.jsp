<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>

<%
	ArrayList<TaiKhoan> listContacts = taiKhoanDAO.getListTaiKhoanAreContact();
%>

<div class="panel panel-primary">

	<div class="panel-heading">
		<h3 class="panel-title">Thông tin liên hệ</h3>
	</div>
	<%
		for (TaiKhoan contact : listContacts) {
	%>
	<div class="panel-body">
		<div>
			<div class="col-xs-3">
				<img
					src="<%=contact.getAnhDaiDien() == null ? "images/default-avatar.png"
						: "uploads/" + contact.getAnhDaiDien()%>"
					class="img-thumbnail">
			</div>
			<div class="col-xs-9">
				<h4>
					<b><%=contact.getHoTen()%></b>
				</h4>
				<p>
					Nơi công tác:
					<%=contact.getNoiCongTac()%></p>
				<p>
					Chức vụ:
					<%=contact.getChucVu()%></p>
				<p>
					Số điện thoại:
					<%=contact.getSoDienThoai()%></p>
				<p>
					Email:
					<%=contact.getEmail()%></p>
			</div>
		</div>
	</div>
	<%
		}
	%>

</div>



<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>
