package xin.hlao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xin.hlao.bean.Evaluate;
import xin.hlao.bean.Msg;
import xin.hlao.service.EvaluateService;

@RestController
public class EvaluateController {
	
	@Autowired
	EvaluateService evaluateService;

//	点赞
	@RequestMapping("/addGood")
	public Msg addGood(@RequestParam(value="eid") String _eid,
			@RequestParam(value="uid") String uid) {
		String eid=_eid.substring(1, _eid.length()-1);
		Evaluate evaluate = evaluateService.findOneEvaluate(eid);
		Integer admire = evaluate.getAdmire();
		
		String uids = evaluate.getUids();
		if(uids!=null) {
			String[] uidsS = uids.split("-,-");
			for (String _uid : uidsS) {
				if(_uid==uid || _uid.equals(uid)) {
					return Msg.fail();
				}
			}
		}
		uids=uids+"-,-"+uid;
		evaluate.setUids(uids);
		evaluate.setAdmire(admire+1);
		evaluateService.update_eva(evaluate);
		return Msg.success().add("eid", _eid);
	}
	
//	点踩
	@RequestMapping("/addBad")
	public Msg addBad(@RequestParam(value="eid") String _eid,
			@RequestParam(value="uid") String uid) {
		String eid=_eid.substring(1, _eid.length()-1);
		Evaluate evaluate = evaluateService.findOneEvaluate(eid);
		Integer trample = evaluate.getTrample();
		
		String uids = evaluate.getUids();
		if(uids!=null) {
			String[] uidsS = uids.split("-,-");
			for (String _uid : uidsS) {
				if(_uid==uid || _uid.equals(uid)) {
					return Msg.fail();
				}
			}
		}
		uids=uids+"-,-"+uid;
		evaluate.setUids(uids);
		evaluate.setTrample(trample+1);
		evaluateService.update_eva(evaluate);
		return Msg.success().add("eid", _eid);
	}

}
