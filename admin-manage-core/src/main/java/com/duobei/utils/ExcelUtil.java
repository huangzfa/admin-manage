package com.duobei.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by bwju on 2016/12/06.
 */


public class ExcelUtil {
    // 测试123
    private ExcelUtil() {

    }

    /***
     * 工作簿
     */
    private static HSSFWorkbook workbook;

    /***
     * sheet
     */
    private static HSSFSheet sheet;

    /***
     * 表头行开始位置
     */
    private static final int HEAD_START_POSITION = 0;

    /***
     * 文本行开始位置
     */
    private static final int CONTENT_START_POSITION = 1;

    /**
     * office 2007 以下excel最大行数
     */
    private static final int EXCEL_MAX_CELL_UNDER_OFFICE2007 = 65536;

    /**
     *
     * @param dataList
     *            对象集合
     * @param titleMap
     *            表头信息（对象属性名称->要显示的标题值)[按顺序添加]
     * @param sheetName
     *            sheet名称和表头值
     */
    public static void excelExport(List<?> dataList, Map<String, String> titleMap, String sheetName, String ExcelPath) {
        // 初始化workbook
        initHSSFWorkbook(sheetName);
        // 表头行
        createHeadRow(titleMap);
        // 文本行
        createContentRow(dataList, titleMap);
        // 写入处理结果
        try {
            String filedisplay = sheetName;
            // 如果web项目，1、设置下载框的弹出（设置response相关参数)；2、通过httpservletresponse.getOutputStream()获取
            File file = new File(ExcelPath);
            if(!file.exists()){
                file.mkdirs();
            }
            OutputStream out = new FileOutputStream(ExcelPath +filedisplay);
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     *
     * @param sheetName
     *            sheetName
     */
    private static void initHSSFWorkbook(String sheetName) {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet(sheetName);
    }

    /**
     * 创建表头行
     *
     * @param titleMap
     *            对象属性名称->表头显示名称
     */
    private static void createHeadRow(Map<String, String> titleMap) {
        // 第1行创建
        HSSFRow headRow = sheet.createRow(HEAD_START_POSITION);
        int i = 0;
        Set<Entry<String, String>> entrySet = titleMap.entrySet();
        for (Entry<String, String> entry : entrySet) {
            HSSFCell headCell = headRow.createCell(i);
            headCell.setCellValue(entry.getValue());
            i++;
        }
    }

    /**
     *
     * @param dataList
     *            对象数据集合
     * @param titleMap
     *            表头信息
     */
    private static void createContentRow(List<?> dataList, Map<String, String> titleMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            int i = 0;
            //sheet行数写满时建新sheet(office07以下excel最大行数为65536,为兼容,以最低版本考虑)

            for (Object obj : dataList) {
                BigDecimal flag = new BigDecimal(i).divide(new BigDecimal(EXCEL_MAX_CELL_UNDER_OFFICE2007-1),10,BigDecimal.ROUND_HALF_DOWN);
                if(i>0 && flag.compareTo(new BigDecimal(1)) == 0){
                    sheet = workbook.createSheet(sheet.getSheetName() + i/65535);
                    createHeadRow(titleMap);
                    i = 0;
                }
                HSSFRow textRow = sheet.createRow(CONTENT_START_POSITION + i);
                int j = 0;
                for (String entry : titleMap.keySet()) {
                    String method = "get" + entry.substring(0, 1).toUpperCase() + entry.substring(1);
                    Method m = obj.getClass().getMethod(method, null);
                    String value;
                    if (m.invoke(obj, null) == null) {
                        value = "";
                    } else {
                        //若反射得到对象为date,则格式化
                        Object _date = m.invoke(obj, null);
                        if(_date instanceof  Date){
                            value = DateUtil.formatDate((Date) _date,"yyyyMMdd HH:mm:ss");
                        }else {
                            value = m.invoke(obj, null).toString();
                        }
                    }

                    //若value为日期格式(EEE MMM dd HH:mm:ss zzz yyyy),则格式化为yyyyMMdd HH:mm:ss
                    try {
                        Date date = sdf.parse(value);
                        value = DateUtil.formatDate(date,"yyyyMMdd HH:mm:ss");
                    }catch (Exception e){

                    }
                    HSSFCell textcell = textRow.createCell(j);
                    textcell.setCellValue(value);
                    j++;
                }
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> loadExcel(String filepath, int count) {
        // 创建Excel工作簿文件的引用
        Workbook workbook = null;
        try {
            URL url = new URL(filepath);//把远程文件地址转换成URL格式
            InputStream in = url.openStream();
            if(filepath.indexOf(".xlsx")>-1){
                workbook = new XSSFWorkbook(in);
            } else {
                workbook = new HSSFWorkbook(in);// 根据路劲创建引用
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 在excel文档中，第一个工作表的缺省索引是0
        Sheet sheet = workbook.getSheetAt(count);
        // 获取到excel文件中的所有行数
        int rows = sheet.getPhysicalNumberOfRows();
        List<Map<String, Object>> li = new ArrayList<Map<String, Object>>();
        // boolean boo = false;
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                // 获取文件中的所有列
                int cells = row.getPhysicalNumberOfCells();
                Map<String, Object> map = new HashMap<>();
                // 遍历列
                for (int j = 0; j < cells; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        Row title = sheet.getRow(0);
                        Cell titleCell = title.getCell(cell.getColumnIndex());
                        map.put(titleCell.getStringCellValue(), cell.getStringCellValue());
                    }
                }
                li.add(map);
            }
        }
        return li;
    }

    public static void excelExportOn(List<?> dataList, Map<String, String> titleMap, String sheetName, HttpServletResponse response) {
        // 初始化workbook
        initHSSFWorkbook(sheetName);
        // 表头行
        createHeadRow(titleMap);
        // 文本行
        createContentRow(dataList, titleMap);
        // 写入处理结果
        try {
            String filedisplay = sheetName;
            // 如果web项目，1、设置下载框的弹出（设置response相关参数)；2、通过httpservletresponse.getOutputStream()获取
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=zonghen.xls");
            response.setContentType("application/msexcel");
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void excelExportOn(List<?> dataList, Map<String, String> titleMap, String sheetName,String fileName,HttpServletResponse response) {
        // 初始化workbook
        initHSSFWorkbook(sheetName);
        // 表头行
        createHeadRow(titleMap);
        // 文本行
        createContentRow(dataList, titleMap);
        OutputStream out = null;
        // 写入处理结果
        try {
            String filedisplay = sheetName;
            // 如果web项目，1、设置下载框的弹出（设置response相关参数)；2、通过httpservletresponse.getOutputStream()获取
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename="+ URLEncoder.encode(fileName, "UTF-8") +".xls");
            response.setContentType("application/msexcel");
            out = response.getOutputStream();
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        	try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}