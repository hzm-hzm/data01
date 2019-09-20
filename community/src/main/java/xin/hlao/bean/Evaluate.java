package xin.hlao.bean;

//评价
public class Evaluate {
//	id
    private String eid;
//赞赏
    private Integer admire;
//踩
    private Integer trample;
//完成该操作的用户
    private String uids;
    
    

    public Evaluate() {
	super();
}
    

	public Evaluate(String eid, Integer admire, Integer trample, String uids) {
		super();
		this.eid = eid;
		this.admire = admire;
		this.trample = trample;
		this.uids = uids;
	}


	public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid == null ? null : eid.trim();
    }

    public Integer getAdmire() {
        return admire;
    }

    public void setAdmire(Integer admire) {
        this.admire = admire;
    }

    public Integer getTrample() {
        return trample;
    }

    public void setTrample(Integer trample) {
        this.trample = trample;
    }

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids == null ? null : uids.trim();
    }


	@Override
	public String toString() {
		return "Evaluate [eid=" + eid + ", admire=" + admire + ", trample=" + trample + ", uids=" + uids + "]";
	}
    
    
}