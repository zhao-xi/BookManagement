package imooc.bookmanagement.pojo;

public class Book {
	private Integer id; // 图书id
	private String name; // 图书名称
	private String category; // 图书分类
	private Double price; // 图书价格
	private String cover; // 图书封面
	private String note; // 图书备注
	
	public Book() { }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", cover=" + cover
				+ ", note=" + note + "]";
	}
	
}
