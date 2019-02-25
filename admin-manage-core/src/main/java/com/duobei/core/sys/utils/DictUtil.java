package com.duobei.core.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.duobei.context.AppContext;
import com.duobei.core.sys.domain.Dict;
import com.duobei.core.sys.service.DictService;

public class DictUtil {

	public static final HashMap<String,List<Dict>> cache=new HashMap<String,List<Dict>>();
	
	/**
	 * 获取字典对象列表
	 * @param dictType
	 * @return
	 */
	public static List<Dict> getDictList(String dictType) {
		DictService dictService = AppContext.getBean(DictService.class);
		List<Dict> dictList = cache.get(dictType);
		if (dictList == null || dictList.size() == 0) {
			dictList = dictService.queryDictByDictType(dictType);
			if (dictList == null) {
				dictList = new ArrayList<Dict>();
			}
			cache.put(dictType, dictList);
		}
		return dictList;
	}

	/**
	 * 获取字典标签
	 * @param dictType
	 * @param dictVal
	 * @return
	 */
	public static String getDictLabel(String dictType,String dictVal) {
		List<Dict> dictList = cache.get(dictType);
		if (dictList == null) {
			dictList = getDictList(dictType);
		}
		if(dictList!=null){
			for (Dict d:dictList) {
				if (d.getDicVal().equals(dictVal)) {
					return d.getDicCode();
				}
			}
		}
		return "";
	}
}
