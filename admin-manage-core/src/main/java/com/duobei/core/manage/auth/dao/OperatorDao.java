package com.duobei.core.manage.auth.dao;

import java.util.List;
import java.util.Set;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.filter.impl.Op;
import com.duobei.core.manage.auth.domain.Operator;
import com.duobei.core.manage.auth.domain.criteria.UserCriteria;
import com.duobei.core.manage.auth.domain.vo.OperatorVo;
import org.apache.ibatis.annotations.Param;

public interface OperatorDao {
	Operator queryOperatorByLoginName(UserCriteria criteria);
	
	OperatorVo queryOperatorVoById(UserCriteria criteria);

	int countByCriteria(UserCriteria userCriteria);

	List<OperatorVo> queryOperatorList(UserCriteria userCriteria);

	Set<String> queryOperatorPermission(UserCriteria userCriteria);

	List<Operator> getByOpIds(@Param("opIds") List<Integer> opIds);

	Operator getByRealName(@Param("realName") String realName);
}