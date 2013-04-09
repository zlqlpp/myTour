package com.cpst.emsadrdb.service.export;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class Exportall{
	
	public  String exportall(String linename,String linelist){
		String[] linelists = linelist.split(",");
		
		String[] linenames = linename.split(",");
		
		Date date = new Date(System.currentTimeMillis());
		
		String path = this.getClass().getResource("/").getPath().replace("classes/", "").replace("WEB-INF/", "");
		
		//if(path.substring(0,1).equals("/")){path = path.substring(1, path.length());}
				
		String allpath = path + "export/";
		
		String getallname = "Exportall_exportall.xls";
		
		String getallpath = allpath + "get/" + getallname;
		
		String setallname = new SimpleDateFormat("yyyyMMdd").format(date) + "Exportall_exportall_" + new SimpleDateFormat("HHmmss").format(date) + ".xls";
		
		String setallpath = allpath + "set/" + setallname;
		
		String exportallpath = "/emsadrdb/export/set/" + setallname;
		
		//System.out.println(allpath);
		
		Exportcom.cleanfile(allpath + "set/",new SimpleDateFormat("yyyyMMdd").format(date));

		try {
			Workbook wb = Workbook.getWorkbook(new File(getallpath));
			File setFile = new File(setallpath);
			WritableWorkbook wwb = Workbook.createWorkbook(setFile, wb); 
			WritableSheet wws = wwb.getSheet(0); 
			
			Label labels = null;
			int labelx=0;
			int labely=0;
			
			for(int n=0;n<linenames.length;n++){
				System.out.println(linenames[n]);
				labels = new Label(labely++,labelx,linenames[n]);
				wws.addCell(labels);
			}
			
			String[] lineliststmp = null;

			for(int i=0;i<linelists.length;i++){
				
				//System.out.println(linelists[i]);
				lineliststmp = linelists[i].split("#");
				
				labelx = labelx+1;
				labely = 0 ;
				
				for(int n=0;n<lineliststmp.length;n++){
					//System.out.println(lineliststmp[n]);
					labels = new Label(labely++,labelx,lineliststmp[n]);
					wws.addCell(labels);
				}
				
			}

			wwb.write(); 
			wwb.close();
			wb.close(); 
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return exportallpath;
	}
	
	
}
