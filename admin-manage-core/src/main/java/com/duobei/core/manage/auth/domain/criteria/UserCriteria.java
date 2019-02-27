package com.duobei.core.manage.auth.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

@Data
public class UserCriteria extends Pagination {
	
	private static final long serialVersionUID = -6857087480897989821L;
	
	private Integer organId;
	private String pathLike;
	private Integer selectOrganId;//选择的组织
	private String loginName;
	private String realName;
	private Integer opId;//操作员id
	private String menuTypeZd;//菜单类型
	private String menuStateZd;//菜单状态
	
}
