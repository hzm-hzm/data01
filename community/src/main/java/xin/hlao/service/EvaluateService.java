package xin.hlao.service;

import xin.hlao.bean.Evaluate;

public interface  EvaluateService {

//	根据id查找
	public Evaluate findOneEvaluate(String eid);
	
//	创建
	public void addEvaluate(Evaluate evaluate);
	
//	点赞点踩操作
	public void update_eva(Evaluate evaluate);
	
//	删除指定话题的点击评论
	public void deleteEvaluateByEid(String eid);
}
