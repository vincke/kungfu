package org.kungfu.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileKit {
	
	public static void wirteToFile(String filePath, String fileName, String content) {
		File dir = new File(filePath);
		if (!dir.exists())
			dir.mkdirs();
		FileWriter fw = null;
		String target = filePath + fileName;
		try {
			fw = new FileWriter(target);
			fw.write(content);
		}
		catch(IOException e) {
			e.getStackTrace();
		}
		finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
