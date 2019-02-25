package com.duobei.core.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duobei.core.sys.dao.DictDao;
import com.duobei.core.sys.dao.mapper.DictMapper;
import com.duobei.core.sys.domain.Dict;
import com.duobei.core.sys.domain.DictExample;
import com.duobei.core.sys.service.DictService;

@Service("DictService")
public class DictServiceImpl implements DictService {

	@Autowired
	private DictMapper dictMapper;
	@Autowired
	private DictDao dictDao;
	
	@Override
	public List<Dict> queryAllDictType(){
		DictExample example=new DictExample();
		example.createCriteria().andPidEqualTo(0);
		return dictMapper.selectByExample(example);
	}
	
	@Override
	public List<Dict> queryDictByDictType(String dictType){
		return dictDao.queryDictByDictType(dictType);
	}

	@Override
	public List<Dict> queryDictByPId(Integer pId) {
		return dictDao.queryDictByPId(pId);
	}

	@Override
	public Dict getDictOneByType(String dictType) {

		return dictDao.getDictOneByType(dictType);
	}
}
