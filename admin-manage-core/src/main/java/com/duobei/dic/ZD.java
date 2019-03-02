package com.duobei.dic;


public interface ZD {
	
	/**
	 *  链接类型
	 */
    public String redirectType = "redirectType";
	
	/**
	 *  银行状态
	 */
    public String bankState = "bankState";
	
	/**
	 *  终端系统类型
	 */
    public String osType = "osType";
	
	/**
	 *  通知业务类型
	 */
    public String notifyBizType = "notifyBizType";
	
	/**
	 *  银行维护状态
	 */
    public String bankMintainState = "bankMaintainState";
	
	/**
	 *  菜单类型
	 */
    public String aPMenuType = "aPMenuType";
	
	/**
	 *  登录类型
	 */
    public String loginType = "loginType";
	
	/**
	 *  优惠券类型
	 */
    public String couponType = "couponType";
	
	/**
	 *  审核状态
	 */
    public String aPMenuExamState = "aPMenuExamState";
	
	/**
	 *  渠道样式模板
	 */
    public String channelStylebtTempt = "channelStylebtTempt";
	
	/**
	 *  认证类型
	 */
    public String authType = "authType";
	
	/**
	 *  渠道类型
	 */
    public String channelType = "channelType";
	
	/**
	 *  APP升级范围
	 */
    public String upgradeRangeState = "upgradeRangeState";
	
	/**
	 *  app升级状态
	 */
    public String appUpgradeState = "appUpgradeState";
	
	/**
	 *  APP是否强制升级
	 */
    public String appUpgradeIsForce = "appUpgradeIsForce";
	
	/**
	 *  菜单类型
	 */
    public String menuType = "menuType";
	
	/**
	 *  菜单打开方式
	 */
    public String menuOpenType = "menuOpenType";
	
	/**
	 *  系统类型
	 */
    public String systemType = "systemType";
	
	/**
	 *  状态
	 */
    public String state = "state";
	
	/**
	 *  用户状态
	 */
    public String profileState = "profileState";
	
	/**
	 *  性别
	 */
    public String gender = "gender";
	
	/**
	 *  数据状态
	 */
    public String dataState = "dataState";
	
	/**
	 *  菜单类型-菜单- 
	 */
    public String menuType_m = "m";
	
	/**
	 *  菜单类型-权限项- 
	 */
    public String menuType_mo = "mo";
	
	/**
	 *  菜单打开方式-默认- 
	 */
    public String menuOpenType_d = "d";
	
	/**
	 *  菜单打开方式-加密链接- 
	 */
    public String menuOpenType_p = "p";
	
	/**
	 *  系统类型-运营平台- 
	 */
    public String systemType_sys = "sys";
	
	/**
	 *  状态-启用- 
	 */
    public String state_open = "10";
	
	/**
	 *  状态-停用- 
	 */
    public String state_close = "20";
	
	/**
	 *  性别-男- 
	 */
    public int gender_men = 1;
	
	/**
	 *  性别-女- 
	 */
    public int gender_wom = 0;
	
	/**
	 *  性别-未知- 
	 */
    public int gender_un = 2;
	
	/**
	 *  数据状态-有效- 
	 */
    public int dataState_valid = 1;
	
	/**
	 *  数据状态-无效- 
	 */
    public int dataState_invalid = 0;
	
	/**
	 *  数据状态-异常- 
	 */
    public int dataState_exce = 90;
	
	/**
	 *  用户状态-正常- 
	 */
    public int profileState_o = 1;
	
	/**
	 *  用户状态-停用- 
	 */
    public int profileState_c = 2;
	
	/**
	 *  终端系统类型-苹果- 
	 */
    public int osType_ios = 1;
	
	/**
	 *  终端系统类型-安卓- 
	 */
    public int osType_an = 2;
	
	/**
	 *  通知业务类型-订单状态通知- 
	 */
    public int notifyBizType_order = 2;
	
	/**
	 *  通知业务类型-短信验证码- 
	 */
    public int notifyBizType_VerifyCode = 1;
	
	/**
	 *  通知业务类型-登录短信验证码- 
	 */
    public int notifyBizType_login = 3;
	
	/**
	 *  菜单类型-h5- 
	 */
    public String aPMenuType_h5 = "2";
	
	/**
	 *  菜单类型-原生- 
	 */
    public String aPMenuType_prot = "1";
	
	/**
	 *  审核状态-审核- 
	 */
    public String aPMenuExamState_audit = "1";
	
	/**
	 *  审核状态-未审核- 
	 */
    public String aPMenuExamState_unaudit = "0";
	
	/**
	 *  认证类型-基础认证- 
	 */
    public String authType_basic = "1";
	
	/**
	 *  认证类型-补充认证- 
	 */
    public String authType_supply = "2";
	
	/**
	 *  渠道类型-其他- 
	 */
    public int channelType_other = 3;
	
	/**
	 *  渠道类型-渠道- 
	 */
    public int channelType_h5 = 0;
	
	/**
	 *  渠道类型-应用市场- 
	 */
    public int channelType_app = 1;
	
	/**
	 *  渠道类型-短信- 
	 */
    public int channelType_sms = 2;
	
	/**
	 *  银行状态-有效- 
	 */
    public int bankState_valid = 1;
	
	/**
	 *  银行状态-失效- 
	 */
    public int bankState_invalid = 0;
	
	/**
	 *  银行维护状态-未维护- 
	 */
    public int bankMaintainState_unmaint = 1;
	
	/**
	 *  银行维护状态-维护中- 
	 */
    public int bankMaintainState_maint = 0;
	
	/**
	 *  app升级状态-新建- 
	 */
    public String appUpgradeState_new = "0";
	
	/**
	 *  app升级状态-开启- 
	 */
    public String appUpgradeState_open = "1";
	
	/**
	 *  app升级状态-关闭- 
	 */
    public String appUpgradeState_close = "-1";
	
	/**
	 *  APP升级范围-所有版本- 
	 */
    public String upgradeRangeState_all = "0";
	
	/**
	 *  APP升级范围-部分版本- 
	 */
    public String upgradeRangeState_part = "1";
	
	/**
	 *  APP是否强制升级-是- 
	 */
    public String appUpgradeIsForce_yes = "1";
	
	/**
	 *  APP是否强制升级-否- 
	 */
    public String appUpgradeIsForce_no = "0";
	
	/**
	 *  渠道样式模板-方框模板- 
	 */
    public int channelStylebtTempt_square = 1;
	
	/**
	 *  渠道样式模板-原型模板- 
	 */
    public int channelStylebtTempt_circul = 2;
	
	/**
	 *  链接类型-URL- 
	 */
    public String redirectType_url = "url";
	
	/**
	 *  链接类型-无连接- 
	 */
    public String redirectType_no = "no";
	
	/**
	 *  优惠券类型-借款券- 
	 */
    public int couponType_jkq = 1;
	
	/**
	 *  优惠券类型-还款券- 
	 */
    public int couponType_hkq = 2;
	
	/**
	 *  登录类型-登录- 
	 */
    public int loginType_in = 1;

	/**
	 *  登录类型-多贝-
	 */
    public String platform_duobei = "duobei";
	
	/**
	 *  登录类型-登出- 
	 */
    public int loginType_out = 2;
	
	/**
	 *  登录类型-修改密码- 
	 */
    public int notifyBizType_update = 4;
}