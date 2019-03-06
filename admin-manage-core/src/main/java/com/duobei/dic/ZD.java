package com.duobei.dic;


public interface ZD {
	
	/**
	 *  链接类型
	 */
    public String redirectType = "redirectType";
	
	/**
	 *  app升级状态
	 */
    public String appUpgradeState = "appUpgradeState";
	
	/**
	 *  终端系统类型
	 */
    public String osType = "osType";
	
	/**
	 *  通知业务类型
	 */
    public String notifyBizType = "notifyBizType";
	
	/**
	 *  菜单类型
	 */
    public String aPMenuType = "aPMenuType";
	
	/**
	 *  审核状态
	 */
    public String aPMenuExamState = "aPMenuExamState";
	
	/**
	 *  APP是否强制升级
	 */
    public String appUpgradeIsForce = "appUpgradeIsForce";
	
	/**
	 *  认证类型
	 */
    public String authType = "authType";
	
	/**
	 *  渠道状态
	 */
    public String channelStatus = "channelStatus";
	
	/**
	 *  推广渠道审核状态
	 */
    public String channelApproveType = "channelApproveType";
	
	/**
	 *  渠道类型
	 */
    public String channelType = "channelType";
	
	/**
	 *  轮播图类型
	 */
    public String bannerType = "bannerType";
	
	/**
	 *  登录类型
	 */
    public String loginType = "loginType";
	
	/**
	 *  银行状态
	 */
    public String bankState = "bankState";
	
	/**
	 *  渠道样式模板
	 */
    public String channelStylebtTempt = "channelStylebtTempt";
	
	/**
	 *  银行维护状态
	 */
    public String bankMintainState = "bankMaintainState";
	
	/**
	 *  APP升级范围
	 */
    public String upgradeRangeState = "upgradeRangeState";
	
	/**
	 *  用户状态
	 */
    public String profileState = "profileState";
	
	/**
	 *  菜单类型
	 */
    public String menuType = "menuType";
	
	/**
	 *  性别
	 */
    public String gender = "gender";
	
	/**
	 *  数据状态
	 */
    public String dataState = "dataState";
	
	/**
	 *  状态
	 */
    public String state = "state";
	
	/**
	 *  优惠券类型
	 */
    public String couponType = "couponType";
	
	/**
	 *  系统类型
	 */
    public String systemType = "systemType";
	
	/**
	 *  菜单打开方式
	 */
    public String menuOpenType = "menuOpenType";
	
	/**
	 *  菜单类型-权限项- 
	 */
    public String menuType_mo = "mo";
	
	/**
	 *  菜单类型-菜单- 
	 */
    public String menuType_m = "m";
	
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
	 *  渠道类型-H5渠道- 
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
	 *  渠道类型-其他- 
	 */
    public int channelType_other = 3;
	
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
	 *  链接类型-商品- 
	 */
    public String redirectType_shop = "shop";
	
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
	 *  登录类型-多贝- 
	 */
    public String platform_duobei = "duobei";
	
	/**
	 *  登录类型-登录- 
	 */
    public int loginType_in = 1;
	
	/**
	 *  登录类型-登出- 
	 */
    public int loginType_out = 2;
	
	/**
	 *  登录类型-修改密码- 
	 */
    public int notifyBizType_update = 4;
	
	/**
	 *  轮播图类型-借钱页顶部轮播图- 
	 */
    public String bannerType_borrowTop = "borrowTop";
	
	/**
	 *  轮播图类型-额度页面banner- 
	 */
    public String bannerType_quota = "quota";
	
	/**
	 *  轮播图类型-注册页底部广告位- 
	 */
    public String bannerType_regBottom = "regBottom";
	
	/**
	 *  轮播图类型-强风控审核成功页- 
	 */
    public String bannerType_strongRiskSucess = "strongRiskSucess";
	
	/**
	 *  轮播图类型-银行卡还款成功页- 
	 */
    public String bannerType_bankRepaySuccess = "bankRepaySuccess";
	
	/**
	 *  轮播图类型-借贷超市轮播- 
	 */
    public String bannerType_dcMarket = "dcMarket";
	
	/**
	 *  轮播图类型-借贷超市中部广告位- 
	 */
    public String bannerType_dcCenter = "dcCenter";
	
	/**
	 *  轮播图类型-借钱页底部贷超- 
	 */
    public String bannerType_borrowBottom = "borrowBottom";
	
	/**
	 *  推广渠道审核状态-审核完成- 
	 */
    public int channelApproveType_finsh = 1;
	
	/**
	 *  推广渠道审核状态-渠道审核中- 
	 */
    public int channelApproveType_ing = 0;
	
	/**
	 *  禁用-启用- 
	 */
    public int channelStatus_yes  = 1;
	
	/**
	 *  禁用-禁用- 
	 */
    public int channelStatus_no = 0;
}