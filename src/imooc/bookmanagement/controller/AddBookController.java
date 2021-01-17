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
import imooc.bookmanagement.service.CategoryServiceImpl;

/**
 * Servlet implementation class AddBookController
 */
@WebServlet("/addBook")
public class AddBookController extends HttpServlet {
	private BookServiceImpl bookService = new BookServiceImpl();
	private CategoryServiceImpl categoryService = new CategoryServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// form以二进制格式上传
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sf = new ServletFileUpload(factory);
		try {
			List<FileItem> formData = sf.parseRequest(request);
			Book book = new Book();
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
					}
				} else {
					String path = request.getServletContext().getRealPath("/upload");
					String fileName = UUID.randomUUID().toString();
					String suffix = fi.getName().substring(fi.getName().lastIndexOf("."));
					fi.write(new File(path, fileName + suffix));
					book.setCover("/upload/" + fileName + suffix);
				}
			}
			bookService.addBook(book);
			response.sendRedirect("/management?target=book");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
