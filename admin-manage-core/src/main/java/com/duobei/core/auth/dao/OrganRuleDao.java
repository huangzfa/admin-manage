package com.duobei.core.auth.dao;

import java.util.List;

import com.duobei.core.auth.domain.criteria.OrganCriteria;
import com.duobei.core.auth.domain.vo.OrganRuleVo;

public interface OrganRuleDao {
	
	List<OrganRuleVo> queryAllOrganRule();

	OrganRuleVo queryOrganRuleById(OrganCriteria orgCriteria);
}