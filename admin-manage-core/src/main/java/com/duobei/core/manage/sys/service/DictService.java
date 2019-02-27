package com.duobei.core.manage.sys.service;

import java.util.List;

import com.duobei.core.manage.sys.domain.Dict;

public interface DictService {

	/**
	 * 获取所有字典类型
	 * @return
	 */
	List<Dict> queryAllDictType();
	/**
	 * 查询字典值-根据字典类型
	 * @param dictType
	 * @return
	 */
	List<Dict> queryDictByDictType(String dictType);
	/**
	 * 查询字典项
	 * @param pId
	 * @return
	 */
	List<Dict> queryDictByPId(Integer pId);

	/**
	 * 根据字典类型获取单个字典表值
	 * @param dictType
	 * @return
	 */
	Dict getDictOneByType(String dictType);



}
