package com.stc.video.util;

/**
 * 
 */

/**
 * @author jverderber
 *
 */
public class CaptureScreens {

	/**
	 * 
	 */
	public CaptureScreens() {
		long ci = 0;
		while(true)
		{
			try{
				Thread.sleep(2000);
				if(!(new java.io.File("images")).exists()){(new java.io.File("images")).mkdir();}
				String iname[]=new String[]{"images/image_"+ci+".jpg"};
				ci++;
				CaptureScreen cs = new CaptureScreen(iname);
			}catch(Exception ex){ex.printStackTrace();}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CaptureScreens();

	}

}
