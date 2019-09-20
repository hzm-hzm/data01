package xin.hlao.bean;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

//评论
public class Comment {
//	id
    private String cid;
//内容
    @NotEmpty(message="内容不能为空！")
    private String content;
//时间
    private Date time;
//连接话题
    private String tid;


//连接用户
    private String uid;
    private User user;
    
    

    public Comment() {
		super();
	}
    
    

	public Comment(String cid, String content, Date time, String tid, String uid) {
		super();
		this.cid = cid;
		this.content = content;
		this.time = time;
		this.tid = tid;
		this.uid = uid;
	}



	public Comment(String cid, String content, Date time, String tid,  String uid, User user) {
		super();
		this.cid = cid;
		this.content = content;
		this.time = time;
		this.tid = tid;
		this.uid = uid;
		this.user = user;
	}



	public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getUid() {
        return uid;
    }
    
    

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }



	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", content=" + content + ", time=" + time + ", tid=" + tid + ", uid=" + uid
				+ ", user=" + user + "]";
	}



	
	
}