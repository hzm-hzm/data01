package xin.hlao.test;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
//
//import xin.hlao.APP;
//import xin.hlao.bean.Topic;
//import xin.hlao.dao.redis.RedisUtil;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes= {APP.class})
//public class RedisTest {
//
//	@Test
//	public void fun1() throws JSONException, ParseException {
//		String s=RedisUtil.stringGet("user:7894562");
////		System.out.println(s.toString());
////		JSONObject jsonObject=JSONObject.
//		JSONObject jsonObj=new JSONObject(s);
////		String sid=jsonObj.getString(s);
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Topic topic=new Topic(jsonObj.getString("tid"), jsonObj.getString("title"), 
//				jsonObj.getString("content"),sdf.parse(jsonObj.getString("time")) , jsonObj.getString("picture"), 
//				jsonObj.getInt("sid"), jsonObj.getString("uid"), jsonObj.getString("eid"));
////		System.out.println(topic);
//	}
//	
//}
