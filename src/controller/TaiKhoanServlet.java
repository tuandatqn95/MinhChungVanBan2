package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.TaiKhoanDAO;

@WebServlet("/admin/TaiKhoanServlet")
public class TaiKhoanServlet extends HttpServlet {

	TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
	private static final long serialVersionUID = 1L;

	public TaiKhoanServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String url = "taikhoan.jsp";
		if (request.getParameter("command") != null && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			switch (request.getParameter("command")) {
			case "delete":
				try {
					taiKhoanDAO.XoaTaiKhoan(id);
					response.sendRedirect(url);
				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}
				break;
			case "edit":
				request.getRequestDispatcher("suataikhoan.jsp").forward(request, response);
				break;
			}
		} else {
			errors.add("Có lỗi xảy ra!");
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();

		String email = "";
		String matKhau = "";
		String reMatKhau = "";
		String hoTen = "";
		Integer idLoaiTaiKhoan = 1;
		String gioiTinh = "";
		String ngaySinh = "";
		String diaChi = "";
		String noiCongTac = "";
		String chucVu = "";
		String soDienThoai = "";
		String avatar = "";
		FileItem avatarFile = null;
		String idTaiKhoan = request.getParameter("id");
		String func = "";
		String page = "";

		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					switch (item.getFieldName()) {
					case "Email":
						email = item.getString("UTF-8");
						break;
					case "MatKhau":
						matKhau = item.getString("UTF-8");
						break;
					case "Re-MatKhau":
						reMatKhau = item.getString("UTF-8");
						break;
					case "HoTen":
						hoTen = item.getString("UTF-8");
						break;
					case "IDLoaiTaiKhoan":
						idLoaiTaiKhoan = Integer.valueOf(item.getString());
						break;
					case "GioiTinh":
						gioiTinh = item.getString();
						break;
					case "NgaySinh":
						ngaySinh = item.getString();
						break;
					case "DiaChi":
						diaChi = item.getString("UTF-8");
						break;
					case "NoiCongTac":
						noiCongTac = item.getString("UTF-8");
						break;
					case "ChucVu":
						chucVu = item.getString("UTF-8");
						break;
					case "SoDienThoai":
						soDienThoai = item.getString();
						break;
					case "Func":
						func = item.getString();
						break;
					case "Page":
						page = item.getString();
						break;
					case "IDTaiKhoan":
						idTaiKhoan = item.getString();
						break;
					}
				} else {
					String nameimg = item.getName();
					if (!nameimg.equals("")) {
						avatarFile = item;
						avatar = item.getName();
					}
				}

			}

		} catch (FileUploadException e) {
			errors.add("Có lỗi xảy ra!");
			e.printStackTrace();
		}

		if (func != "") {
			if (email.isEmpty() || matKhau.isEmpty() || reMatKhau.isEmpty() || hoTen.isEmpty()
					|| gioiTinh.isEmpty() || ngaySinh.isEmpty() || diaChi.isEmpty()
					|| noiCongTac.isEmpty() || chucVu.isEmpty() || soDienThoai.isEmpty()) {
				errors.add("Vui lòng nhập đủ thông tin!");
			} else {
				if (!matKhau.equals(reMatKhau)) {
					errors.add("Mật khẩu không trùng khớp");
				} else {
					try {
						if (avatarFile != null) {
							String nameimg = avatarFile.getName();
							String dirUrl = request.getServletContext().getRealPath("") + File.separator + "uploads";
							File dir = new File(dirUrl);
							if (!dir.exists()) {
								dir.mkdir();
								
							}
							String fileImg = dirUrl + File.separator + nameimg;
							File file = new File(fileImg);
							try {
								avatarFile.write(file);
							} catch (Exception e) {
								errors.add("Có lỗi trong quá trình upload!");
								e.printStackTrace();
							}
						}
						if (errors.size() == 0) {
							switch (func) {
							case "add":
								taiKhoanDAO.ThemTaiKhoan(email, matKhau, hoTen, idLoaiTaiKhoan,
										gioiTinh.equals("Nu"), ngaySinh, diaChi, noiCongTac, chucVu, soDienThoai,
										avatar);
								break;
							case "edit":

								taiKhoanDAO.SuaTaiKhoan(Integer.parseInt(idTaiKhoan), email, hoTen,
										idLoaiTaiKhoan, gioiTinh.equals("Nu"), ngaySinh, diaChi,
										noiCongTac, chucVu, soDienThoai, avatar);
								break;
							}
						}

					} catch (NumberFormatException | SQLException e) {
						errors.add("Có lỗi xảy ra!");
						e.printStackTrace();
					}
				}
			}

		} else {
			errors.add("Có lỗi xảy ra!");
		}
		
		String urlRedirect = null, urlFeedback = null;
		switch (page) {
		case "capnhattaikhoan":
			urlRedirect = "\\MinhChungVanban\\profile.jsp";
			urlFeedback = "capnhattaikhoan.jsp";
			break;
		case "":
			urlRedirect = "taikhoan.jsp";
			switch (func) {
			case "add":
				urlFeedback = "themtaikhoan.jsp";
				break;
			case "edit":
				urlFeedback = "suataikhoan.jsp?id=" + idTaiKhoan;
				break;
			}
			break;
		}
		
		if (errors.size() == 0) {
			response.sendRedirect(urlRedirect);
		} else {
			request.setAttribute("errors", errors);
			if(urlFeedback == "capnhattaikhoan.jsp")
				response.sendRedirect("/MinhChungVanban/capnhattaikhoan.jsp");
			else
				request.getRequestDispatcher(urlFeedback).forward(request, response);
		}

	}

}
