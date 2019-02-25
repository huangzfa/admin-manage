package com.duobei.core.auth.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.duobei.core.auth.domain.Menu;
import com.duobei.dic.ZD;

/**
 * 菜单帮助类
 * @author Hong
 *
 */
public class MenuHelper {

	/**
	 * 顶级菜单id
	 */
	public static final int rootMenuId=1;
	/**
	 * 顶级菜单父id
	 */
	public static final int rootMenuParentId=0;
	
	//菜单排序格式化 开始。。。
	/**
	 * 排序菜单
	 * @param menus
	 * @param parentId
	 * @return
	 */
	public static List<Menu> sortMenu(List<Menu> menus,int parentId){
		List<Menu> resultMenu=new ArrayList<Menu>();
		for (int i = 0; i < menus.size(); i++) {
			Menu umVo=menus.get(i);
			if(ZD.menuOpenType_p.equals(umVo.getMenuOpenType())){
				try {
					umVo.setMenuUrl(URLEncoder.encode(umVo.getMenuUrl(),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
				}
			}
			if (parentId==umVo.getParentId().intValue()) {
				resultMenu.add(umVo);
				sortMenu(menus,umVo);
			}
		}
		return resultMenu;
	}
	public static void sortMenu(List<Menu> menus,Menu menu){
		if (menu.getIsParent()) {
			for (int i = 0; i < menus.size(); i++) {
				Menu umVo=menus.get(i);
				if (menu.getMenuId().intValue()==umVo.getParentId().intValue()) {
					menu.getChilds().add(umVo);
					sortMenu(menus,umVo);
				}
			}
		}
	}
	//菜单排序格式化 结束。。。
	
	/**
	 * 校验菜单类型
	 * @param menuTypeZd
	 * @return
	 */
	public static boolean checkMenuType(String menuTypeZd) {
		return ZD.menuType_m.equals(menuTypeZd)||ZD.menuType_mo.equals(menuTypeZd);
	}
	/**
	 * 校验菜单打开方式
	 * @param menuOpenTypeZd
	 * @return
	 */
	public static boolean checkMenuOpenType(String menuOpenTypeZd) {
		return ZD.menuOpenType_d.equals(menuOpenTypeZd)||ZD.menuOpenType_p.equals(menuOpenTypeZd);
	}
	/**
	 * 校验菜单所属系统类型
	 * @param systemTypeZd
	 * @return
	 */
	public static boolean checkMenuSystemType(String systemTypeZd) {
		return ZD.systemType_sys.equals(systemTypeZd);
	}
}
