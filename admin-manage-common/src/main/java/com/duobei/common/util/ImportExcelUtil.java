package com.duobei.common.util;

import com.duobei.common.util.lang.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class ImportExcelUtil {  
      
    private final static String excel2003L =".xls";    //2003- 版本的excel  
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel  
      
    /** 
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public static List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception{
        List<List<Object>> list = new ArrayList<List<Object>>();

        Workbook work =getWorkbook(in,fileName);
        Sheet sheet= work.getSheetAt(0);
        if( sheet == null ){
            throw new Exception("");
        }
		// 获取Excel的所有行,中间有空行，不计算其中，但是遍历时候还会读取空行，导致最后少读一行
		int rows = sheet.getPhysicalNumberOfRows();
		// 遍历行
		for (int i = 0; i < rows; i++) {
			// 读取左上角单元格
			Row row = sheet.getRow(i);
			// 行不能为空
			if (row != null) {
				// 获取Excel文件中的所以列
				int cells = row.getPhysicalNumberOfCells();

				List<Object> li = new ArrayList<Object>();  
				// 遍历列
				for (int j = 0; j < cells; j++) {
					// 获取列的值
					Cell cell = row.getCell(j);
					if (cell != null) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							break;
						case HSSFCell.CELL_TYPE_STRING:
							break;
						default:
							break;
						}
					}
					li.add(getCellValue(cell));
				}
				list.add(li);
			}else{
			    rows++;
            }
		}
        return list;  
    }  
      
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(excel2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(excel2007U.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  
  
    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public static Object getCellValue(Cell cell){  
        Object value = null;  
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
          
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }

    /**
     *
     * @param in
     * @param fileName
     * @param columNameMap Map key为excel的列,value 为对应的calss属性名称
     * @param t
     * @param begin 从第几行开始
     * @param end 到 倒数第几行结束
     * @return
     * @throws Exception
     */
    public static List covertExcelToClass(InputStream in,String fileName, Map<Integer,String> columNameMap, Class t,int begin ,int end) throws Exception{
        List<Object> list = new ArrayList<Object>();

        Workbook work =getWorkbook(in,fileName);
        Sheet sheet= work.getSheetAt(0);
        if(sheet == null || sheet.getPhysicalNumberOfRows() <= begin){
            throw new Exception("文件数据为空,请先检查文件!");
        }

        // 获取Excel的所有行
        int rows = sheet.getPhysicalNumberOfRows();

        Map<String,Method> setMethod = new HashMap<>();
        Method[] methods = t.getMethods();
        for(Method method:methods){
            if(method.getName().substring(0,3).equals("set")){
                setMethod.put(method.getName(),method);
            }
        }
        rows = rows - end;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 遍历行
        for (int i = begin; i < rows; i++) {
            // 读取左上角单元格
            Row row = sheet.getRow(i);
            // 行不能为空
            if (row != null) {
                Object obj = t.newInstance();
                // 获取Excel文件中的所有列
                    int cells = row.getLastCellNum();
                // 遍历列
                for (int j = 0; j < cells; j++) {
                    String columName = columNameMap.get(j);

                    if(StringUtil.isNotEmpty(columName)){
                        //通过列名 获取类中属性
                        Method  method = setMethod.get(getSetMethod(columName));
                        if (method == null){
                            throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,类中没有接收数据的属性列："+columName);
                        }
                        //set方法只有一个参数
                        Class setParam = method.getParameterTypes()[0];
                        if(setParam.isAssignableFrom(Integer.class)){
                            // 获取列的值
                            Cell cell = row.getCell(j);
                            if(cell == null ){
                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据为空，请检查表格中是否存在空行！");
                            }
                            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                                //获取列对应的方法

                                method.invoke(obj,(int)cell.getNumericCellValue());

                            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                                //如果是字符串,尝试转化成时间
                                try{

                                    method.invoke(obj,Integer.parseInt(cell.getStringCellValue()));

                                }catch (Exception e){

                                    throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                                }
                            }else if(cell.getCellType() != HSSFCell.CELL_TYPE_BLANK){

                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                            }

                        }else if (setParam.isAssignableFrom(Long.class)){
                            // 获取列的值
                            Cell cell = row.getCell(j);
                            if(cell == null ){
                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据为空，请检查！");
                            }
                            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                                //获取列对应的方法

                                method.invoke(obj,(long)cell.getNumericCellValue());

                            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                                //如果是字符串,尝试转化成时间
                                try{

                                    method.invoke(obj,Long.parseLong(cell.getStringCellValue()));

                                }catch (Exception e){

                                    throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                                }
                            }else if(cell.getCellType() != HSSFCell.CELL_TYPE_BLANK){

                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                            }

                        }else if (setParam.isAssignableFrom(BigDecimal.class)){
                            // 获取列的值
                            Cell cell = row.getCell(j);
                            if(cell == null ){
                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据为空，请检查！");
                            }

                            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                                //获取列对应的方法

                                method.invoke(obj,new BigDecimal(cell.getNumericCellValue()));

                            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                                //如果是字符串,尝试转化成时间
                                try{

                                    method.invoke(obj,new BigDecimal(cell.getStringCellValue()));

                                }catch (Exception e){

                                    throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                                }
                            }else if(cell.getCellType() != HSSFCell.CELL_TYPE_BLANK){

                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                            }


                        }else if(setParam.isAssignableFrom(String.class)){
                            // 获取列的值
                            Cell cell = row.getCell(j);
                            if(cell == null ){
                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据为空，请检查！");
                            }
                            // 对于异常 公式值 Eorror  均不去处理
                            if(cell.getCellType() == HSSFCell.CELL_TYPE_BLANK){

                            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                                //获取列对应的方法,只要数据非空,就set值
                                method.invoke(obj,cell.getStringCellValue());
                            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                                //获取列对应的方法,只要数据非空,就set值
                                method.invoke(obj,"" + cell.getNumericCellValue());
                            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
                                //获取列对应的方法,只要数据非空,就set值
                                method.invoke(obj,"" + cell.getBooleanCellValue());
                            }


                        }else if(setParam.isAssignableFrom(Date.class)){
                            // 获取列的值
                            Cell cell = row.getCell(j);
                            if(cell == null ){
                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据为空，请检查！");
                            }
                            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){

                                if(HSSFDateUtil.isCellDateFormatted(cell)){
                                    method.invoke(obj,cell.getDateCellValue());
                                }

                            }else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                                //如果是字符串,尝试转化成时间
                                try{

                                    method.invoke(obj,sf.parse(cell.getStringCellValue()));

                                }catch (Exception e){

                                    throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                                }
                            }else if(cell.getCellType() != HSSFCell.CELL_TYPE_BLANK){

                                throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列,数据类型与实体中"+columName+"列不匹配，请检查！");
                            }
                        }else{
                            throw new Exception("格式错误,第"+(i+1)+"行,第"+(j+1)+"列："+columName+"不在可上传的类型范围！");
                        }
                    }
                }

                list.add(obj);
            }
        }
        return list;
    }

    public static String getSetMethod(String columnName){

        return "set" + columnName.substring(0,1).toUpperCase() + columnName.substring(1);
    }

    public static String getAlipayNo(InputStream in,String fileName)throws  Exception{
        List<Object> list = new ArrayList<Object>();
        Workbook work =getWorkbook(in,fileName);
        Sheet sheet= work.getSheetAt(0);
        Row row = sheet.getRow(0);
        if (row == null){
            throw  new Exception("文件数据为空,请先检查文件!");
        }
        Cell cell = row.getCell(0);
        String alipayNo = cell.getStringCellValue();
        return alipayNo.substring(alipayNo.indexOf("：")+1,alipayNo.indexOf("["));
    }

    public static void main(String[] args) {

        System.out.println( "abcType".substring(0,1).toUpperCase() +"abcType".substring(1) );


    }

}
