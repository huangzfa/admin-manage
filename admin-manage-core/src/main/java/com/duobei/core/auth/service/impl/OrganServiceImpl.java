package com.duobei.core.auth.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duobei.common.config.Global;
import com.duobei.common.vo.ListVo;
import com.duobei.core.auth.dao.OrganDao;
import com.duobei.core.auth.dao.OrganRuleDao;
import com.duobei.core.auth.dao.OrganTypeDao;
import com.duobei.core.auth.dao.mapper.OperatorMapper;
import com.duobei.core.auth.dao.mapper.OrganMapper;
import com.duobei.core.auth.dao.mapper.OrganRuleMapper;
import com.duobei.core.auth.dao.mapper.OrganTypeMapper;
import com.duobei.core.auth.domain.OperatorExample;
import com.duobei.core.auth.domain.Organ;
import com.duobei.core.auth.domain.OrganExample;
import com.duobei.core.auth.domain.OrganRule;
import com.duobei.core.auth.domain.OrganRuleExample;
import com.duobei.core.auth.domain.OrganType;
import com.duobei.core.auth.domain.OrganTypeExample;
import com.duobei.core.auth.domain.credential.OperatorCredential;
import com.duobei.core.auth.domain.criteria.OrganCriteria;
import com.duobei.core.auth.domain.vo.OrganRuleVo;
import com.duobei.core.auth.domain.vo.OrganVo;
import com.duobei.core.auth.service.OrganService;
import com.duobei.common.exception.TqException;

@Service("OrganService")
public class OrganServiceImpl implements OrganService {
	@Autowired
	private OrganTypeMapper organTypeMapper;
	@Autowired
	private OrganTypeDao organTypeDao;
	@Autowired
	private OrganMapper organMapper;
	@Autowired
	private OrganDao organDao;
	@Autowired
	private OrganRuleMapper organRuleMapper;
	@Autowired
	private OrganRuleDao organRuleDao;
	@Autowired
	private OperatorMapper operatorMapper;
	
