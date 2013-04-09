package com.cpst.emsadrdb.service.jjsx;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class JjsxExportall{
	
	public  String exportall(String USERPATH,String LINEMAIN,String LINENAME,List<?> list){
		String[] LINEMAINs = LINEMAIN.split(",");
		
		String[] LINENAMEs = LINENAME.split(",");
		
		Date date = new Date(System.currentTimeMillis());
		
		String path = this.getClass().getResource("/").getPath().replace("classes/", "").replace("WEB-INF/", "");
				
		String allpath = path + "export/";
		
		String setname = new SimpleDateFormat("yyyyMMdd").format(date) + "_" +  LINEMAINs[0] + "_" + new SimpleDateFormat("HHmmss").format(date) + ".xls";

		String setallpath = allpath + "set/" + USERPATH;
		
		File foder = new File(setallpath);
		
		if(foder.exists()==false){
			foder.mkdir();
	    }

		String setallname = setallpath + "/" + setname;
		
		String exportallpath = "/emsadrdb/export/set/" + USERPATH + "/" + setname;

		JjsxExportcom.cleanfile(setallpath,new SimpleDateFormat("yyyyMMdd").format(date));

		try {
			File setFile = new File(setallname);
			
			WritableWorkbook wbook = Workbook.createWorkbook(setFile);
	        String tmptitle = LINEMAINs[1]; 
	        WritableSheet wsheet = wbook.createSheet(LINEMAINs[1], 0);

			WritableFont wfont = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
			WritableCellFormat wcfFC = new WritableCellFormat(wfont); 
			//wcfFC.setBackground(Colour.AQUA); 
			wsheet.addCell(new Label(1, 0, LINEMAINs[2], wcfFC));   
			
			wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14,WritableFont.BOLD,false, UnderlineStyle.NO_UNDERLINE,Colour.BLACK);   
			wcfFC = new WritableCellFormat(wfont);  
        
			for(int n=0;n<LINENAMEs.length;n++){
				wsheet.addCell(new Label(n, 2,LINENAMEs[n]));
			}
	
			Iterator<?> it = list.iterator();
			int i = 3;
			while (it.hasNext()) {
				Object[] obj = (Object[]) it.next();
				for(int n=0;n<obj.length;n++){
					if(obj[n]!=null && obj[n].toString().length()>0){
						wsheet.addCell(new Label(n,i,obj[n].toString()));
					}else{
						wsheet.addCell(new Label(n,i," "));
					}
				}
				i = i + 1;
			}

			wbook.write(); 
			wbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} 
		
		return exportallpath;
	}
}
