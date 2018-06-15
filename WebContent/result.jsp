<%@page import="model.TieuChi"%>
<%@page import="model.TieuChuan"%>
<%@page import="dao.TieuChiDAO"%>
<%@page import="dao.TieuChuanDAO"%>
<%@page import="model.MinhChung"%>
<%@page import="dao.MinhChungDAO"%>
<%@page import="model.TapTin"%>
<%@page import="dao.TapTinDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="header.jsp"%>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">Danh sách minh chứng</h3>
	</div>
	<div class="panel-body">
		<%
					MinhChungDAO minhChungDAO = new MinhChungDAO();
					List<MinhChung> listminhchung = null;
					switch (request.getParameter("findBy")) {
						case "name":
							listminhchung = minhChungDAO.getListMinhChungByName(request.getParameter("keyword"));
							break;
						case "tieuchi":
							listminhchung = minhChungDAO.getListMinhChungByMaTieuChi(Integer.valueOf(request.getParameter("maTieuChi")));
							break;
					}
					if(listminhchung.size() > 0) {
	%>
		<table width="100%"
			class="table table-striped table-bordered table-hover"
			id="dataTables-example">
			<thead>
				<tr>
					<th>Mã minh chứng</th>
					<th>Tên minh chứng</th>
					<th></th>

				</tr>
			</thead>
			<tbody>
				<%for (MinhChung minhChung : listminhchung) {%>
				<tr class="odd gradeX">
					<td><%=minhChung.getMaMinhChung()%></td>
					<td><%=minhChung.getTenMinhChung()%></td>
					<td>
						<%
							TapTinDAO tapTinDAO = new TapTinDAO();
							for (TapTin tt : tapTinDAO.GetListTapTinByMinhChung(minhChung.getID())) {
						%> <a href="../MinhChungVanban/documents/<%=tt.getFilePath()%>"><i>Xem</i></a>
						<%
					}
				}%>
					</td>
				</tr>
			</tbody>
		</table>
		<%} else {%>
		<div class="alert alert-info">Không có minh chứng nào.</div>
		<%}%>
	</div>
</div>


<%@ include file="sidebar.jsp"%>
<%@ include file="footer.jsp"%>