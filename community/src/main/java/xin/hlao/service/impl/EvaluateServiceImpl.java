package xin.hlao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xin.hlao.bean.Evaluate;
import xin.hlao.dao.EvaluateMapper;
import xin.hlao.service.EvaluateService;

@Service
public class EvaluateServiceImpl implements EvaluateService {

	@Autowired
	EvaluateMapper evaluateMapper;
	
	
	@Override
	public Evaluate findOneEvaluate(String eid) {
		// TODO Auto-generated method stub
		return evaluateMapper.selectByPrimaryKey(eid);
	}

//创建
	@Override
	public void addEvaluate(Evaluate evaluate) {
		// TODO Auto-generated method stub
		 evaluateMapper.insert(evaluate);
	}

//	点赞
	@Override
	public void update_eva(Evaluate evaluate) {
		// TODO Auto-generated method stub
		evaluateMapper.updateByPrimaryKey(evaluate);
	}

//	删除指定话题的点击评论
	@Override
	public void deleteEvaluateByEid(String eid) {
		// TODO Auto-generated method stub
		evaluateMapper.deleteByPrimaryKey(eid);
	}
	
	
	
}
