package xin.hlao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.mysql.fabric.xmlrpc.base.Array;

import xin.hlao.bean.Comment;
import xin.hlao.bean.Topic;
import xin.hlao.bean.TopicExample;
import xin.hlao.bean.TopicExample.Criteria;
import xin.hlao.dao.TopicMapper;
import xin.hlao.service.TopicService;
import xin.hlao.tool.LoadTool;

@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	LoadTool loadTool;
	
	@Autowired
	TopicMapper topicMapper;
	
	
	@Override
	public List<Topic> findAllTopics() {
		// TODO Auto-generated method stub
		List<Topic> topics=topicMapper.selectByExample(null);
		return loadTool.loadTopics(topics);
	}

	@Override
	public void addOneTopic(Topic topic) {
		// TODO Auto-generated method stub
		topicMapper.insert(topic);
	}
	
	//通过话题查找话题
	@Override
	public List<Topic> findTopicsBySort(Integer sid) {
		// TODO Auto-generated method stub
		TopicExample example=new TopicExample();
		Criteria criteria=example.createCriteria();
		criteria.andSidEqualTo(sid);
		return loadTool.loadTopics(topicMapper.selectByExample(example));
	}

	//通过用户来查找话题
	@Override
	public List<Topic> findTopicsByUser(String uid) {
		// TODO Auto-generated method stub
		TopicExample example=new TopicExample();
		Criteria criteria=example.createCriteria();
		criteria.andUidEqualTo(uid);
		return loadTool.loadTopics(topicMapper.selectByExample(example));
	}

//	通过标题模糊查找话题
	@Override
	public List<Topic> findTopicsByTitle(String title) {
		// TODO Auto-generated method stub
		TopicExample example=new TopicExample();
		Criteria criteria=example.createCriteria();
		title="%"+title+"%";
		criteria.andTitleLike(title);
		return loadTool.loadTopics(topicMapper.selectByExample(example));
	}

//	通过id查找topic
	@Override
	public Topic findOneTopic(String tid) {
		// TODO Auto-generated method stub
		Topic topic=topicMapper.selectByPrimaryKey(tid);
		return loadTool.loadTopic(topic);
	}

//	根据用户评论来找话题
	@Override
	public List<Topic> findTopicsByUserComment(List<Comment> comments) {
		// TODO Auto-generated method stub
		List<String> tids=new ArrayList<String>();
		for (Comment comment : comments) {
			String tid=comment.getTid();
			tids.add(tid);
		}
		
		
		TopicExample example=new TopicExample();
		for (String tid : tids) {
			Criteria criteria=example.createCriteria();
			criteria.andTidEqualTo(tid);
			example.or(criteria);
		}
		List<Topic> topics = topicMapper.selectByExample(example);
		return loadTool.loadTopics(topics);
	}

//	删除指定的话题
	@Override
	public void deleteTopic(String tid) {
		// TODO Auto-generated method stub
		topicMapper.deleteByPrimaryKey(tid);
	}


	
	

}
