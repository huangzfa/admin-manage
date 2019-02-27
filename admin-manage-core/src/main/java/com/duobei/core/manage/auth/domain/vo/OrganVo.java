package com.duobei.core.manage.auth.domain.vo;

import com.duobei.core.manage.auth.domain.Organ;
import lombok.Data;

@Data
public class OrganVo extends Organ {
	private static final long serialVersionUID = -5694813451668460251L;
	private String organTypeCode;
	private String organTypeName;
	private Integer selectOrganId;//选择的组织

	
}
