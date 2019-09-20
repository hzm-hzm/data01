package xin.hlao.service;

import java.util.List;

import xin.hlao.bean.Sort;

public interface SortService {
//	查找一个类别
	public Sort findOneSort(Integer sid);
	
//	查找全部分类
	public List<Sort> findAllSort();
}
