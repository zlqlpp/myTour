package com.cpst.emsadrdb.service.clfw;

import java.io.File;

public class ClfwExportcom{
	public static void cleanfile(String allpath,String deldate){
		//String prefix = "^[XXX].*$";//
		String prefix = "^" + deldate + ".*$";//
		
		File file = new File(allpath);
		File fileList[] = file.listFiles();
		for (File f : fileList) {   
			if (f.isFile()) {    
			if (!f.getName().matches(prefix)){
				System.out.println(f.getName());
				f.delete();
			}
		}   
		}   
	}	
}
