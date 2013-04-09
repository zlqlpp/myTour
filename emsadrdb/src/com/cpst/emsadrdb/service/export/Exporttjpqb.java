package com.cpst.emsadrdb.service.export;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class Exporttjpqb{
	
	public String exportall(List<?> list){
		Date date = new Date(System.currentTimeMillis());
		
		String path = this.getClass().getResource("/").getPath().replace("classes/", "").replace("WEB-INF/", "");
		
		//if(path.substring(0,1).equals("/")){path = path.substring(1, path.length());}
				
		String allpath = path + "export/";
		
		String getallname = "Exporttjpqb_exportall.xls";
		
		String getallpath = allpath + "get/" + getallname;
		
		String setallname = new SimpleDateFormat("yyyyMMdd").format(date) + "Exporttjpqb_exportall_" + new SimpleDateFormat("HHmmss").format(date) + ".xls";
		
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
			Number labeln = null;
			int labelx=0;
			int labely=0;
			
			Iterator<?> it = list.iterator();
			
			while (it.hasNext()) {
				labelx = labelx + 1;
				labely = 0 ;
				Object[] obj = (Object[]) it.next();
				
				labely = 0;
				labels = new Label(labely,labelx,obj[1].toString());
				wws.addCell(labels);
				
				labely = 1;
				labeln = new Number(labely,labelx,Integer.valueOf(obj[2].toString()));
				wws.addCell(labeln);
				
				labely = 2;
				labeln = new Number(labely,labelx,Integer.valueOf(obj[3].toString()));
				wws.addCell(labeln);
				
				labely = 3;
				labeln = new Number(labely,labelx,Integer.valueOf(obj[4].toString()));
				wws.addCell(labeln);
				
				labely = 4;
				labeln = new Number(labely,labelx,Integer.valueOf(obj[5].toString()));
				wws.addCell(labeln);
				
				labely = 6;
				labeln = new Number(labely,labelx,Integer.valueOf(obj[7].toString()));
				wws.addCell(labeln);
				
				labely = 7;
				labeln = new Number(labely,labelx,Integer.valueOf(obj[8].toString()));
				wws.addCell(labeln);
				
				labely = 8;
				labeln = new Number(labely,labelx,Integer.valueOf(obj[9].toString()));
				wws.addCell(labeln);
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
