package com.duobei.core.auth.dao;

import java.util.List;

import com.duobei.core.auth.domain.OrganType;
import com.duobei.core.auth.domain.criteria.OrganCriteria;

public interface OrganTypeDao {
    
	List<OrganType> queryCanAddChildOrganTypeForOrganRule(OrganCriteria criteria);
}