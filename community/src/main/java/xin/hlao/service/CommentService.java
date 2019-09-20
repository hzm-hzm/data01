package xin.hlao.service;

import java.util.List;

import xin.hlao.bean.Comment;

public interface CommentService {
	
//	查找全部评论
	List<Comment> findAllComments();
	
//	根据话题查找全部评论根据时间排序
	List<Comment> findComentsBytidAcTime(String tid);
	
//	添加评论
	void add_comment(Comment comment);
	
//	删除指定的评论
	public void deleteOneComment(String cid);
	
//	根据用户id查询全部评论
	public List<Comment> findCommentsByUid(String uid);
	
//	删除指定评论的话题
	public void deleteCommentsByTid(String tid);
}
