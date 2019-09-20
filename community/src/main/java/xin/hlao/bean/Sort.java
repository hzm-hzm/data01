package xin.hlao.bean;

//分类
public class Sort {
//	id
    private Integer sid;
//类别名字
    private String sname;

    
    public Sort() {
	super();
}
    

	public Sort(Integer sid, String sname) {
		super();
		this.sid = sid;
		this.sname = sname;
	}


	public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }


	@Override
	public String toString() {
		return "Sort [sid=" + sid + ", sname=" + sname + "]";
	}
    
}