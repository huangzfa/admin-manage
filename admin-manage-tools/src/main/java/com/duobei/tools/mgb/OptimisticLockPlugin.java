package com.duobei.tools.mgb;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class OptimisticLockPlugin extends PluginAdapter {

	public boolean validate(List<String> arg0) {
		return true;
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		@SuppressWarnings("unused")
		List<Element> list = element.getElements();

		boolean bool = super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);

		return bool;
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {
		// TODO Auto-generated method stub
		return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
	}

}
