package xin.hlao.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xin.hlao.bean.Comment;
import xin.hlao.bean.Msg;
import xin.hlao.service.CommentService;
import xin.hlao.tool.RandomTool;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	
	
//	发表评论
	@RequestMapping(value="/Comment",method=RequestMethod.POST)
	public Msg addComment(@Valid Comment comment, BindingResult result) {
		
		Map<String, Object> errors=new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				errors.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errors", errors);
		}
		
		String cid=RandomTool.getRandomString(10);
		Date time=new Date();
		comment.setCid(cid);
		comment.setTime(time);
		commentService.add_comment(comment);
		return Msg.success().add("comment", comment);
	};
	
//	删除指定的评论
	@DeleteMapping(value="/Comment")
//	@RequestMapping(value="/Comment/{cid}",method=RequestMethod.PUT)
	public Msg deleteComment(@RequestParam("cid") String cid) {
		cid=cid.substring(1, cid.length() -1);
		System.out.println(cid);
		commentService.deleteOneComment(cid);
		return Msg.success();
	}
	
	
	
}
