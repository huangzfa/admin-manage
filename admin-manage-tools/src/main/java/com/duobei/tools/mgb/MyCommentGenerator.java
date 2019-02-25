package com.duobei.tools.mgb;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

public class MyCommentGenerator implements CommentGenerator {

	/** 配置信息 */
	private Properties properties;

	/** The suppress date. */
	private boolean suppressDate;

	/** 是否取消注释标识 */
	private boolean suppressAllComments;

	/**
	 * The addition of table remark's comments. If suppressAllComments is true,
	 * this option is ignored
	 */
	private boolean addRemarkComments;

	private SimpleDateFormat dateFormat;

	/**
	 * 构造方法
	 */
	public MyCommentGenerator() {
		super();
		properties = new Properties();
		suppressDate = false;
		suppressAllComments = false;
		addRemarkComments = false;
	}

	/**
	 * 此处是文件最顶部的注释
	 */
	public void addJavaFileComment(CompilationUnit compilationUnit) {
	}

	/**
	 * 给生成的XML添加注释
	 */
	public void addComment(XmlElement xmlElement) {
	}

	/**
	 * XML根节点注释
	 */
	public void addRootComment(XmlElement rootElement) {
	}

	/**
	 * 加载配置属性设置
	 */
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);

		suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

		suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

		addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));

		String dateFormatString = properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
		if (StringUtility.stringHasValue(dateFormatString)) {
			dateFormat = new SimpleDateFormat(dateFormatString);
		}
	}

	/**
	 * This method adds the custom javadoc tag for. You may do nothing if you do
	 * not wish to include the Javadoc tag - however, if you do not include the
	 * Javadoc tag then the Java merge capability of the eclipse plugin will
	 * break.
	 *
	 * @param javaElement
	 *            the java element
	 * @param markAsDoNotDelete
	 *            the mark as do not delete
	 */
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *"); //$NON-NLS-1$
		StringBuilder sb = new StringBuilder();
		sb.append(" * "); //$NON-NLS-1$
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge"); //$NON-NLS-1$
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	/**
	 * This method returns a formated date string to include in the Javadoc tag
	 * and XML comments. You may return null if you do not want the date in
	 * these documentation elements.
	 * 
	 * @return a string representing the current timestamp, or null
	 */
	protected String getDateString() {
		if (suppressDate) {
			return null;
		} else if (dateFormat != null) {
			return dateFormat.format(new Date());
		} else {
			return new Date().toString();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mybatis.generator.api.CommentGenerator#addClassComment(org.mybatis.
	 * generator.api.dom.java.InnerClass,
	 * org.mybatis.generator.api.IntrospectedTable)
	 */
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
		innerClass.addJavaDocLine(" * This class was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This class corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());

		addJavadocTag(innerClass, false);

		innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mybatis.generator.api.CommentGenerator#addTopLevelClassComment(org.
	 * mybatis.generator.api.dom.java.TopLevelClass,
	 * org.mybatis.generator.api.IntrospectedTable)
	 */
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments || !addRemarkComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$

		String remarks = introspectedTable.getRemarks();
		System.out.println("888" + remarks);
		if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
			topLevelClass.addJavaDocLine(" * Database Table Remarks:");
			String[] remarkLines = remarks.split(System.getProperty("line.separator")); //$NON-NLS-1$
			for (String remarkLine : remarkLines) {
				topLevelClass.addJavaDocLine(" *   " + remarkLine); //$NON-NLS-1$
			}
		}
		topLevelClass.addJavaDocLine(" *"); //$NON-NLS-1$

		topLevelClass.addJavaDocLine(" * This class was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This class corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		topLevelClass.addJavaDocLine(sb.toString());

		addJavadocTag(topLevelClass, true);

		topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/**
	 * 枚举类型注释
	 */
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerEnum.addJavaDocLine("/**"); //$NON-NLS-1$
		innerEnum.addJavaDocLine(" * This enum was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This enum corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(sb.toString());

		addJavadocTag(innerEnum, false);

		innerEnum.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/**
	 * 类属性的注释
	 */
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		field.addJavaDocLine("/**");
		field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		field.addJavaDocLine(" */");
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		field.addJavaDocLine("/**"); //$NON-NLS-1$
		field.addJavaDocLine(" * This field was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This field corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		field.addJavaDocLine(sb.toString());

		addJavadocTag(field, false);

		field.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mybatis.generator.api.CommentGenerator#addGeneralMethodComment(org.
	 * mybatis.generator.api.dom.java.Method,
	 * org.mybatis.generator.api.IntrospectedTable)
	 */
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		method.addJavaDocLine("/**"); //$NON-NLS-1$
		method.addJavaDocLine(" * This method was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This method corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		method.addJavaDocLine(sb.toString());

		addJavadocTag(method, false);

		method.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/**
	 * get方法注释
	 */
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
	}

	/**
	 * set方法注释
	 */
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mybatis.generator.api.CommentGenerator#addClassComment(org.mybatis.
	 * generator.api.dom.java.InnerClass,
	 * org.mybatis.generator.api.IntrospectedTable, boolean)
	 */
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
		innerClass.addJavaDocLine(" * This class was generated by MyBatis Generator."); //$NON-NLS-1$

		sb.append(" * This class corresponds to the database table "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());

		addJavadocTag(innerClass, markAsDoNotDelete);

		innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}
}
