package com.duobei.core.manage.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duobei.core.manage.auth.domain.Menu;
import com.duobei.core.manage.auth.domain.criteria.MenuCriteria;

public interface MenuDao {
	
	int hasRoleUseMenu(@Param("menuId")String menuId);
	
	List<Menu> queryAdminMenu(MenuCriteria criteria);

	List<Menu> queryUserMenu(MenuCriteria criteria);

	List<Menu> queryAccreditMenu(MenuCriteria criteria);
}