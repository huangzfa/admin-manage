package com.duobei.core.auth.domain.criteria;

import com.duobei.common.util.Pagination;
import lombok.Data;

@Data
public class RoleCriteria extends Pagination {
	private static final long serialVersionUID = -5284846518881653872L;
	
	private String roleName;
	private String roleStateZd;
	private Integer opId;


}
