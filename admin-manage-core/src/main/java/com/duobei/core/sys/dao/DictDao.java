package com.duobei.core.sys.dao;

import java.util.List;

import com.duobei.core.sys.domain.Dict;

public interface DictDao {
    
	List<Dict> queryDictByDictType(String dictType);

	List<Dict> queryDictByPId(Integer pId);

	Dict getDictOneByType(String dictType);
}