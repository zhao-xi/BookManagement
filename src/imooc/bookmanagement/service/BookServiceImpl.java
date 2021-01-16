package imooc.bookmanagement.service;

import java.util.ArrayList;
import java.util.List;

import imooc.bookmanagement.pojo.Book;
import imooc.bookmanagement.util.XmlDataSource;

public class BookServiceImpl {
	public List<Book> getBookList() {
		return XmlDataSource.getRawBookData();
	}
	public Book getBookById(String bookId) {
		for(Book book : XmlDataSource.getRawBookData()) {
			if(String.valueOf(book.getId()).equals(bookId)) {
				return book;
			}
		}
		return null;
	}
	public List<Book> getBooksByCategoryName(String categoryName) {
		List<Book> books = new ArrayList<>();
		for(Book book : XmlDataSource.getRawBookData()) {
			if(book.getCategory().equals(categoryName)) {
				books.add(book);
			}
		}
		return books;
	}
}
