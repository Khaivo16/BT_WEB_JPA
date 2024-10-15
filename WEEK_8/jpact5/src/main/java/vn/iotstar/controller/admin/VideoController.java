package vn.iotstar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.services.impl.VideoService;
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 10,  // 10 MB (ngưỡng bộ nhớ tạm trước khi ghi vào đĩa)
	    maxFileSize = 1024 * 1024 * 50,        // 50 MB (kích thước tối đa cho mỗi tệp)
	    maxRequestSize = 1024 * 1024 * 50      // 50 MB (kích thước tối đa cho toàn bộ yêu cầu)
	)
@WebServlet(urlPatterns = {"/admin/videos","/admin/video/add","/admin/video/insert","/admin/video/edit","/admin/video/update",
							"/admin/video/delete","/admin/video/search","/admin/video/watch"})
public class VideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IVideoService videoService = new VideoService();
	public ICategoryService cateService = new CategoryService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		String url = req.getRequestURI();
		
		if (url.contains("/admin/videos")) {
			
			HttpSession session = req.getSession(true);
			if (req.getParameter("id") != null) {
				int id = Integer.parseInt(req.getParameter("id"));
				session.setAttribute("CategoryId", cateService.findById(id));
			}
			List<Video> list = videoService.findByCateId(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Video video = videoService.findById(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/add")) {
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		}
		else if (url.contains("/admin/video/delete")) {
			String id = req.getParameter("id");
			try {
				videoService.delete(Integer.parseInt(id));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = req.getSession(false);
			Category cate = (Category) session.getAttribute("CategoryId");
			resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + cate.getCategoryId());
		}
		else if (url.contains("/admin/video/search")) {
			String name = req.getParameter("keyword");
			HttpSession session = req.getSession(false);
			Category cate = (Category) session.getAttribute("CategoryId");
			List<Video> video = videoService.searchByTitleAndCateId(name,cate.getCategoryId());
			req.setAttribute("listvideo", video);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
			
		}
		else if (url.contains("/admin/video/watch")) {
			String id = req.getParameter("id");
			Video video = videoService.findById(Integer.parseInt(id)); 
			req.setAttribute("video", video);

			// Chuyển hướng đến trang JSP để hiển thị video
			req.getRequestDispatcher("/views/admin/video-watch.jsp").forward(req, resp);
		}	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		String url = req.getRequestURI();
		
		if (url.contains("/admin/video/insert")) {
			String title = req.getParameter("title");
			int active = Integer.parseInt(req.getParameter("active"));
			String description = req.getParameter("description");
			Video video = new Video();
			HttpSession session = req.getSession(false);
			Category cate = (Category) session.getAttribute("CategoryId");
			video.setCategory(cate);
			video.setTitle(title);
			video.setActive(active);
			video.setDescription(description);
			String fname = "";
			String fname_video="";
			String uploadPath = "E:\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				String posterurl = req.getParameter("posterURL");
				
				
			    
				if (part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);
				}
				else if (url != null) {
					video.setPoster(posterurl);
				}
				
				
			}
			catch (Exception e){
				e.printStackTrace();
			}
			
			try {
				Part part_video = req.getPart("video"); // Nhận tệp video từ form (input name="video")
			    String videourl = req.getParameter("videoUrl"); // Nhận URL video từ form (nếu có)
			    
			    if (part_video.getSize() > 0) {
			        String filename = Paths.get(part_video.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp
			        int index = filename.lastIndexOf(".");
			        String ext = filename.substring(index + 1); // Lấy phần mở rộng của tệp (vd: mp4)
			        fname_video = System.currentTimeMillis() + "." + ext; // Tạo tên tệp mới duy nhất bằng timestamp
			        part_video.write(uploadPath + "/" + fname_video); // Lưu tệp vào thư mục upload

			        video.setVideo_file(fname_video); // Lưu tên tệp vào thuộc tính poster (hoặc video URL) của đối tượng video
			    }
			    // Kiểm tra nếu người dùng nhập URL video thay vì tải lên tệp
			    else if (videourl != null ) {
			        video.setVideo_file(videourl); // Sử dụng URL video người dùng đã nhập
			    }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + cate.getCategoryId());
		}
		else if(url.contains("/admin/video/update")) {
			int videoid = Integer.parseInt(req.getParameter("videoid"));
			String title = req.getParameter("title");
			int active = Integer.parseInt(req.getParameter("active"));
			String description = req.getParameter("description");
			Video video = new Video();
			HttpSession session = req.getSession(false);
			Category cate = (Category) session.getAttribute("CategoryId");
			video.setCategory(cate);
			video.setVideoId(videoid);
			video.setTitle(title);
			video.setActive(active);
			video.setDescription(description);
			Video videold = videoService.findById(videoid);
			String fileold = videold.getPoster();
			String filevd_old = videold.getVideo_file();
			String fname = "";
			String fname_video="";
			String uploadPath = "E:\\upload";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				String posterurl = req.getParameter("posterURL");
				if (part.getSize()>0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + fname);
					video.setPoster(fname);
				}
				else if (url != null) {
					video.setPoster(posterurl);
				}
				else  {
					video.setPoster(fileold);
				}
			}
			catch (Exception e){
				e.printStackTrace();
			}
			try {
				Part part_video = req.getPart("video"); // Nhận tệp video từ form (input name="video")
			    String videourl = req.getParameter("videoUrl"); // Nhận URL video từ form (nếu có)
			    
			    if (part_video.getSize() > 0) {
			        String filename = Paths.get(part_video.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp
			        int index = filename.lastIndexOf(".");
			        String ext = filename.substring(index + 1); // Lấy phần mở rộng của tệp (vd: mp4)
			        fname_video = System.currentTimeMillis() + "." + ext; // Tạo tên tệp mới duy nhất bằng timestamp
			        part_video.write(uploadPath + "/" + fname_video); // Lưu tệp vào thư mục upload

			        video.setVideo_file(fname_video); // Lưu tên tệp vào thuộc tính poster (hoặc video URL) của đối tượng video
			    }
			    // Kiểm tra nếu người dùng nhập URL video thay vì tải lên tệp
			    else if (videourl != null ) {
			        video.setVideo_file(videourl); // Sử dụng URL video người dùng đã nhập
			    }
			    else {
			    	video.setVideo_file(filevd_old);
			    }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos?id=" + cate.getCategoryId());
		}
	}
	
}
