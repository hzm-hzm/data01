package xin.hlao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xin.hlao.bean.Msg;
import xin.hlao.bean.Sort;
import xin.hlao.service.SortService;

@RestController
public class SortController {
	
	@Autowired
	SortService sortService;
	

	@RequestMapping("/findAllSort")
	public Msg find_All_Sort() {
		List<Sort> sorts=sortService.findAllSort();
		return Msg.success().add("sorts", sorts);
	}
	
}
