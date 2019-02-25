package com.duobei.common.zxing;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {
	public static Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
	static{
		// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）  
	    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
	    // 内容所使用字符集编码  
	    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");     
	 // hints.put(EncodeHintType.MAX_SIZE, 350);//设置图片的最大值  
	 // hints.put(EncodeHintType.MIN_SIZE, 100);//设置图片的最小值  
	    hints.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数  
	}
    
	public static void encode(String text, int width, int height,OutputStream out,String iconUrl) throws WriterException, IOException{
		 BitMatrix matrix = new MultiFormatWriter().encode(text,//要编码的内容  
	                BarcodeFormat.QR_CODE,  
	                width, //条形码的宽度  
	                height, //条形码的高度  
	                hints);//生成条形码时的一些配置,此项可选  
		 MatrixToImageWriter.writeToStream(matrix, "jpg", out,iconUrl);
	}
    public static void main(String[] args) {  
    	
    }  
}
