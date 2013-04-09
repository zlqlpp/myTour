package com.cpst.emsadrdb.service.export;

import java.io.File;

public class Exportcom{
	public static void cleanfile(String allpath,String deldate){
		//String prefix = "^[XXX].*$";//以"XXX"开头的文件
		String prefix = "^" + deldate + ".*$";//以"XXX"开头的文件
		
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
