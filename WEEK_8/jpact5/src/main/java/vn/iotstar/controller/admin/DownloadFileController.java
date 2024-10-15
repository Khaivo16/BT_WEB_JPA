package vn.iotstar.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/image","/video" })
public class DownloadFileController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DIR ="E:\\upload";
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("/image")) {
		String fileName = req.getParameter("fname");
		File file = new File(DIR + "/" + fileName);
		resp.setContentType("image/jpeg");
		if (file.exists()) {
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
			}
		}
		else if (url.contains("/video")) {
			String fileName = req.getParameter("fname");
			File file = new File(DIR + "/" + fileName);
			resp.setContentType("video/mp4");
			if (file.exists()) {
				IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
				}
		}
	}

}
