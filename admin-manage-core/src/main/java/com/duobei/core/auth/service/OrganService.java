package com.duobei.core.auth.service;

import java.util.List;

import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.domain.Organ;
import com.duobei.core.auth.domain.OrganRule;
import com.duobei.core.auth.domain.OrganType;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.OrganCriteria;
import com.duobei.core.auth.domain.vo.OrganRuleVo;
import com.duobei.core.auth.domain.vo.OrganVo;
import com.duobei.common.exception.TqException;

/**
 * 组织结构服务类
 * @author Hong
 *
 */
public interface OrganService {

	/**
	 * 查询所有组织类型
	 * @return
	 * @throws TqException
	 */
	List<OrganType> queryAllOrganType() throws TqException;
	/**
	 * 查询组织类型
	 * @param organTypeId
	 * @return
	 * @throws TqException 
	 */
	OrganType queryOrganTypeById(Integer organTypeId) throws TqException;
	/**
	 * 添加组织类型
	 * @param organType
	 */
	void addOrganType(OperatorCredential credential,OrganType organType)throws TqException;
	/**
	 * 更新组织类型
	 * @param organType
	 */
	void updateOrganType(OperatorCredential credential,OrganType organType)throws TqException;
	/**
	 * 删除组织类型
	 * @param credential
	 * @param organTypeId
	 * @throws TqException
	 */
	void deleteOrganType(OperatorCredential credential,Integer organTypeId)throws TqException;
	/**
	 * 组织类型是否在使用
	 * @param topOrganId
	 * @param organTypeId
	 * @throws TqException
	 */
	void isUsedForOrganType(Integer organTypeId)throws TqException;
	/**
	 * 是否已经存在组织类型代码
	 * @param organTypeCode
	 * @return
	 */
	boolean hasOrganTypeCode(String organTypeCode);
	/**
	 * 查询组织类型
	 * @param organTypeCode
	 * @return
	 */
	OrganType queryOrganTypeByCode(String organTypeCode)throws TqException;
	
	/**
	 * ==================================================================================
	 */
	/**
	 * 查询所有组织结构规则
	 * @param topOrganId
	 * @return
	 * @throws TqException
	 */
	List<OrganRuleVo> queryAllOrganRule()throws TqException;
	/**
	 * 查询组织结构规则
	 * @param organId
	 * @param organRuleId
	 * @return
	 * @throws TqException
	 */
	OrganRuleVo queryOrganRuleById(Integer organRuleId)throws TqException;
	/**
	 * 添加组织结构规则
	 * @param organRule
	 * @throws TqException
	 */
	void addOrganRule(OperatorCredential credential,OrganRule organRule)throws TqException;
	/**
	 * 更新组织机构规则
	 * @param organRule
	 * @throws TqException
	 */
	void updateOrganRule(OperatorCredential credential,OrganRule organRule)throws TqException;
	/**
	 * 删除组织结构规则
	 * @param credential
	 * @param organRuleId
	 * @throws TqException
	 */
	void deleteOrganRule(OperatorCredential credential, Integer organRuleId)throws TqException;
	/**
	 * 查询组织结构规则
	 * @param organId
	 * @param supOrganTypeId
	 * @param subOrganTypeId
	 * @return
	 * @throws TqException 
	 */
	OrganRule queryOrganRule(Integer supOrganTypeId, Integer subOrganTypeId) throws TqException;
	/**
	 * 校验组织架构规则是否在使用中
	 * @param organId
	 * @param supOrganTypeId
	 * @param subOrganTypeId
	 * @throws TqException
	 */
	void checkUsedForOrganRule(Integer supOrganTypeId, Integer subOrganTypeId) throws TqException;
	/**
	 * 是否存在组织结构规则
	 * @param organId
	 * @param supOrganTypeId
	 * @param subOrganTypeId
	 * @return
	 * @throws TqException 
	 */
	boolean hasOrganRule(Integer supOrganTypeId,Integer subOrganTypeId) throws TqException;
	/**
	 * ==================================================================================
	 */
	/**
	 * 查询组织机构
	 * @param organId
	 * @return
	 * @throws TqException 
	 */
	Organ queryOrganById(Integer organId) throws TqException;
	/**
	 * 查询组织机构
	 * @param organId
	 * @return
	 * @throws TqException 
	 */
	OrganVo queryOrganVoById(Integer organId) throws TqException;
	/**
	 * 查询组织架构
	 * @param topOrganId
	 * @return
	 * @throws TqException 
	 */
	List<Organ> queryAllOrgan() throws TqException;
	/**
	 * 添加组织
	 * @param organ
	 * @throws TqException
	 */
	void addOrgan(OperatorCredential credential,Organ organ) throws TqException;
	/**
	 * 更新组织
	 * @param organ
	 * @throws TqException
	 */
	void updateOrgan(OperatorCredential credential,Organ organ) throws TqException;
	/**
	 * 删除组织-顶级组织只有超级管理员才能删
	 * @param organId
	 * @throws TqException
	 */
	void deleteOrgan(OperatorCredential credential,Integer organId) throws TqException;
	/**
	 * 校验目标组织是否可以添加下级组织，依据规则
	 * @param parentOrganId
	 * @return
	 * @throws TqException
	 */
	boolean isCanAddChildOrganTypeForOrganRule(Integer parentOrganId) throws TqException;
	/**
	 * 查询可以添加的下级组织类型
	 * @param organId
	 * @param parentOrganId
	 * @return
	 * @throws TqException
	 */
	List<OrganType> queryCanAddChildOrganTypeForOrganRule(Integer parentOrganId) throws TqException;
	/**
	 * 根据父组织查询下级组织列表
	 * @param criteria
	 * @return
	 * @throws TqException
	 */
	ListVo<OrganVo> queryOrganListByParent(OrganCriteria criteria) throws TqException;
	/**
	 * 查询组织根据组织编码
	 * @param organCode
	 * @return
	 * @throws TqException 
	 */
	Organ queryOrganByCode(String organCode) throws TqException;
	/**
	 * 更新组织架构是否是父节点
	 * @param organId
	 * @param isParent
	 * @return
	 */
	int updateOrganIsParent(Integer organId,boolean isParent);
	/**
	 * 校验组织是否正在使用，组织下是否有用户
	 * @param organId
	 * @throws TqException
	 */
	void checkUsedForOrgan(Integer organId) throws TqException;
}
