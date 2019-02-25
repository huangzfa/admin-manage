package com.duobei.common.config;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GlobalBuilder {
	private static Logger log = LoggerFactory.getLogger(GlobalBuilder.class);
	
	private static Boolean flag=false;
	
	public static void builder(){
		InputStream is = null;
		if (!flag) {
			synchronized (flag) {
				if (!flag) {
					flag=true;
					try {
						is=GlobalBuilder.class.getClassLoader().getResourceAsStream("global.xml");
						SAXParserFactory factory = SAXParserFactory.newInstance();
						SAXParser parser = factory.newSAXParser();
						GlobalHandler handle = new GlobalHandler();
						parser.parse(is, handle);
					} catch (Exception e) {
						log.error("解析系统配置xml异常",e);
					}finally{
						if (is!=null) {
							try {
								is.close();
							} catch (IOException e) {
							}
						}
					}
				}
			}
		}
    }
}

class GlobalHandler extends DefaultHandler{
	private static Logger log = LoggerFactory.getLogger(GlobalHandler.class);
	
	private GlobalItem item;
	
	//开始解析文档，即开始解析XML根元素时调用该方法
	@Override
	public void startDocument() throws SAXException {
		log.info("=====>开始解析global.xml");
	}
	//开始解析每个元素时都会调用该方法
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
//		log.info("=====>startElement--"+qName);
		if ("item".equals(qName)) {
			item=new GlobalItem();
			if (attributes!=null) {
				item.setKey(attributes.getValue("key"));
				item.setVal(attributes.getValue("val"));
				item.setTitle(attributes.getValue("title"));
				item.setDesc(attributes.getValue("desc"));
				
				Global.getItems().put(item.getKey(), item);
			}
			
		}
	}
	//解析到每个元素的内容时会调用此方法
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
//		String content = new String(ch,start,length);  
//		log.info("=====>characters--"+content.trim());
	}
	//每个元素结束的时候都会调用该方法
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
//		log.info("=====>endElement--"+qName);
		if ("item".equals(qName)) {
			log.info(item.toString());
			item=null;
		}
	}
	//结束解析文档，即解析根元素结束标签时调用该方法
	@Override
	public void endDocument() throws SAXException {
		log.info("=====>结束解析global.xml");
	}
}
