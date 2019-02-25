package com.duobei.dic;

/**
 * 字典帮助类
 * @author Hong
 *
 */
public class ZDHelper {
	/**
	 * 校验性别是否合法
	 * @param state
	 * @return
	 */
	public static boolean validateGender(int g){
		return ZD.gender_wom==g||ZD.gender_men==g||ZD.gender_un==g;
	}
	/**
	 * 校验状态是否合法
	 * @param state
	 * @return
	 */
	public static boolean validateState(String state){
		return (ZD.state_open.equals(state)||ZD.state_close.equals(state));
	}
	/**
	 * 校验终端类型
	 * @param osType
	 * @return
	 */
	public static boolean validateOsType(int osType){
		return (ZD.osType_ios==osType||ZD.osType_an==osType);
	}
	/**
	 * 校验支付类型
	 * @param payType
	 * @return
	 */
	public static boolean validatePayType(Integer payType) {
		return (ZD.payType_zfb==payType||ZD.payType_wx==payType);
	}
	/**
	 * 校验用户状态
	 * @param profileState
	 * @return
	 */
	public static boolean validateProfileState(Integer profileState) {
		return (ZD.profileState_o==profileState||ZD.profileState_c==profileState);
	}
}
