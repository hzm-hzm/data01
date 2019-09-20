package xin.hlao.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import xin.hlao.APP;
import xin.hlao.bean.Comment;
import xin.hlao.bean.Topic;
import xin.hlao.service.CommentService;
import xin.hlao.service.SortService;
import xin.hlao.service.TopicService;
import xin.hlao.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= {APP.class})
public class MysqlTest {
	
	@Autowired
	CommentService cs;
	
	@Autowired
	TopicService ts;
	
	
	@Autowired
	UserService us;
	
	@Autowired
	SortService ss;
	

	@Test
	public void fun1() {
//		System.out.println(ts);
//		System.out.println(cs.findAllComments());
//		List<Topic> topic=ts.findAllTopics();
//		System.out.println(us.findOneUser("123456abc"));
//		PageHelper.startPage(1, 2);
//		List<Topic> topics=ts.findAllTopics();
//		PageInfo page=new PageInfo(topics,5);
//		System.out.println(page);
//		System.out.println(cs.findAllComments());
//		Topic topic=new Topic("7894562", "NBA交易", "什么鬼呀", new Date(), "88.jpg", 1,  "123456abc", "123456");
//		ts.addOneTopics(topic);
//		System.out.println(ss.findAllSort());
//		System.out.println(ts.findAllTopics());
//		System.out.println(ts.findTopicsBySort(1));
//		System.out.println(ts.findTopicsByUser("123456abc"));
//		System.out.println(ts.findTopicsByTitle("蔡"));
//		System.out.println(RandomTool.getRandomString(10));
//		System.out.println("haha");
//		System.out.println(us.findUserByEmail("1139434884@qq.com"));
//		System.out.println(us.findUserByphone("110"));
//		System.out.println(ts.findOneTopic("VCevLlriLh"));
//		List<Comment> comments = cs.findComentsBytidAcTime("123abc");
//		for (Comment comment : comments) {
//			System.out.println(comment);
//		}
		List<Comment> comments = cs.findCommentsByUid("123456abc");
//		for (Comment comment : comments) {
//			System.out.println(comment);
//		}
		List<Topic> topics=ts.findTopicsByUserComment(comments);
		for (Topic topic : topics) {
			System.out.println(topic);
		}
	}
	
}
