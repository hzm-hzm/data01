package xin.hlao.service;

import java.util.List;

import org.w3c.dom.ls.LSInput;

import xin.hlao.bean.Comment;
import xin.hlao.bean.Topic;

public interface TopicService {

//	查找全部话题
	public List<Topic> findAllTopics();
	
//	添加一个话题
	public void addOneTopic(Topic topic); 
	
//	通过类别查找话题
	public List<Topic> findTopicsBySort(Integer sid);
	
//	通过用户查找话题
	public List<Topic> findTopicsByUser(String uid);
	
//	通过id查找话题的详情
	public Topic findOneTopic(String tid);
	
//通过标题模糊查询话题
	public List<Topic> findTopicsByTitle(String title);
	
//	根据用户评论来找话题
	public List<Topic> findTopicsByUserComment(List<Comment> comments);
	
//	删除指定的话题
	public void deleteTopic(String tid);
	
}
