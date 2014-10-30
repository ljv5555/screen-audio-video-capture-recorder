/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.stc.audio.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.zip.GZIPOutputStream;

import com.stc.video.util.CaptureScreen;

public class Recorder {
	public static long IMAGE_CAPTURE_DELAY = 2;
	public Recorder(String[] args) throws Exception {
		while (true) {
			long ts = new Date().getTime();
			File file1 = new File(ts + "-out.wav");
			SimpleAudioRecorder r = SimpleAudioRecorder
					.init1(new String[] { file1.getAbsolutePath() });
			r.start();
			try {
				for(int i=0;i<(60/IMAGE_CAPTURE_DELAY);i++)
				{
					Thread.sleep(IMAGE_CAPTURE_DELAY);
					CaptureScreen.main(new String[]{"images/image_"+(new Date()).getTime()});
				}
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			r.stopRecording();
			System.out.println("Wrote file " + file1.getAbsolutePath());
			File gz = new File(file1.getAbsolutePath() + ".gz");
			FileOutputStream fos = new FileOutputStream(gz);
			FileInputStream fis = new FileInputStream(file1);
			GZIPOutputStream zos = new GZIPOutputStream(fos);
			while (true) {
				int i = fis.read();
				if (i == -1)
					break;
				zos.write(i);
			}
			zos.close();
			file1.delete();
		}
	}

	public static void main(String[] args) {
		try {
			new Recorder(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}