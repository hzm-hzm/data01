package xin.hlao.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.fabric.xmlrpc.base.Array;
import com.sun.mail.handlers.multipart_mixed;

import xin.hlao.bean.Comment;
import xin.hlao.bean.Evaluate;
import xin.hlao.bean.Msg;
import xin.hlao.bean.Sort;
import xin.hlao.bean.Topic;
import xin.hlao.service.CommentService;
import xin.hlao.service.EvaluateService;
import xin.hlao.service.SortService;
import xin.hlao.service.TopicService;
import xin.hlao.tool.PictureTool;
import xin.hlao.tool.RandomTool;

@RestController
public class TopicController {
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	EvaluateService evaluateService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	SortService sortService;

//	查找全部话题
	@RequestMapping("/allTopics")
	public Msg allTopics(@RequestParam(value="ph",defaultValue="1")Integer ph) {
		PageHelper.startPage(ph, 10);
		List<Topic> topics=topicService.findAllTopics();
		PageInfo pageinfo=new PageInfo<>(topics, 5);
		Map<String, Object> choose=new HashMap<String, Object>();
		choose.put("id",1);
		return Msg.success().add("pageinfo", pageinfo).add("choose", choose);
	}
	
//	通过类别来查找话题
	@RequestMapping("/topicsBySort")
	public Msg topicsBySort(@RequestParam(value="ph",defaultValue="1")Integer ph,
			@RequestParam(value="sid") Integer sid) {
		PageHelper.startPage(ph, 10);
		List<Topic> topics=topicService.findTopicsBySort(sid);
		Sort sort = sortService.findOneSort(sid);
		PageInfo pageinfo=new PageInfo<>(topics, 5);
		Map<String, Object> choose=new HashMap<String, Object>();
		choose.put("id",2);
		choose.put("sid", sid);
		return Msg.success().add("pageinfo", pageinfo).add("sname",sort.getSname()).add("choose", choose);
	}
	
//	通过标题模糊查询话题
	@RequestMapping("/topicsByTtile")
	public Msg topicsByTtile(@RequestParam(value="ph",defaultValue="1")Integer ph,
	@RequestParam(value="title") String title) {
		PageHelper.startPage(ph, 10);
		List<Topic> topics=topicService.findTopicsByTitle(title);
		PageInfo pageinfo=new PageInfo<>(topics, 5);
		Map<String, Object> choose=new HashMap<String, Object>();
		choose.put("id",3);
		choose.put("title", title);
		return Msg.success().add("pageinfo", pageinfo).add("choose", choose);
	}
	
//通过用户id查询话题
	@RequestMapping("/topicsByUid")
	public Msg topicsByUid(@RequestParam(value="ph",defaultValue="1")Integer ph,
		@RequestParam(value="uid") String uid) {
		PageHelper.startPage(ph, 10);
		List<Topic> topics = topicService.findTopicsByUser(uid);
		PageInfo pageinfo=new PageInfo<>(topics, 5);
		Map<String, Object> choose=new HashMap<String, Object>();
		choose.put("id", 4);
		return Msg.success().add("pageinfo", pageinfo).add("choose", choose);
	}
	
//	通过用户指定评论查找指定话题
	@RequestMapping("/topicsByUidComment")
	public Msg topicsByUidComment(@RequestParam(value="ph",defaultValue="1")Integer ph,
			@RequestParam("uid") String uid) {
		PageHelper.startPage(ph, 10);
		List<Comment> comments=commentService.findCommentsByUid(uid);
		List<Topic> topics=topicService.findTopicsByUserComment(comments);
		if(comments.size()==0) {
			topics=new ArrayList<>();
		}
		PageInfo pageinfo=new PageInfo<>(topics,5);
		Map<String, Object> choose=new HashMap<String, Object>();
		choose.put("id", 5);
		return Msg.success().add("pageinfo", pageinfo).add("choose", choose);
	}
	
	

	//添加话题
	@RequestMapping(value="/topic",method=RequestMethod.POST)
	public Msg addTopic(@Valid Topic topic, BindingResult result,String uid,
			MultipartFile file1,MultipartFile file2,MultipartFile file3) throws Exception {		
		
		Map<String, Object> errors=new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errors", errors);
		}
		
		String tid=RandomTool.getRandomString(10);
		topic.setTid(tid);
		Date date = new Date();
		topic.setTime(date);
		topic.setUid(uid);
		
//		String eid=RandomTool.getRandomString(10);
		Evaluate evaluate=new Evaluate(tid, 0, 0, null);
		evaluateService.addEvaluate(evaluate);
		topic.setEid(tid);
		
		String picture1=PictureTool.saveTopicPicture(file1);
		String picture2=PictureTool.saveTopicPicture(file2);
		String picture3=PictureTool.saveTopicPicture(file3);
		String picture=picture1+"-,-"+picture2+"-,-"+picture3;
		topic.setPicture(picture);
		
		topicService.addOneTopic(topic);
		return Msg.success().add("topic", topic);
	}
	
//	进入话题详情
	@RequestMapping("/toTopic")
	public Msg toTopic(@RequestParam(value="tid") String tid) {
		tid=tid.substring(1, tid.length()-1);
		Topic topic=topicService.findOneTopic(tid);
		List<Comment> comments=commentService.findComentsBytidAcTime(tid);
		return Msg.success().add("topic", topic).add("comments", comments);
	};
	
//	删除指定话题
	@DeleteMapping("/Topic")
	public Msg deleteTopic(@RequestParam("tid") String tid) {
		tid=tid.substring(1, tid.length()-1);
		topicService.deleteTopic(tid);
		commentService.deleteCommentsByTid(tid);
		evaluateService.deleteEvaluateByEid(tid);
		return Msg.success();
	}
	
	
}
