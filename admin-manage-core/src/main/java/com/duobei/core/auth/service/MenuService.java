package com.duobei.core.auth.service;

import java.util.List;

import com.duobei.core.auth.domain.Menu;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.common.exception.TqException;

public interface MenuService {
	
	/**
	 * 验证菜单
	 * @param menu
	 * @return
	 * @throws TqException
	 */
	boolean beanValidator(Menu menu) throws TqException;
	/**
	 * 根据菜单id查询菜单
	 * @param menuId
	 * @return
	 */
	Menu queryMenuByMenuId(Integer menuId);
	/**
	 * 查询所有菜单
	 * @param menuType 菜单类型，null为所有类型
	 * @return
	 */
	List<Menu> queryAllMenu(String menuType);
	/**
	 * 查询某个菜单节点下的下一级子菜单
	 * @param parentId
	 * @return
	 */
	List<Menu> queryMenuByParentId(Integer parentId);
	/**
	 * 更新菜单是否为父菜单
	 * @param menuId
	 * @param isParent
	 * @return
	 */
	int updateMenuIsParent(Integer menuId, boolean isParent);
	/**
	 * 是否有角色使用此菜单
	 * @param menuId
	 * @return
	 */
	boolean hasRoleUseMenu(Integer menuId);
	/**
	 * 是否有子集
	 * @param menuId
	 * @return
	 */
	boolean hasChildMenu(Integer menuId);
	/**
	 * 查询菜单-菜单标记
	 * @param menuTag
	 * @return
	 */
	Menu queryMenuByMenuTag(String menuTag);
	/**
	 * 是否有此菜单标记
	 * @param menuTag
	 * @return
	 */
	boolean hasMenuTag(String menuTag);
	/**
	 * 删除某一菜单及其所有子菜单
	 * @param menuId
	 * @return
	 */
	int deleteMenuAndChild(Integer menuId);
	/**
	 * 添加菜单
	 * @param menu
	 * @throws TqException 
	 */
	Menu addMenu(OperatorCredential credential,Menu menu) throws TqException;
	/**
	 * 更新菜单
	 * @param menu
	 * @return
	 * @throws TqException
	 */
	void updateMenu(OperatorCredential credential,Menu menu) throws TqException;
	/**
	 * 删除菜单
	 * @param menuId
	 */
	void deleteMenu(Integer menuId) throws TqException;
	/**
	 * 获取当前用户可以授权的权限菜单
	 * @param credential
	 * @return
	 * @throws TqException 
	 */
	List<Menu> queryAccreditMenu(OperatorCredential credential) throws TqException;
	String encryptMenuUrl(String menuUrl);
	String decryptMenuUrl(String menuUrl);

}
