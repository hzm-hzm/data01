package xin.hlao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xin.hlao.bean.Sort;
import xin.hlao.dao.SortMapper;
import xin.hlao.service.SortService;

@Service
public class SortServiceImpl implements SortService {
	
	@Autowired
	SortMapper sortMapper;

//	查找指定的分类
	@Override
	public Sort findOneSort(Integer sid) {
		// TODO Auto-generated method stub
		return sortMapper.selectByPrimaryKey(sid);
	}

//	查找全部分类
	@Override
	public List<Sort> findAllSort() {
		// TODO Auto-generated method stub
		return sortMapper.selectByExample(null);
	}

}
