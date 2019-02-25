package com.duobei.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

public class VerifyCodeUtil {

	private static String[] fontTypes = { "Arial", "Arial Black", "AvantGarde Bk BT","Calibri" };
	private static int imgW = 90;
	private static int imgH = 40;
	
	public static void createImg(String verifyCode,OutputStream output) throws IOException{
		BufferedImage image = new BufferedImage(imgW, imgH, BufferedImage.TYPE_INT_RGB);
		Graphics g = null;
		try {
			g = image.getGraphics();
			createBackground(g);
			createCharacter(g,verifyCode);
		}finally{
			if(g!=null){
				g.dispose();
			}
		}
		ImageIO.write(image, "JPEG", output);
	}
	
	private static Color getRandColor(int fc, int bc) {
		int f = fc;
		int b = bc;
		Random random = new Random();
		if (f > 255) {
			f = 255;
		}
		if (b > 255) {
			b = 255;
		}
		return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f),
				f + random.nextInt(b - f));
	}

	private static void createBackground(Graphics g) {
		g.setColor(getRandColor(220, 250));
		g.fillRect(0, 0, imgW, imgH);

		for (int i = 0; i < 8; i++) {
			g.setColor(getRandColor(40, 150));
			Random random = new Random();
			int x = random.nextInt(imgW);
			int y = random.nextInt(imgH);
			int x1 = random.nextInt(imgW);
			int y1 = random.nextInt(imgH);
			g.drawLine(x, y, x1, y1);
		}
	}

	private static void createCharacter(Graphics g,String verifyCode) {
		Random random = new Random();
		for (int i = 0; i < verifyCode.length(); i++) {
			g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
			g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], 1,26));
			g.drawString(String.valueOf(verifyCode.charAt(i)), 20 * i + 5, 22 + random.nextInt(8));
		}
	}
	
	public static void main(String[] args) throws IOException {
		VerifyCodeUtil.createImg("s2d7",new FileOutputStream(new File("G:/验证码.JPEG")));
	}
}
