package com.stc.video.util;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 * @author jverderber
 *
 */
public class CaptureScreen {

	/**
	 * 
	 */
	public CaptureScreen(String args[]) {
		
		try {
			java.awt.Robot r = new Robot();
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();
			String fpath = "images/screen.png";
			if(args.length>0){fpath=""+args[0];}
			
			File fpathp = new File(fpath).getParentFile();
			if(!fpathp.exists()){fpathp.mkdirs();}
			
			ImageIO.write(
			r.createScreenCapture(new Rectangle(width,height)),
			"png",new File(fpath));
			System.out.println("Saved image: "+fpath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CaptureScreen(args);

	}
	
	

}
