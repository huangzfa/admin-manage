package com.duobei.core.manage.auth.dao;

import java.util.List;
import java.util.Set;

import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.manage.auth.domain.criteria.UserCriteria;
import com.duobei.core.manage.auth.domain.vo.OperatorVo;

public interface OperatorDao {
	Operator queryOperatorByLoginName(UserCriteria criteria);
	
	OperatorVo queryOperatorVoById(UserCriteria criteria);

	int countByCriteria(UserCriteria userCriteria);

	List<OperatorVo> queryOperatorList(UserCriteria userCriteria);

	Set<String> queryOperatorPermission(UserCriteria userCriteria);
}