package xin.hlao.tool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xin.hlao.bean.Comment;
import xin.hlao.bean.Evaluate;
import xin.hlao.bean.Sort;
import xin.hlao.bean.Topic;
import xin.hlao.bean.User;
import xin.hlao.dao.EvaluateMapper;
import xin.hlao.dao.SortMapper;
import xin.hlao.dao.TopicMapper;
import xin.hlao.dao.UserMapper;

@Component
public class LoadTool {
	
	@Autowired
	SortMapper sortMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	EvaluateMapper evaluateMapper; 
	
	@Autowired
	TopicMapper topicMapper;
	
	
//	处理topic订单的加载
	public void topicTool(Topic topic) {
		Integer sid=topic.getSid();
		Sort sort=sortMapper.selectByPrimaryKey(sid);
		topic.setSort(sort);
		String uid=topic.getUid();
		User user=userMapper.selectByPrimaryKey(uid);
		topic.setUser(user);
		String eid=topic.getEid();
		Evaluate evaluate=evaluateMapper.selectByPrimaryKey(eid);
		topic.setEvaluate(evaluate);
	}
	
	
	public Topic loadTopic(Topic topic) {
		topicTool(topic);
		return topic;
	}
	
	
	public List<Topic> loadTopics(List<Topic> topics){
		for (Topic topic : topics) {
			topicTool(topic);
		}
		return topics;
	}
	
	
	
	public void CommentTool(Comment comment) {
		String uid=comment.getUid();
		User user=userMapper.selectByPrimaryKey(uid);
		comment.setUser(user);
	}
	
	public Comment loadComment(Comment comment) {
		CommentTool(comment);
		return comment;
	}
	
	public List<Comment> loadComments(List<Comment> comments){
		for (Comment comment : comments) {
			CommentTool(comment);
		}
		return comments;
	}
	
}
