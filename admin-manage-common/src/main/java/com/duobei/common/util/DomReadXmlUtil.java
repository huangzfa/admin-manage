package com.duobei.common.util;

import com.duobei.common.util.lang.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * declare
 *
 * @author: ritchey
 * @date: 2019/1/15
 * @time: 17:39
 * @version: v1.0
 * Description:
 */
public class DomReadXmlUtil {
    private static DocumentBuilderFactory dbFactory = null;
    private static DocumentBuilder db = null;
    private static Document document = null;

    static {
        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            db = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static List covertUploadByClass(InputStream in, String fileName, Map<Integer, String> columNameMap, Class t, int begin, int end) throws Exception {
        //将给定 URI 的内容解析为一个 XML 文档,并返回Document对象
        document = db.parse(in);

        //按文档顺序返回包含在文档中且具有给定标记名称的所有 Element 的 NodeList
        NodeList rowList = document.getElementsByTagName("Row");
        if(rowList == null ||  rowList.getLength() <= begin){

            throw new Exception("上传文件为空,请检查文件后再上传!");
        }

        List<Object> result = new ArrayList<>();

        Map<String, Method> setMethod = new HashMap<>();
        Method[] methods = t.getMethods();
        for (Method method : methods) {
            if (method.getName().substring(0, 3).equals("set")) {
                setMethod.put(method.getName(), method);
            }
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //遍历books
        for (int i = 3; i < rowList.getLength() - 1; i++) {
            //获取第i个book结点
            Node rowNode = rowList.item(i);
            NodeList cellList = ((Element) rowNode).getElementsByTagName("Cell");

            Iterator<Integer> it = columNameMap.keySet().iterator();
            Object obj = t.newInstance();
            while (it.hasNext()) {
                Integer key = it.next();
                String columName = columNameMap.get(key);
                Node cellNode = cellList.item(key);
                if (StringUtil.isNotEmpty(columName)) {
                    //通过列名 获取类中属性
                    Method method = setMethod.get(getSetMethod(columName));
                    if (method == null) {
                        throw new Exception("格式错误,第" + (i + 1) + "行,第" + (key) + "列,类中没有接收数据的属性列：" + columName);
                    }
                    String data = null;
                    if (cellNode != null) {
                        NodeList dataNodeList = ((Element) cellNode).getElementsByTagName("Data");
                        if (dataNodeList != null) {
                            Node dataNode = dataNodeList.item(0);
                            data = dataNode.getTextContent();
                        }
                    } else {
                        throw new Exception("格式错误,第" + (i + 1) + "行,第" + (key) + "列,数据为空，请检查表格中是否存在空行！");
                    }

                    if (StringUtil.isNotEmpty(data)) {
                        //set方法只有一个参数
                        Class setParam = method.getParameterTypes()[0];
                        try {
                            if (setParam.isAssignableFrom(Integer.class)) {
                                method.invoke(obj, Integer.parseInt(data));
                            } else if (setParam.isAssignableFrom(Long.class)) {
                                method.invoke(obj, Long.parseLong(data));
                            } else if (setParam.isAssignableFrom(BigDecimal.class)) {
                                method.invoke(obj, new BigDecimal(data));
                            } else if (setParam.isAssignableFrom(String.class)) {
                                method.invoke(obj, data);
                            } else if (setParam.isAssignableFrom(Date.class)) {
                                method.invoke(obj, sf.parse(data));
                            }
                        } catch (Exception e) {
                            throw new Exception("格式错误,第" + (i + 1) + "行,第" + (key) + "列,数据类型与实体中" + columName + "列不匹配，请检查！");
                        }
                    }
                }
            }

            result.add(obj);
        }

        return result;

    }


    public static String getAlipayNo(InputStream in)throws  Exception{
        //将给定 URI 的内容解析为一个 XML 文档,并返回Document对象
        document = db.parse(in);

        //按文档顺序返回包含在文档中且具有给定标记名称的所有 Element 的 NodeList
        NodeList rowList = document.getElementsByTagName("Row");
        if(rowList == null ||  rowList.getLength() <= 0){

            throw new Exception("上传文件为空,请检查文件后再上传!");
        }

        Node rowNode = rowList.item(0);
        if (rowNode == null){

            throw new Exception("上传文件中没有支付宝账号数据,请检查文件后再上传!");
        }

        NodeList cellList = ((Element) rowNode).getElementsByTagName("Cell");

        if(cellList == null){

            throw new Exception("上传文件中没有支付宝账号数据,请检查文件后再上传!");
        }
        Node cellNode = cellList.item(0);

        if(cellNode == null ){

            throw new Exception("上传文件中没有支付宝账号数据,请检查文件后再上传!");
        }

        NodeList dataNodeList = ((Element) cellNode).getElementsByTagName("Data");

        if (dataNodeList == null){

            throw new Exception("上传文件中没有支付宝账号数据,请检查文件后再上传!");
        }
        Node dataNode = dataNodeList.item(0);
        if (dataNode == null ){

            throw new Exception("上传文件中没有支付宝账号数据,请检查文件后再上传!");
        }

        String alipayNo = dataNode.getTextContent();

        return alipayNo.substring(alipayNo.indexOf("：")+1,alipayNo.indexOf("["));
    }

    public static String getSetMethod(String columnName) {

        return "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
    }

    public static void main(String args[]) {
        String fileName = "C:\\Users\\acer\\Desktop\\新模板.xml";
        try {
            File file = new File(fileName);
            InputStream in = new FileInputStream(file);
            String alipayNo = getAlipayNo(in);
            System.out.println(alipayNo);
            //List<Object> list = DomReadXmlUtil.covertUploadByClass(in, fileName, Constants.BILLING_DETAIL_IMPORT, RepaymentOfflineUploadRong.class, 3, 1);
            //System.out.println(JSONObject.toJSON(list).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
