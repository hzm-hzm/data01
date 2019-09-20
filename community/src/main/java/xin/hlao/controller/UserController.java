package xin.hlao.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.multi.MultiTabbedPaneUI;
import javax.validation.Valid;

import org.apache.coyote.Request;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartResolutionDelegate;

import com.github.pagehelper.PageHelper;

import io.netty.handler.codec.http.HttpRequest;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import xin.hlao.bean.Comment;
import xin.hlao.bean.Msg;
import xin.hlao.bean.Topic;
import xin.hlao.bean.User;
import xin.hlao.service.CommentService;
import xin.hlao.service.TopicService;
import xin.hlao.service.UserService;
import xin.hlao.service.exception.MyException;
import xin.hlao.tool.EmailTool;
import xin.hlao.tool.PictureTool;
import xin.hlao.tool.RandomTool;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	EmailTool emailTool;
	
//	注册操作
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public Msg saveUser( @Valid User user,BindingResult result,
			MultipartFile file,String password1)throws Exception {
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
				if(password1.equals("") || password1==null) {
					map.put("password1","密码不能为空！");
				}else if(!user.getPassword().equals(password1)) {
					map.put("password", "两次密码不一致！");
				}
			}
			return Msg.fail().add("fieldErrors", map);
			
		}else {
			
			String uid=RandomTool.getRandomString(10);
			boolean state=false;
			String code=uid+uid+uid;
			 String content="<html>\n"+"<body>\n"
		                + "<a href='http://localhost:80/to_msg?code="+code+"'>点击激活</a>\n"
		                +"</body>\n"+"</html>";
			user.setPicture("/image/regist/default.jpg");
			user.setUid(uid);
			user.setState(state);
			user.setEmail(user.getEmail());
			
			
			try {
//				System.err.println(user);
				userService.addOneUser(user);
				
			} catch (MyException e) {
				// TODO Auto-generated catch block
				String error=e.getMessage();
				String[] errors=error.split(",");
				map.put(errors[0], errors[1]);
				return Msg.fail().add("fieldErrors", map);
			}
			String picture=PictureTool.savePicture(file);
			user.setPicture(picture);
			userService.updateUser(user, user.getUid());
			emailTool.sendEamil(user.getEmail(), "激活信息", content);
			return Msg.success().add("user", user);
		}
	}
	
	
	
	
//	登录操作
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public Msg userLogin(@Valid User user,BindingResult result,HttpServletRequest request) {
		
		Map<String, Object> map=new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				map.put(fieldError.getField(), fieldError.getDefaultMessage());	
			}
			if(fieldErrors.size()>3) {
				return Msg.fail().add("fieldErrors", map);
			}
			
		}
		
		try {
			userService.verifyUserLogin(user);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			String[] error=e.getMessage().split(",");
			map.put(error[0], error[1]);
			return Msg.fail().add("fieldErrors", map);
		}
		
		
		User login_user=userService.findUserByEmail(user.getEmail()).get(0);
//		String uid=login_user.getUid();
		login_user.setPassword(null);
		request.getSession().setAttribute("user",login_user);
		return Msg.success();
	}
	
//	登出操作
	@RequestMapping("/exit")
	public Msg user_exit(HttpServletRequest request) {
		request.getSession().invalidate();
		return Msg.success();
	};
	
	
//利用uid查找用户
	@RequestMapping("/find_user")
	public Msg findUserByid(@RequestParam("uid") String uid) {
		User user=userService.findOneUser(uid);
		return Msg.success().add("user", user);
	}
	
//	利用uid查找该话题和评论的数量
	@RequestMapping("/findCount")
	public Msg findcount(@RequestParam("uid") String uid) {
		List<Topic> topics=topicService.findTopicsByUser(uid);
		List<Comment> comments=commentService.findCommentsByUid(uid);
		Integer topicsCount=topics.size();
		Integer commentsCount=comments.size();
		return Msg.success().add("topicsC", topicsCount).add("commentsC", commentsCount);
	}
	
	
	@DeleteMapping("/testDelete")
	public Msg testDelete(@RequestParam("id") String id) {
		return Msg.success().add("id", id);
	}

	
}
