package com.duobei.core.manage.auth.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

@Data
public class MenuCriteria extends Pagination{
	private static final long serialVersionUID = -2985507170674224328L;
	private Integer menuId;
	private Integer parentId;
	private String menuTypeZd;//菜单类型
	private String stateZd;
	private Integer opId;//操作员id

	
}
