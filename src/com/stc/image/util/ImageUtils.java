/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.stc.image.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtils {
	BufferedImage bi = null;

	public BufferedImage getBi() {
		return this.bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public ImageUtils(byte[] b) throws IOException {
		this.bi = ImageIO.read(new ByteArrayInputStream(b));
	}

	private static boolean imagesMatch(byte[] b1, byte[] b2) throws IOException {
		boolean rtn = true;
		BufferedImage bi1 = new ImageUtils(b1).getBi();
		BufferedImage bi2 = new ImageUtils(b2).getBi();
		if (bi1.getWidth() != bi2.getWidth())
			return false;
		if (bi1.getHeight() != bi2.getHeight())
			return false;

		for (int i = 0; i < bi1.getWidth(); ++i) {
			for (int j = 0; j < bi1.getHeight(); ++j) {
				rtn = (rtn) && (bi1.getRGB(i, j) == bi2.getRGB(i, j));
			}
		}
		return rtn;
	}

	int[] indexOf(byte[] b) throws IOException {
		BufferedImage bi2 = new ImageUtils(b).getBi();
		int[] rtn = { -1, -1 };
		for (int i = 0; i < this.bi.getWidth(); ++i) {
			for (int j = 0; j < this.bi.getHeight(); ++j) {
				BufferedImage bi3 = this.bi.getSubimage(i, j, bi2.getWidth(),
						bi2.getHeight());
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(bi3, "PNG", baos);
				if (imagesMatch(baos.toByteArray(), b))
					return new int[] { i, j };
			}
		}
		return rtn;
	}
}