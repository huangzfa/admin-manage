package com.duobei.core.auth.dao;

import java.util.List;

import com.duobei.core.auth.domain.Organ;
import com.duobei.core.auth.domain.criteria.OrganCriteria;
import com.duobei.core.auth.domain.vo.OrganVo;

public interface OrganDao {
	
	OrganVo queryByOrganId(Integer organId);

	Organ queryOrganByCode(String organCode);

	List<OrganVo> queryOrganListByParent(OrganCriteria criteria);
}