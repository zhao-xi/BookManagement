package imooc.bookmanagement.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import imooc.bookmanagement.pojo.*;

public class XmlDataSource {
	private static List<User> userData = new ArrayList<>();
	private static List<Category> categoryData = new ArrayList<>();
	private static List<Book> bookData = new ArrayList<>();

	private static String userDataFile;
	private static String categoryDataFile;
	private static String bookDataFile;
	static {
		userDataFile = XmlDataSource.class.getResource("/User.xml").getPath();
		categoryDataFile = XmlDataSource.class.getResource("/Category.xml").getPath();
		bookDataFile = XmlDataSource.class.getResource("/Book.xml").getPath();
		reloadUser();
		reloadCategory();
		reloadBook();
	}
	private static void reloadUser() {
		URLDecoder decoder = new URLDecoder();
		try {
			/**
			 * 取得userData
			 */
			userDataFile = decoder.decode(userDataFile, "UTF-8");
			SAXReader reader = new SAXReader();
			Document document = reader.read(userDataFile);
			List<Node> userNodes = document.selectNodes("/root/user");
			userData.clear();
			for(Node node : userNodes) {
				Element element = (Element) node;
				String username = element.elementText("username");
				String password = element.elementText("password");
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				userData.add(user);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void reloadCategory() {
		URLDecoder decoder = new URLDecoder();
		try {
			/**
			 * 取得categoryData
			 */
			SAXReader reader = new SAXReader();
			categoryDataFile = decoder.decode(categoryDataFile, "UTF-8");
			Document document = reader.read(categoryDataFile);
			List<Node> categoryNodes = document.selectNodes("/root/category");
			categoryData.clear();
			for(Node node : categoryNodes) {
				Element element = (Element) node;
				String id = element.attributeValue("id");
				String name = element.elementText("name");
				Category category = new Category();
				category.setId(Integer.parseInt(id));
				category.setName(name);
				categoryData.add(category);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void reloadBook() {
		URLDecoder decoder = new URLDecoder();
		try {
			/**
			 * 取得bookData
			 */
			bookDataFile = decoder.decode(bookDataFile, "UTF-8");
			SAXReader reader = new SAXReader();
			Document document = reader.read(bookDataFile);
			List<Node> bookNodes = document.selectNodes("/root/book");
			bookData.clear();
			for(Node node : bookNodes) {
				Element element = (Element) node;
				String id = element.attributeValue("id");
				String name = element.elementText("name");
				String category = element.elementText("category");
				String price = element.elementText("price");
				String cover = element.elementText("cover");
				String note = element.elementText("note");
				Book book = new Book();
				book.setId(Integer.parseInt(id));
				book.setName(name);
				book.setCategory(category);
				book.setPrice(Double.parseDouble(price));
				book.setCover(cover);
				book.setNote(note);
				bookData.add(book);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 获取用户数据
	public static List<User> getRawUserData() {
		return userData;
	}
	// 获取分类数据
	public static List<Category> getRawCategoryData() {
		return categoryData;
	}
	// 获取图书数据
	public static List<Book> getRawBookData() {
		return bookData;
	}
	
	// 删除分类数据
	public static void deleteCategory(String id) {
		SAXReader reader = new SAXReader();
		Writer writer = null;
		try {
			Document document = reader.read(categoryDataFile);
			List<Node> nodes = document.selectNodes("/root/category[@id=" + id + "]");
			if(nodes.size() == 0) throw new RuntimeException("id=" + id + "编号分类不存在");
			Element p = (Element) nodes.get(0);
			p.getParent().remove(p);
			writer = new OutputStreamWriter(new FileOutputStream(categoryDataFile), "UTF-8");
			document.write(writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reloadCategory();
		}
	}
	
	// 增加分类
	public static void appendCategory(Category category) {
		SAXReader reader = new SAXReader();
		Writer writer = null;
		try {
			Document document = reader.read(categoryDataFile);
			Element root = document.getRootElement();
			Element p = root.addElement("category");
			p.addAttribute("id", String.valueOf(category.getId()));
			p.addElement("name").setText(category.getName());
			writer = new OutputStreamWriter(new FileOutputStream(categoryDataFile), "UTF-8");
			document.write(writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reloadCategory();
		}
	}
	
	public static void deleteBook(String id) {
		SAXReader reader = new SAXReader();
		Writer writer = null;
		try {
			Document document = reader.read(bookDataFile);
			List<Node> nodes = document.selectNodes("/root/book[@id=" + id + "]");
			if(nodes.size() == 0) throw new RuntimeException("id=" + id + "图书不存在");
			Element p = (Element) nodes.get(0);
			p.getParent().remove(p);
			writer = new OutputStreamWriter(new FileOutputStream(bookDataFile), "UTF-8");
			document.write(writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reloadBook();
		}
	}
	
	public static void appendBook(Book book) {
		SAXReader reader = new SAXReader();
		Writer writer = null;
		try {
			Document document = reader.read(bookDataFile);
			Element root = document.getRootElement();
			Element p = root.addElement("book");
			p.addAttribute("id", String.valueOf(book.getId()));
			p.addElement("name").setText(book.getName());
			p.addElement("category").setText(book.getCategory());
			p.addElement("price").setText(String.valueOf(book.getPrice()));
			p.addElement("cover").setText(book.getCover());
			p.addElement("note").setText(book.getNote());
			writer = new OutputStreamWriter(new FileOutputStream(bookDataFile), "UTF-8");
			document.write(writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			reloadBook();
		}
	}
	
	
	public static void main(String[] args) {
		// 测试用主方法
		System.out.println("添加前:");
		for(Category data : categoryData) {
			System.out.println(data.toString());
		}
		Category category = new Category();
		category.setId(5);
		category.setName("新增分类");
		XmlDataSource.appendCategory(category);
		System.out.println("添加后:");
		for(Category data : categoryData) {
			System.out.println(data.toString());
		}
		XmlDataSource.deleteCategory("5");
		System.out.println("删除后:");
		for(Category data : categoryData) {
			System.out.println(data.toString());
		}
	}
}
