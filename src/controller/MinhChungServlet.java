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

import dao.MinhChungDAO;
import dao.TapTinDAO;

@WebServlet("/admin/MinhChungServlet")
public class MinhChungServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MinhChungDAO minhChungDAO = new MinhChungDAO();
	TapTinDAO tapTinDAO = new TapTinDAO();

	public MinhChungServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String url = "minhchung.jsp";
		if (request.getParameter("command") != null && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			switch (request.getParameter("command")) {
			case "delete":
				try {
					minhChungDAO.XoaMinhChung(id);
					response.sendRedirect(url);
				} catch (SQLException e) {
					errors.add("Đã có lỗi xảy ra!");
					e.printStackTrace();
				}
				break;
			case "edit":
				request.getRequestDispatcher("suaminhchung.jsp").forward(request, response);
				break;
			}
		} else {
			errors.add("Đã có lỗi xảy ra!");
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
		String url = "themminhchung.jsp";

		String maMinhChung = "";
		String tenMinhChung = "";
		String moTa = "";
		String soHieu = "";
		String idTieuChi = "";
		String idNoiBanHanh = "";
		String ngayBanHanh = "";
		String func = "";
		String idMinhChung = "";
		ArrayList<FileItem> listFile = new ArrayList<FileItem>();

		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem item : fileItems) {
				if (item.isFormField()) {
					switch (item.getFieldName()) {
					case "MaMinhChung":
						maMinhChung = item.getString();
						break;
					case "TenMinhChung":
						tenMinhChung = item.getString("UTF-8");
						break;
					case "MoTa":
						moTa = item.getString("UTF-8");
						break;
					case "SoHieu":
						soHieu = item.getString("UTF-8");
						break;
					case "IDTieuChi":
						idTieuChi = item.getString();
						break;
					case "IDNoiBanHanh":
						idNoiBanHanh = item.getString();
						break;
					case "NgayBanHanh":
						ngayBanHanh = item.getString();
						break;
					case "Func":
						func = item.getString();
						break;
					case "IDMinhChung":
						idMinhChung = item.getString();
						break;
					}
				} else {
					String nameimg = item.getName();
					if (!nameimg.equals("")) {
						listFile.add(item);
					}
				}

			}

		} catch (FileUploadException e) {
			errors.add("Đã có lỗi xảy ra!");
			e.printStackTrace();
		}

		if (!func.isEmpty()) {
			if (maMinhChung.isEmpty() || tenMinhChung.isEmpty() || soHieu.isEmpty() || idTieuChi.isEmpty()
					|| idNoiBanHanh.isEmpty() || ngayBanHanh.isEmpty()) {
				errors.add("Vui lòng điền tất cả các trường bắt buộc!");
			} else {
				try {
					if (errors.size() == 0) {
						switch (func) {
						case "add":
							url = "themminhchung.jsp";
							minhChungDAO.ThemMinhChung(Integer.parseInt(idTieuChi), maMinhChung, tenMinhChung, moTa,
									soHieu, ngayBanHanh, Integer.parseInt(idNoiBanHanh));
							break;
						case "edit":
							minhChungDAO.SuaMinhChung(Integer.parseInt(idMinhChung), Integer.parseInt(idTieuChi),
									maMinhChung, tenMinhChung, moTa, soHieu, ngayBanHanh,
									Integer.parseInt(idNoiBanHanh));
							break;
						}
						int ID = minhChungDAO.GetIDMinhChungByMaMinhChung(maMinhChung);
						if (listFile.size() > 0 && ID > 0) {
							for (FileItem item : listFile) {
								String nameimg = item.getName();
								String dirUrl = request.getServletContext().getRealPath("") + File.separator
										+ "documents";
								File dir = new File(dirUrl);
								if (!dir.exists()) {
									dir.mkdir();

								}
								String fileImg = dirUrl + File.separator + nameimg;
								File file = new File(fileImg);
								try {
									item.write(file);
									tapTinDAO.ThemTapTin(ID, nameimg, nameimg.substring(nameimg.lastIndexOf(".") + 1));
								} catch (Exception e) {
									errors.add("Có lỗi trong quá trình upload!");
									e.printStackTrace();
								}
							}
						}

					}

				} catch (NumberFormatException | SQLException e) {
					errors.add("Đã có lỗi xảy ra!");
					e.printStackTrace();
				}

			}

		} else {
			errors.add("Đã có lỗi xảy ra!");
		}

		if (errors.size() == 0) {
			response.sendRedirect("minhchung.jsp");
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
