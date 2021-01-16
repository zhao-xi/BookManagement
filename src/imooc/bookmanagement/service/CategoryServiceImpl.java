package imooc.bookmanagement.service;

import java.util.List;

import imooc.bookmanagement.pojo.Category;
import imooc.bookmanagement.util.XmlDataSource;

public class CategoryServiceImpl {
	public List<Category> getCategoryList() {
		return XmlDataSource.getRawCategoryData();
	}
	public void addCategory(String categoryId, String categoryName) {
		Category category = new Category();
		category.setId(Integer.parseInt(categoryId));
		category.setName(categoryName);
		XmlDataSource.appendCategory(category);
	}
	public void deleteCategory(String categoryId) {
		XmlDataSource.deleteCategory(categoryId);
	}
	
	public static void main(String[] args) {
		// 测试用主方法
		CategoryServiceImpl categoryService = new CategoryServiceImpl();
		
		System.out.println("添加前:");
		for(Category data : categoryService.getCategoryList()) {
			System.out.println(data.toString());
		}
		
		categoryService.addCategory("5", "新增分类");
		System.out.println("添加后:");
		for(Category data : categoryService.getCategoryList()) {
			System.out.println(data.toString());
		}
		
		categoryService.deleteCategory("5");
		System.out.println("删除后:");
		for(Category data : categoryService.getCategoryList()) {
			System.out.println(data.toString());
		}
	}
}
