package xin.hlao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import xin.hlao.service.UserService;
import xin.hlao.service.exception.MyException;

@Controller
public class ToController {
	
	@Autowired
	UserService userService;

//	重定向到注册页面
	@RequestMapping("/to_regist")
//	@ResponseBody
	public String To_regist() {
//		ModelAndView mv=new ModelAndView("user/regist.html");
		return "redirect:/regist.html";
	}
	
//	重定向到首页
	@RequestMapping("/to_index")
	public String To_index() {
		return "redirect:/";
	}
	
	@RequestMapping("/to_msg")
	public String to_msg(@RequestParam(value="code" ) String code,
			Model model) {
		String _code=code.substring(10, 20);
//		System.out.println(code);
		
		try {
			userService.updateUserState(_code);
		} catch (MyException e) {
			// TODO Auto-generated catch block
			model.addAttribute("msg", e.getMessage());
			return "msg";
		}
		model.addAttribute("msg", "恭喜你！激活成功！可以去进行登录");
		return "msg";
		
	}
	
	
	
	
}
