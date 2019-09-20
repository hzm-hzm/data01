package xin.hlao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xin.hlao.bean.Comment;
import xin.hlao.bean.CommentExample;
import xin.hlao.bean.CommentExample.Criteria;
import xin.hlao.dao.CommentMapper;
import xin.hlao.service.CommentService;
import xin.hlao.tool.LoadTool;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	LoadTool loadTool;

	@Autowired
	CommentMapper cm;
		
	@Override
	public List<Comment> findAllComments() {
		// TODO Auto-generated method stub
		List<Comment> comments=cm.selectByExample(null);
		loadTool.loadComments(comments);
		return comments;
	}

	@Override
	public List<Comment> findComentsBytidAcTime(String tid) {
		// TODO Auto-generated method stub
		CommentExample example=new CommentExample();
		example.setOrderByClause("time DESC");
		Criteria createCriteria = example.createCriteria();
		createCriteria.andTidEqualTo(tid);
		List<Comment> comments=cm.selectByExample(example);
		return loadTool.loadComments(comments);
	}

//	增加评论
	@Override
	public void add_comment(Comment comment) {
		// TODO Auto-generated method stub
		cm.insert(comment);
	}
	
//	删除指定cid的评论
	@Override
	public void deleteOneComment(String cid) {
		// TODO Auto-generated method stub
		cm.deleteByPrimaryKey(cid);
	}

//	根据用户查询全部话题
	@Override
	public List<Comment> findCommentsByUid(String uid) {
		// TODO Auto-generated method stub
		CommentExample example=new CommentExample();
		Criteria criter=example.createCriteria();
		criter.andUidEqualTo(uid);
		return cm.selectByExample(example);  
	}

//	删除指定的话题的评论
	@Override
	public void deleteCommentsByTid(String tid) {
		// TODO Auto-generated method stub
		CommentExample example=new CommentExample();
		Criteria criteria=example.createCriteria();
		criteria.andTidEqualTo(tid);
		cm.deleteByExample(example);
	}

}
