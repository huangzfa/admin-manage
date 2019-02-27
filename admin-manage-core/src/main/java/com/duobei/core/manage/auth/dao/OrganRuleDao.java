package com.duobei.core.manage.auth.dao;

import java.util.List;

import com.duobei.core.manage.auth.domain.criteria.OrganCriteria;
import com.duobei.core.manage.auth.domain.vo.OrganRuleVo;

public interface OrganRuleDao {
	
	List<OrganRuleVo> queryAllOrganRule();

	OrganRuleVo queryOrganRuleById(OrganCriteria orgCriteria);
}