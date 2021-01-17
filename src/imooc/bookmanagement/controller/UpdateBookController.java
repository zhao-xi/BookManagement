package imooc.bookmanagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import imooc.bookmanagement.pojo.Book;
import imooc.bookmanagement.service.BookServiceImpl;

/**
 * Servlet implementation class UpdateBookController
 */
@WebServlet("/dept/update.do")
public class UpdateBookController extends HttpServlet {
	private BookServiceImpl bookService = new BookServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = new Book();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sf = new ServletFileUpload(factory);
		List<FileItem> formData = null;
		try {
			formData = sf.parseRequest(request);
			String isCoverModified = "";
			FileItem file = null;
			for(FileItem fi : formData) {
				if(fi.isFormField()) {
					switch(fi.getFieldName()) {
					case "bookId":
						book.setId(Integer.parseInt(fi.getString("UTF-8"))); break;
					case "bookName":
						book.setName(fi.getString("UTF-8")); break;
					case "category":
						book.setCategory(fi.getString("UTF-8")); break;
					case "bookPrice":
						book.setPrice(Double.parseDouble(fi.getString("UTF-8"))); break;
					case "note":
						book.setNote(fi.getString("UTF-8")); break;
					case "isCoverModified":
						isCoverModified = fi.getString("UTF-8"); break;
					}
				} else {
					file = fi;
				}
			}
			if(isCoverModified.equals("1")) {
				// 改变了封面图像，上传新的图
				String path = request.getServletContext().getRealPath("/upload");
				String fileName = UUID.randomUUID().toString();
				String suffix = file.getName().substring(file.getName().lastIndexOf("."));
				file.write(new File(path, fileName + suffix));
				System.out.println("上传新的图到:" + path);
				book.setCover("/upload/" + fileName + suffix);
			} else {
				// 没有改变封面图像，把旧的cover设置进来
				System.out.println("没有上传新图");
				book.setCover(bookService.getBookById(String.valueOf(book.getId())).getCover());
			}
			bookService.updateBook(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/management?target=book");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
