package com.duobei.core.auth.dao;

import java.util.List;

import com.duobei.core.auth.domain.OperatorRoleKey;
import com.duobei.core.auth.domain.criteria.RoleCriteria;
import com.duobei.core.auth.domain.vo.OperatorRoleVo;
import org.apache.ibatis.annotations.Param;

public interface OperatorRoleDao {
	
	List<OperatorRoleKey> queryOperatorRoleIds(RoleCriteria roleCriteria);

	List<OperatorRoleVo> getRoleByOpIds(@Param("opIds") List<Integer> opIds);
}