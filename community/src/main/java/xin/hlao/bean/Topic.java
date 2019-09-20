package xin.hlao.bean;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

//话题
public class Topic {
//	id
    private String tid;
//题目
    @Length(min=1,max=20,message="字数不能少于1或者超过20！")
    @NotEmpty(message="标题不能为空！")
    private String title;
//内容
    @NotEmpty(message="内容不能为空！")
    private String content;
//时间
    private Date time;
//图片
    private String picture;
//连接类别
    private Integer sid;
    private Sort sort;

//    连接用户
    private String uid;
    private User user;

//    连接评价
    private String eid;
    private Evaluate evaluate;

	public Topic() {
		super();
	}
	
	
    
    
    public Topic(String tid, String title, String content, Date time, String picture, Integer sid, String uid,
			String eid) {
		super();
		this.tid = tid;
		this.title = title;
		this.content = content;
		this.time = time;
		this.picture = picture;
		this.sid = sid;
		this.uid = uid;
		this.eid = eid;
	}




	public Topic(String tid, String title, String content, Date time, String picture, Integer sid, Sort sort,
			String uid, User user, String eid, Evaluate evaluate) {
		super();
		this.tid = tid;
		this.title = title;
		this.content = content;
		this.time = time;
		this.picture = picture;
		this.sid = sid;
		this.sort = sort;
		this.uid = uid;
		this.user = user;
		this.eid = eid;
		this.evaluate = evaluate;
	}



	public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Evaluate getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Evaluate evaluate) {
		this.evaluate = evaluate;
	}

	@Override
	public String toString() {
		return "Topic [tid=" + tid + ", title=" + title + ", content=" + content + ", time=" + time + ", picture="
				+ picture + ", sid=" + sid + ", sort=" + sort + ", uid=" + uid + ", user=" + user + ", eid=" + eid
				+ ", evaluate=" + evaluate + "]";
	}
    
    
}