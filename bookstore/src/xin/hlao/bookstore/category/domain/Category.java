package xin.hlao.bookstore.category.domain;

//分类
public class Category {
	private String cid;
	private String cname;
	private Integer count;
	
	
	public Category() {
		super();
	}
	
	public Category(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	

	public Category(String cid, String cname, Integer count) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.count = count;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", count=" + count + "]";
	}

	
	
}
