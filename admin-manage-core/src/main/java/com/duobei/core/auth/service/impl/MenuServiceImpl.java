package com.duobei.core.auth.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duobei.common.util.AESCoder;
import com.duobei.core.auth.dao.MenuDao;
import com.duobei.core.auth.dao.mapper.MenuMapper;
import com.duobei.core.auth.domain.Menu;
import com.duobei.core.auth.domain.MenuExample;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.MenuCriteria;
import com.duobei.core.auth.helper.MenuHelper;
import com.duobei.core.auth.service.MenuService;
import com.duobei.dic.ZD;
import com.duobei.common.exception.TqException;

@Service("MenuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private MenuDao menuDao;
	
	@Override
	public boolean beanValidator(Menu menu) throws TqException{
		if (menu==null) {
			throw new TqException("菜单数据不能为空");
		}
		if (menu.getParentId()==null) {
			throw new TqException("上级菜单不能为空");
		}
		if (StringUtils.isBlank(menu.getMenuName())) {
			throw new TqException("菜单名称不能为空");
		}
		
		if (StringUtils.isBlank(menu.getMenuType())) {
			throw new TqException("菜单类型不能为空");
		}
		
		if (!MenuHelper.checkMenuType(menu.getMenuType())){
			throw new TqException("菜单类型错误");
		}
		if (StringUtils.isBlank(menu.getMenuTag())) {
			menu.setMenuTag(String.valueOf(System.currentTimeMillis()));
//			throw new Ms56Exception("菜单标记不能为空");
		}
		if (StringUtils.isBlank(menu.getState())) {
			throw new TqException("菜单状态不能为空");
		}
		if (StringUtils.isBlank(menu.getMenuOpenType())) {
			throw new TqException("菜单打开方式不能为空");
		}
		if (!MenuHelper.checkMenuOpenType(menu.getMenuOpenType())){
			throw new TqException("菜单打开方式错误");
		}
		if (menu.getMenuSort()==null) {
			throw new TqException("菜单排序不能为空");
		}
		if (StringUtils.isBlank(menu.getSystemType())) {
			throw new TqException("菜单所属系统类型不能为空");
		}
		if (!MenuHelper.checkMenuSystemType(menu.getSystemType())){
			throw new TqException("菜单所属系统类型错误");
		}
		return true;
	}
	
	@Override
	public Menu queryMenuByMenuId(Integer menuId) {
		return menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public List<Menu> queryAllMenu(String menuType) {
		MenuExample example=new MenuExample();
		if (StringUtils.isNotBlank(menuType)) {
			example.createCriteria().andMenuTypeEqualTo(menuType);
		}
		example.setOrderByClause("m.menu_level,m.menu_sort,m.add_time");
		return menuMapper.selectByExample(example);
	}

	@Override
	public List<Menu> queryMenuByParentId(Integer parentId) {
		MenuExample example=new MenuExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		example.setOrderByClause("menu_sort ASC,add_time DESC");
		return menuMapper.selectByExample(example);
	}
	
	@Override
	public int updateMenuIsParent(Integer menuId, boolean isParent) {
		Menu updateMenu=new Menu();
		updateMenu.setMenuId(menuId);
		updateMenu.setIsParent(isParent);
		return menuMapper.updateByPrimaryKeySelective(updateMenu);
	}
	@Override
	public boolean hasRoleUseMenu(Integer menuId) {
		return menuDao.hasRoleUseMenu("#"+String.valueOf(menuId)+"#")>0;
	}
	@Override
	public boolean hasChildMenu(Integer menuId) {
		MenuExample example=new MenuExample();
		example.createCriteria().andParentIdEqualTo(menuId);
		return menuMapper.countByExample(example)>0;
	}
	
	@Override
	public Menu queryMenuByMenuTag(String menuTag){
		MenuExample example=new MenuExample();
		example.createCriteria().andMenuTagEqualTo(menuTag);
		List<Menu> ms=menuMapper.selectByExample(example);
		if (ms!=null&&ms.size()>0) {
			return ms.get(0);
		}
		return null;
	}
	
	@Override
	public boolean hasMenuTag(String menuTag){
		MenuExample example=new MenuExample();
		example.createCriteria().andMenuTagEqualTo(menuTag);
		return menuMapper.countByExample(example)>0;
	}
	@Override
	public int deleteMenuAndChild(Integer menuId) {
		MenuExample example=new MenuExample();
		example.createCriteria().andPathLike("%#"+String.valueOf(menuId)+"#%");
		return menuMapper.deleteByExample(example);
	}

	@Override
	public Menu addMenu(OperatorCredential credential,Menu menu) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		//基础校验
		beanValidator(menu);
		//校验菜单标记是否已存在
		if (hasMenuTag(menu.getMenuTag().trim())) {
			throw new TqException("菜单标记已存在");
		}
		menu.setIsParent(false);
		Menu parentMenu=menuMapper.selectByPrimaryKey(menu.getParentId());
		if (parentMenu==null) {
			throw new TqException("上级菜单不存在");
		}
		if(parentMenu.getMenuLevel().intValue()==3&&ZD.menuType_m.equals(menu.getMenuType())){
			throw new TqException("最多只能添加三级菜单");
		}
		if (ZD.menuType_mo.equals(parentMenu.getMenuType())) {
			throw new TqException("权限项下不能添加子项");
		}
		menu.setMenuLevel(parentMenu.getMenuLevel().intValue()+1);
		//如果是加密链接，加密处理
		if(ZD.menuOpenType_d.equals(menu.getMenuOpenType())&&menu.getMenuLevel()==3&&ZD.menuType_m.equals(menu.getMenuType())){
			menu.setMenuUrl("encrypt"+AESCoder.encryptAES128_Base64(menu.getMenuUrl()));
		}
		menu.setAddOperatorId(credential.getOpId());
		menu.setAddTime(new Date());
		if (1==menuMapper.insertSelective(menu)) {
			Menu updateMenu=new Menu();
			updateMenu.setMenuId(menu.getMenuId());
			if (parentMenu.getIsParent()==null||!parentMenu.getIsParent().booleanValue()) {
				//更新父菜单是否是父菜单状态
				updateMenuIsParent(parentMenu.getMenuId(),true);
			}
			updateMenu.setPath(parentMenu.getPath()+String.valueOf(menu.getMenuId())+"#");
			menuMapper.updateByPrimaryKeySelective(updateMenu);
			return menu;
		}else{
			throw new TqException("添加菜单失败");
		}
	}

	@Override
	public void updateMenu(OperatorCredential credential,Menu menu) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		//基础校验
		beanValidator(menu);
		if (menu.getMenuId()==null) {
			throw new TqException("菜单id不能为空");
		}
		Menu oldMenu=menuMapper.selectByPrimaryKey(menu.getMenuId());
		if (oldMenu==null) {
			throw new TqException("菜单不存在");
		}
		if(!oldMenu.getMenuType().equals(menu.getMenuType())){
			throw new TqException("菜单类型不能修改");
		}
		Menu tagMenu=queryMenuByMenuTag(menu.getMenuTag());
		if (tagMenu!=null&&!tagMenu.getMenuId().equals(menu.getMenuId())) {
			throw new TqException("菜单标记已存在");
		}
		Menu parentMenu=menuMapper.selectByPrimaryKey(menu.getParentId());
		if (parentMenu==null) {
			throw new TqException("上级菜单不存在");
		}
		if(parentMenu.getMenuLevel().intValue()==3&&ZD.menuType_m.equals(menu.getMenuType())){
			throw new TqException("最多只能添加三级菜单");
		}
		if (ZD.menuType_mo.equals(parentMenu.getMenuType())) {
			throw new TqException("权限项下不能添加子项");
		}
		if (!oldMenu.getParentId().equals(menu.getParentId())) {
			if (hasChildMenu(menu.getMenuId())) {
				throw new TqException("此菜单有子菜单，不能修改上级菜单");
			}
			//更新父菜单是否是父菜单状态
			updateMenuIsParent(menu.getParentId(),true);
			menu.setMenuLevel(parentMenu.getMenuLevel().intValue()+1);
			menu.setPath(parentMenu.getPath()+String.valueOf(menu.getMenuId())+"#");
			
			oldMenu.setMenuLevel(menu.getMenuLevel());
		}
		menu.setModifyOperatorId(credential.getOpId());
		menu.setModifyTime(new Date());
		//校验加密链接
		if(!oldMenu.getMenuOpenType().equals(menu.getMenuOpenType())){
			if(ZD.menuOpenType_d.equals(oldMenu.getMenuOpenType())&&oldMenu.getMenuLevel()==3&&ZD.menuType_m.equals(menu.getMenuType())){
				throw new TqException("加密的链接不可修改");
			}
			if(oldMenu.getMenuLevel()==3&&ZD.menuType_m.equals(menu.getMenuType())){
				if(menu.getMenuUrl().indexOf("encrypt")!=0){
					menu.setMenuUrl("encrypt"+AESCoder.encryptAES128_Base64(menu.getMenuUrl()));
				}
			}
		}else{
			if(ZD.menuOpenType_d.equals(oldMenu.getMenuOpenType())&&oldMenu.getMenuLevel()==3&&ZD.menuType_m.equals(menu.getMenuType())){
				//如果url未加密，表示更新，重新加密
			/*	if(menu.getMenuUrl().indexOf("encrypt")!=0){
					menu.setMenuUrl("encrypt"+AESCoder.encryptAES128_Base64(menu.getMenuUrl()));
				}*/
			}
		}
		if (1!=menuMapper.updateByPrimaryKeySelective(menu)) {
			throw new TqException("更新数据库失败");
		}
	}

	@Override
	public void deleteMenu(Integer menuId) throws TqException {
		if(menuId==null){
			throw new TqException("菜单id不能为空");
		}
		if (menuId==0) {
			throw new TqException("顶级菜单不能删除");
		}
		Menu menu=menuMapper.selectByPrimaryKey(menuId);
		if (menu==null) {
			throw new TqException("菜单不存在");
		}
		if (hasChildMenu(menuId)) {
			throw new TqException("必须先删除下级菜单");
		}
		//校验菜单是否在使用
		if (hasRoleUseMenu(menuId)) {
			throw new TqException("有角色正在使用此菜单");
		}
		if (menuMapper.deleteByPrimaryKey(menuId)>0) {
			if (!hasChildMenu(menu.getParentId())) {
				updateMenuIsParent(menu.getParentId(),false);
			}
		}else{
			throw new TqException("更新数据库失败");
		}	
	}

	@Override
	public List<Menu> queryAccreditMenu(OperatorCredential credential) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if(credential.isSuperAdmin()){
			return queryAllMenu(null);
		}else{
			MenuCriteria criteria=new MenuCriteria();
			criteria.setOpId(credential.getOpId());
			criteria.setStateZd(ZD.state_open);
			return menuDao.queryAccreditMenu(criteria);
		}
	}
	//加密链接
	@Override
	public String encryptMenuUrl(String menuUrl){
		return "encrypt"+AESCoder.encryptAES128_Base64(menuUrl);
	}
	//解密链接
	@Override
	public String decryptMenuUrl(String menuUrl){
		String enMenuUrl=menuUrl.substring(7);
		return AESCoder.decryptAES128_Base64(enMenuUrl);
	}
	
	public static void main(String[] args) {
		MenuServiceImpl menuServiceImpl=new MenuServiceImpl();
		String content = "https://das.base.shuju.aliyun.com/token3rd/dashboard/view/pc.htm?pageId=4485a694-6dda-44e9-bcca-43ddce6618e9&accessToken=1b9cc47f2c3b598b501c0bb61193d39c";  
        // 加密  
        System.out.println("加密前：" + content);  
        String encryptResult = menuServiceImpl.encryptMenuUrl(content);
        System.out.println("加密并base64后：" + encryptResult);  
        System.out.println(encryptResult.indexOf("encrypt"));
        // 解密  
        String decryptResult = menuServiceImpl.decryptMenuUrl(encryptResult);
        System.out.println("解密后：" + decryptResult);  
        
        System.out.println(content.equals(decryptResult));
	}
}
