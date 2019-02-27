package com.duobei.core.manage.auth.dao;

import java.util.List;

import com.duobei.core.manage.auth.domain.OrganType;
import com.duobei.core.manage.auth.domain.criteria.OrganCriteria;

public interface OrganTypeDao {
    
	List<OrganType> queryCanAddChildOrganTypeForOrganRule(OrganCriteria criteria);
}