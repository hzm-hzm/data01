package xin.hlao.bookstore.admin.domain;

public class Admin {
	private String aid;
	private String adminname;
	private String adminpassword;
	public Admin(String aid, String adminname, String adminpassword) {
		super();
		this.aid = aid;
		this.adminname = adminname;
		this.adminpassword = adminpassword;
	}
	public Admin() {
		super();
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminpassword() {
		return adminpassword;
	}
	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", adminname=" + adminname + ", adminpassword=" + adminpassword + "]";
	}
	
}
