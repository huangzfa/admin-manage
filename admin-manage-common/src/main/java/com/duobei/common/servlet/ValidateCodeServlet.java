package com.duobei.common.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 验证码
 * @author Hong
 *
 */
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 5863811465721564075L;
	
	public static final String VALIDATE_CODE = "validateCode";
	private int w = 70;
	private int h = 26;

	public void destroy() {
		super.destroy();
	}

	/**
	 * 校验验证码
	 * 
	 * @param request
	 * @param validateCode
	 * @return
	 */
	public static boolean validate(HttpServletRequest request,String validateCode) {
		if (StringUtils.isBlank(validateCode)) {
			return false;
		}
		String code = (String) request.getSession().getAttribute(VALIDATE_CODE);
		return validateCode.toUpperCase().equals(code);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		createImage(req, resp);
	}

	private void createImage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		response.setContentType("image/jpeg");

		String width = request.getParameter("width");
		String height = request.getParameter("height");
		if ((StringUtils.isNumeric(width)) && (StringUtils.isNumeric(height))) {
			this.w = NumberUtils.toInt(width);
			this.h = NumberUtils.toInt(height);
		}

		BufferedImage image = new BufferedImage(this.w, this.h, 1);
		Graphics g = image.getGraphics();

		createBackground(g);

		String s = createCharacter(g);
		request.getSession().setAttribute(VALIDATE_CODE, s);

		g.dispose();
		OutputStream out = response.getOutputStream();
		ImageIO.write(image, "JPEG", out);
		out.close();
	}

	private Color getRandColor(int fc, int bc) {
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

	private void createBackground(Graphics g) {
		g.setColor(getRandColor(220, 250));
		g.fillRect(0, 0, this.w, this.h);

		for (int i = 0; i < 8; i++) {
			g.setColor(getRandColor(40, 150));
			Random random = new Random();
			int x = random.nextInt(this.w);
			int y = random.nextInt(this.h);
			int x1 = random.nextInt(this.w);
			int y1 = random.nextInt(this.h);
			g.drawLine(x, y, x1, y1);
		}
	}

	private String createCharacter(Graphics g) {
		char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
				'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
		String[] fontTypes = { "Arial", "Arial Black", "AvantGarde Bk BT",
				"Calibri" };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);
			g.setColor(new Color(50 + random.nextInt(100), 50 + random
					.nextInt(100), 50 + random.nextInt(100)));
			g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], 1,
					26));
			g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));

			s.append(r);
		}
		return s.toString();
	}
}
