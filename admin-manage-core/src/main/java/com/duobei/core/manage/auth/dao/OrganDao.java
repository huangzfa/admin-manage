package com.duobei.core.manage.auth.dao;

import java.util.List;

import com.duobei.core.manage.auth.domain.Organ;
import com.duobei.core.manage.auth.domain.criteria.OrganCriteria;
import com.duobei.core.manage.auth.domain.vo.OrganVo;

public interface OrganDao {
	
	OrganVo queryByOrganId(Integer organId);

	Organ queryOrganByCode(String organCode);

	List<OrganVo> queryOrganListByParent(OrganCriteria criteria);
}