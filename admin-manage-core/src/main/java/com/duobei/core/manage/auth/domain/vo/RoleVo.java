package com.duobei.core.manage.auth.domain.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.duobei.core.manage.auth.domain.Role;

public class RoleVo extends Role {
	private static final long serialVersionUID = 5075147890339116774L;
	
	private List<Integer> menuIdList = new ArrayList<Integer>();
	private boolean checked=false;//是否拥有该角色
	
    public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<Integer> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Integer> menuIdList) {
		this.menuIdList = menuIdList;
	}

    public String getMenuIds() {
        return StringUtils.join(getMenuIdList(), ",");
    }

    public void setMenuIds(String menuIds) {
	    if (menuIds != null) {
	    	List<Integer> menuIdList = new ArrayList<Integer>();
	        String[] ids = StringUtils.split(menuIds.trim(), ",");
	        for (String id:ids) {
				try {
					menuIdList.add(Integer.parseInt(id));
				} catch (Exception e) {
				}
			}
	        setMenuIdList(menuIdList);
	    }
    }
	
}