	@Override
	public List<OrganType> queryAllOrganType() throws TqException {
		return organTypeMapper.selectByExample(null);
	}
	@Override
	public OrganType queryOrganTypeById(Integer organTypeId) throws TqException {
		if (organTypeId==null) {
			throw new TqException("组织类型id不能为空");
		}
		return organTypeMapper.selectByPrimaryKey(organTypeId);
	}
	@Override
	public void addOrganType(OperatorCredential credential,OrganType organType) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organType==null) {
			throw new TqException("组织类型数据不能为空");
		}
		//基础校验
		beanValidator(organType);
		//校验组织类型代码是否已存在
		if (hasOrganTypeCode(organType.getOrganTypeCode().trim())) {
			throw new TqException("组织类型代码已存在");
		}
		organType.setIsSystem(false);
		organType.setAddTime(new Date());
		if (1!=organTypeMapper.insertSelective(organType)) {
			throw new TqException("组织类型入库失败");
		}
	}
	
	@Override
	public void updateOrganType(OperatorCredential credential,OrganType organType) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organType==null) {
			throw new TqException("组织类型数据不能为空");
		}
		//基础校验
		beanValidator(organType);
		
		OrganType oldOrganType=queryOrganTypeById(organType.getOrganTypeId());
		if (oldOrganType==null) {
			throw new TqException("组织类型不存在");
		}
		//系统数据必须是管理员才能修改
		if (oldOrganType.getIsSystem()&&!credential.isSuperAdmin()) {
			throw new TqException("此组织类型是系统数据，只有管理员才能修改");
		}
		OrganType codeOrganType=queryOrganTypeByCode(organType.getOrganTypeCode());
		if (codeOrganType!=null&&!codeOrganType.getOrganTypeId().equals(organType.getOrganTypeId())) {
			throw new TqException("组织类型代码已存在");
		}
		organType.setModifyTime(new Date());
		if (1!=organTypeMapper.updateByPrimaryKeySelective(organType)) {
			throw new TqException("更新数据库失败");
		}
	}
	@Override
	public void deleteOrganType(OperatorCredential credential,Integer organTypeId) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organTypeId==null) {
			throw new TqException("组织类型id不能为空");
		}
		OrganType organType=queryOrganTypeById(organTypeId);
		if (organType==null) {
			throw new TqException("组织类型不存在");
		}
		//系统数据必须是管理员才能删除
		if (organType.getIsSystem()&&!credential.isSuperAdmin()) {
			throw new TqException("此组织类型是系统数据，只有管理员才能删除");
		}
		//校验组织类型是否在使用
		isUsedForOrganType(organTypeId);
		
		if (1!=organTypeMapper.deleteByPrimaryKey(organTypeId)) {
			throw new TqException("更新数据库失败");
		}	
	}
	
	@Override
	public void isUsedForOrganType(Integer organTypeId) throws TqException {
		//校验组织规则是否在使用
		OrganRuleExample organRuleExample=new OrganRuleExample();
		organRuleExample.createCriteria().andSupOrganTypeIdEqualTo(organTypeId);
		organRuleExample.or().andSubOrganTypeIdEqualTo(organTypeId);
		if (organRuleMapper.countByExample(organRuleExample)>0) {
			throw new TqException("组织规则正在使用此组织类型");
		}
		//校验组织机构是否在使用
		OrganExample example=new OrganExample();
		example.createCriteria().andOrganTypeIdEqualTo(organTypeId);
		if (organMapper.countByExample(example)>0) {
			throw new TqException("组织机构正在使用此组织类型");
		}
	}
	
	@Override
	public boolean hasOrganTypeCode(String organTypeCode) {
		OrganTypeExample example=new OrganTypeExample();
		example.createCriteria().andOrganTypeCodeEqualTo(organTypeCode);
		return organTypeMapper.countByExample(example)>0;
	}
	
	@Override
	public OrganType queryOrganTypeByCode(String organTypeCode) throws TqException{
		OrganTypeExample example=new OrganTypeExample();
		example.createCriteria().andOrganTypeCodeEqualTo(organTypeCode);
		List<OrganType> list=organTypeMapper.selectByExample(example);
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	//验证组织类型
	private boolean beanValidator(OrganType organType) throws TqException{
		if (organType==null) {
			throw new TqException("组织类型数据不能为空");
		}
		if (StringUtils.isBlank(organType.getOrganTypeCode())) {
			throw new TqException("组织类型代码不能为空");
		}
		if (StringUtils.isBlank(organType.getOrganTypeName())) {
			throw new TqException("组织类型名称不能为空");
		}
		return true;
	}
	/**
	 * ==================================================================================
	 */
	@Override
	public List<OrganRuleVo> queryAllOrganRule()throws TqException {
		return organRuleDao.queryAllOrganRule();
	}
	@Override
	public OrganRuleVo queryOrganRuleById(Integer organRuleId)throws TqException {
		if (organRuleId==null) {
			throw new TqException("组织规则id不能为空");
		}
		OrganCriteria criteria=new OrganCriteria();
		criteria.setOrganRuleId(organRuleId);
		return organRuleDao.queryOrganRuleById(criteria);
	}
	@Override
	public void addOrganRule(OperatorCredential credential,OrganRule organRule) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organRule==null) {
			throw new TqException("组织规则数据不能为空");
		}
		//基础校验
		beanValidator(organRule);
		
		//校验组织规则是否已存在
		if (hasOrganRule(organRule.getSupOrganTypeId(),organRule.getSubOrganTypeId())) {
			throw new TqException("组织规则已存在");
		}
		organRule.setIsSystem(false);
		organRule.setAddTime(new Date());
		if (1!=organRuleMapper.insertSelective(organRule)) {
			throw new TqException("组织规则入库失败");
		}
		
	}
	@Override
	public void updateOrganRule(OperatorCredential credential,OrganRule organRule) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organRule==null) {
			throw new TqException("组织规则数据不能为空");
		}
		//基础校验
		beanValidator(organRule);
		OrganRuleVo oldOrganRule=queryOrganRuleById(organRule.getOrganRuleId());
		if (oldOrganRule==null) {
			throw new TqException("组织规则不存在");
		}
		//系统数据必须是管理员才能修改
		if (oldOrganRule.getIsSystem()&&!credential.isSuperAdmin()) {
			throw new TqException("此组织规则是系统数据，只有管理员才能修改");
		}
		//如果修改的规则与原规则不一致
		if (!oldOrganRule.getSupOrganTypeId().equals(organRule.getSupOrganTypeId())
				||!oldOrganRule.getSubOrganTypeId().equals(organRule.getSubOrganTypeId())) {
			//校验规则是否正在使用
			checkUsedForOrganRule(oldOrganRule.getSupOrganTypeId(), oldOrganRule.getSubOrganTypeId());
			//上级组织类型
			OrganType sup=queryOrganTypeById(oldOrganRule.getSupOrganTypeId());
			if (sup==null) {
				throw new TqException("上级组织类型不存在");
			}
			//下级组织类型
			OrganType sub=queryOrganTypeById(oldOrganRule.getSubOrganTypeId());
			if (sub==null) {
				throw new TqException("下级组织类型不存在");
			}
			//校验修改的规则是否已存在
			OrganRule otherOrganRule=queryOrganRule(organRule.getSupOrganTypeId(),organRule.getSubOrganTypeId());
			if (otherOrganRule!=null&&!otherOrganRule.getOrganRuleId().equals(organRule.getOrganRuleId())) {
				throw new TqException("组织规则已存在");
			}
		}
		
		organRule.setModifyTime(new Date());
		if (1!=organRuleMapper.updateByPrimaryKeySelective(organRule)) {
			throw new TqException("更新数据库失败");
		}
		
	}
	@Override
	public void deleteOrganRule(OperatorCredential credential, Integer organRuleId)
			throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organRuleId==null) {
			throw new TqException("组织规则id不能为空");
		}
		OrganRuleVo organRule=queryOrganRuleById(organRuleId);
		if (organRule==null) {
			throw new TqException("组织规则不存在");
		}
		//系统数据必须是管理员才能修改
		if (organRule.getIsSystem()&&!credential.isSuperAdmin()) {
			throw new TqException("此组织规则是系统数据，只有管理员才能删除");
		}
		//校验规则是否正在使用
		checkUsedForOrganRule(organRule.getSupOrganTypeId(), organRule.getSubOrganTypeId());
		
		if (1!=organRuleMapper.deleteByPrimaryKey(organRuleId)) {
			throw new TqException("更新数据库失败");
		}
	}
	@Override
	public OrganRule queryOrganRule(Integer supOrganTypeId,Integer subOrganTypeId) throws TqException {
		OrganRuleExample example=new OrganRuleExample();
		example.createCriteria().andSupOrganTypeIdEqualTo(supOrganTypeId)
		.andSubOrganTypeIdEqualTo(subOrganTypeId);
		List<OrganRule> list=organRuleMapper.selectByExample(example);
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public void checkUsedForOrganRule(Integer supOrganTypeId,Integer subOrganTypeId) throws TqException {
		//校验组织机构是否在使用
		OrganExample example=new OrganExample();
		example.createCriteria().andParentOrganTypeIdEqualTo(supOrganTypeId)
		.andOrganTypeIdEqualTo(subOrganTypeId);
		if (organMapper.countByExample(example)>0) {
			throw new TqException("此组织规则正在使用中");
		}
	}
	@Override
	public boolean hasOrganRule(Integer supOrganTypeId,Integer subOrganTypeId) throws TqException {
		OrganRuleExample example=new OrganRuleExample();
		example.createCriteria().andSupOrganTypeIdEqualTo(supOrganTypeId)
		.andSubOrganTypeIdEqualTo(subOrganTypeId);
		return organRuleMapper.countByExample(example)>0;
	}
	//校验组织结构规则
	private boolean beanValidator(OrganRule organRule) throws TqException{
		if (organRule==null) {
			throw new TqException("组织规则数据不能为空");
		}
		if (organRule.getSupOrganTypeId()==null) {
			throw new TqException("上级组织类型不能为空");
		}
		OrganType supOrganType=queryOrganTypeById(organRule.getSupOrganTypeId());
		if(supOrganType==null){
			throw new TqException("上级组织类型不存在");
		}
		if (organRule.getSubOrganTypeId()==null) {
			throw new TqException("下级组织类型不能为空");
		}
		OrganType subOrganType=queryOrganTypeById(organRule.getSubOrganTypeId());
		if(subOrganType==null){
			throw new TqException("下级组织类型不存在");
		}
		return true;
	}
	/**
	 * ==================================================================================
	 */
	@Override
	public Organ queryOrganById(Integer organId) throws TqException {
		if (organId==null) {
			throw new TqException("组织id不存在");
		}
		Organ organ=organMapper.selectByPrimaryKey(organId);
		if (organ==null) {
			throw new TqException("组织不存在");
		}
		return organ;
	}
	@Override
	public OrganVo queryOrganVoById(Integer organId) throws TqException {
		OrganVo organ=organDao.queryByOrganId(organId);
		if (organ==null) {
			throw new TqException("组织不存在");
		}
		return organ;
	}
	@Override
	public List<Organ> queryAllOrgan() throws TqException {
		return organMapper.selectByExample(null);
	}
	
	@Override
	public void addOrgan(OperatorCredential credential,Organ organ) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organ==null) {
			throw new TqException("组织数据不能为空");
		}
		//基本校验
		beanValidator(organ);
		//校验组织类型是否存在
		if(null==queryOrganTypeById(organ.getOrganTypeId())){
			throw new TqException("组织类型不存在");
		}
		//校验上级组织是否存在
		Organ parentOrgan=queryOrganById(organ.getParentOrganId());
		if (parentOrgan==null) {
			throw new TqException("上级组织不存在");
		}
		organ.setParentOrganTypeId(parentOrgan.getOrganTypeId());
		//校验是否符合组织规则
		if (!hasOrganRule(parentOrgan.getOrganTypeId(),organ.getOrganTypeId())) {
			throw new TqException("不符合组织规则");
		}
		//校验组织编号是否已存在
		if (queryOrganByCode(organ.getOrganCode())!=null) {
			throw new TqException("组织编码已存在");
		}
		organ.setOrganLevel(parentOrgan.getOrganLevel().intValue()+1);
		organ.setOrganSort(1);
		organ.setIsParent(false);
		organ.setAddOperatorId(credential.getOpId());
		organ.setAddTime(new Date());
		//创建组织
		if (1!=organMapper.insertSelective(organ)) {
			throw new TqException("组织入库失败");
		}
		//更新组织架构路径path
		Organ updateOrgan=new Organ();
		updateOrgan.setOrganId(organ.getOrganId());
		updateOrgan.setPath(parentOrgan.getPath()+organ.getOrganId().intValue()+"#");
		if (1!=organMapper.updateByPrimaryKeySelective(updateOrgan)) {
			throw new TqException("组织入库失败.");
		}
		
		//如果上级组织架构不是父节点，则更新
		if(!parentOrgan.getIsParent().booleanValue()){
			updateOrganIsParent(organ.getParentOrganId(), true);
		}
		
	}
	@Override
	public void updateOrgan(OperatorCredential credential,Organ organ) throws TqException {
		if (credential==null) {
			throw new TqException("操作员不能为空");
		}
		if (organ==null) {
			throw new TqException("组织数据不能为空");
		}
		//基本校验
		beanValidator(organ);
		
		Organ oldOrgan=queryOrganById(organ.getOrganId());
		if (oldOrgan==null) {
			throw new TqException("组织不存在");
		}
		//校验组织编码是否已存在
		Organ codeOrgan=queryOrganByCode(organ.getOrganCode());
		if (codeOrgan!=null&&!codeOrgan.getOrganId().equals(organ.getOrganId())) {
			throw new TqException("组织代码已存在");
		}
		organ.setModifyOperatorId(credential.getOpId());
		organ.setModifyTime(new Date());
		//以下信息不能做修改
		organ.setOrganTypeId(null);//组织类型
		organ.setParentOrganId(null);//上级组织
		organ.setParentOrganTypeId(null);//上级组织组织类型
		//更新组织
		if (1!=organMapper.updateByPrimaryKeySelective(organ)) {
			throw new TqException("更新数据库库失败");
		}
	}
	@Override
	public Organ queryOrganByCode(String organCode) throws TqException{
		if(StringUtils.isBlank(organCode)){
			throw new TqException("组织编码不能为空");
		}
		return organDao.queryOrganByCode(organCode);
	}
	//校验组织
	private boolean beanValidator(Organ organ) throws TqException{
		if (organ==null) {
			throw new TqException("组织数据不能为空");
		}
		if (organ.getOrganTypeId()==null) {
			throw new TqException("组织类型不能为空");
		}
		if (organ.getParentOrganId()==null) {
			throw new TqException("上级组织不能为空");
		}
		if (StringUtils.isBlank(organ.getOrganCode())) {
			throw new TqException("组织编码不能为空");
		}
		if (StringUtils.isBlank(organ.getOrganName())) {
			throw new TqException("组织名称不能为空");
		}
		
		return true;
	}
	@Override
	public void deleteOrgan(OperatorCredential credential,Integer organId) throws TqException {
		//根组织不能删除
		Organ organ=queryOrganById(organId);
		if (organ==null) {
			throw new TqException("组织不存在");
		}
		if (organ.getParentOrganId()==0) {
			throw new TqException("根组织不可以删除");
		}
		//校验组织下是否有用户，有就无法删除
		checkUsedForOrgan(organId);
		//删除组织
		if(1!=organMapper.deleteByPrimaryKey(organId)){
			throw new TqException("删除组织更新数据库失败");
		}
		//如果上级组织架构不是父节点，则更新
		OrganExample example=new OrganExample();
		example.createCriteria().andParentOrganIdEqualTo(organ.getParentOrganId());
		
		if(organMapper.countByExample(example)==0){
			updateOrganIsParent(organ.getParentOrganId(), false);
		}
	}
	
	@Override
	public void checkUsedForOrgan(Integer organId) throws TqException {
		OperatorExample example=new OperatorExample();
		example.createCriteria().andOrganIdEqualTo(organId)
		.andIsDeleteEqualTo(Global.delete_not);
		if(operatorMapper.countByExample(example)>0){
			throw new TqException("该组织下已有用户，无法删除");
		}
		
	}
	@Override
	public boolean isCanAddChildOrganTypeForOrganRule(Integer parentOrganId) throws TqException {
		if (parentOrganId==null) {
			throw new TqException("上级组织id不能为空");
		}
		Organ parentOrgan=queryOrganById(parentOrganId);
		if (parentOrgan==null) {
			throw new TqException("校验的组织不存在");
		}
		OrganRuleExample example=new OrganRuleExample();
		example.createCriteria().andSupOrganTypeIdEqualTo(parentOrgan.getOrganTypeId());
		return organRuleMapper.countByExample(example)>0;
	}
	@Override
	public List<OrganType> queryCanAddChildOrganTypeForOrganRule(Integer parentOrganId) throws TqException {
		if (parentOrganId==null) {
			throw new TqException("上级组织id不能为空");
		}
		Organ parentOrgan=queryOrganById(parentOrganId);
		if (parentOrgan==null) {
			throw new TqException("上级组织不存在");
		}
		OrganCriteria criteria=new OrganCriteria();
		criteria.setSupOrganTypeId(parentOrgan.getOrganTypeId());
		
		return organTypeDao.queryCanAddChildOrganTypeForOrganRule(criteria);
	}
	@Override
	public ListVo<OrganVo> queryOrganListByParent(OrganCriteria criteria) throws TqException {
		if (criteria==null) {
			throw new TqException("参数不能为空");
		}
		if (criteria.getSelectOrganId()==null) {
			throw new TqException("上级组织id不能为空");
		}
		criteria.setPathLike("#"+criteria.getSelectOrganId().intValue()+"#");
		
		OrganExample example=new OrganExample();
		example.createCriteria().andPathLike("%" + criteria.getPathLike() + "%");
		
		int total = organMapper.countByExample(example);
		List<OrganVo> list = null;
		if (total > 0) {
			list = organDao.queryOrganListByParent(criteria);
		}
		return new ListVo<OrganVo>(total, list);
	}
	@Override
	public int updateOrganIsParent(Integer organId, boolean isParent) {
		Organ updateOrgan=new Organ();
		updateOrgan.setOrganId(organId);
		updateOrgan.setIsParent(isParent);
		return organMapper.updateByPrimaryKeySelective(updateOrgan);
	}
	
}
