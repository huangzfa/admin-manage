package com.duobei.core.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.duobei.core.auth.domain.Menu;
import com.duobei.core.auth.domain.criteria.MenuCriteria;

public interface MenuDao {
	
	int hasRoleUseMenu(@Param("menuId")String menuId);
	
	List<Menu> queryAdminMenu(MenuCriteria criteria);

	List<Menu> queryUserMenu(MenuCriteria criteria);

	List<Menu> queryAccreditMenu(MenuCriteria criteria);
}