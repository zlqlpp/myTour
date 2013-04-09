package com.cpst.emsadrdb.service.clfw;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cpst.emsadrdb.entity.pmn.User;

public class ClfwCommon {
	
	public static boolean isMunicipalities(String name){
		name=name.trim();
		if(name.equals("北京市")||name.equals("上海市")||name.equals("天津市")||name.equals("重庆市")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isMunicipalitiesID(String id){
		id=id.trim();
		if(id.equals("11")||id.equals("31")||id.equals("12")||id.equals("50")){
			return true;
		}else{
			return false;
		}
	}
	
	public static String getRulUsProvinceOffice(User user){
		String RulUsProvinceOffice = null;
		if(user.getRulLevel()==0 || user.getRulLevel()==2){
			RulUsProvinceOffice = null;
		}else if(user.getRulLevel()==5 || user.getRulLevel()==10 || user.getRulLevel()==15 || user.getRulLevel()==20 || user.getRulLevel()==25){
			RulUsProvinceOffice = user.getUsProvinceOffice();
		}
		return RulUsProvinceOffice;
	}
	
	public static String getRulUsCityOffice(User user){
		String RulUsOffice = null;
		if(user.getRulLevel()==0 || user.getRulLevel()==2 || user.getRulLevel()==5){
			RulUsOffice = null;
		}else if(user.getRulLevel()==10 || user.getRulLevel()==15 || user.getRulLevel()==20 || user.getRulLevel()==25){
			RulUsOffice = user.getUsCityOffice();
		}
		return RulUsOffice;
	}
	
	public static String getRulUsPkId(User user){
		String RulUsPkId = null;
		if(user.getRulLevel()==0 || user.getRulLevel()==2 || user.getRulLevel()==5 || user.getRulLevel()==10){
			RulUsPkId = null;
		}else if(user.getRulLevel()==15 || user.getRulLevel()==20 || user.getRulLevel()==25){
			RulUsPkId = null;
			//RulUsPkId = user.getUsDistrictOffice().toString();
		}
		return RulUsPkId;
	}
	
	public static String getRulUsDepartmentOffice(User user){
		String RulUsDepartmentOffice = null;
		if(user.getRulLevel()==0 || user.getRulLevel()==2 || user.getRulLevel()==5 || user.getRulLevel()==10 || user.getRulLevel()==15){
			RulUsDepartmentOffice = null;
		}else if(user.getRulLevel()==20 || user.getRulLevel()==25){
			RulUsDepartmentOffice = user.getUsDepartmentOffice().toString();
		}
		return RulUsDepartmentOffice;
	}
	
	public static String getUsPostsegOffice(User user){
		String RulUsPostsegOffice = null;
		if(user.getRulLevel()==0 || user.getRulLevel()==2 || user.getRulLevel()==5 || user.getRulLevel()==10 || user.getRulLevel()==15 || user.getRulLevel()==20){
			RulUsPostsegOffice = null;
		}else if(user.getRulLevel()==25){
			RulUsPostsegOffice = user.getUsPostsegOffice().toString();
		}
		return RulUsPostsegOffice;
	}
	
	public static Boolean getUsWebRule(User user){
		Boolean RulUsWebRule = true;
		if(user.getRulLevel()==0 || user.getRulLevel()==2 || user.getRulLevel()==5 || user.getRulLevel()==10 || user.getRulLevel()==15){
			RulUsWebRule = true;
		}else if(user.getRulLevel()==20 || user.getRulLevel()==25){
			RulUsWebRule = false;
		}
		return RulUsWebRule;
	}
	
	public static String sql_inj(String str){
		if(str!=null){
			String inj_str = "and#exec#insert#select#delete#update#count#chr#mid#master#truncate#char#declare#or";
			String inj_stra[] = inj_str.split("#");
			for (int i=0;i<inj_stra.length;i++ )
			{
				str = str.replaceAll(inj_stra[i],"");
			}
		}
		return str;
	}
		
	public static  String configcommon(Object objstr) {
		String str = "";
		if (objstr == null || objstr.toString().equals("null")) {
			str = "";
		}else{
			str = objstr.toString();
		}
		return str;
	}
	
	public static  int shijianss(String stimestr,String etimestr) {
		
		int strint = 0;
		
		try
		{

		
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date stime = df.parse("2012-08-08 " + stimestr.substring(0,2) + ":" + stimestr.substring(2,4) + ":00"); //入住时间
			
			Date etime = df.parse("2012-08-08 " + etimestr.substring(0,2) + ":" + etimestr.substring(2,4) + ":00"); //入住时间
	        
			if(Math.abs(stime.getTime() - etime.getTime()) > 1800000){
				if((stimestr.substring(0,2).equals("00")||stimestr.substring(0,2).equals("01")) && !etimestr.substring(0,1).equals("0")){
					stime = df.parse("2012-08-09 " + stimestr.substring(0,2) + ":" + stimestr.substring(2,4) + ":00"); //入住时间
				}
		        
				if((etimestr.substring(0,2).equals("00")||etimestr.substring(0,2).equals("01")) && !stimestr.substring(0,1).equals("0")){
					etime = df.parse("2012-08-09 " + etimestr.substring(0,2) + ":" + etimestr.substring(2,4) + ":00"); //入住时间
				}

			}
			
			//System.out.println(stime.getTime()+":"+etime.getTime());
			
			if(Math.abs(stime.getTime() - etime.getTime()) <= 1800000){
				
				strint = 1;
				
			}else if(stime.getTime() < etime.getTime()){
				
				strint = 2;
				
			}else{
				
				strint = 0;
			}

		}
		
		catch (Exception e)
		{
			
		}


        
		return strint;
	}
	
	public static  int shijianssf(String stimestr,String etimestr,String stimestrf,String etimestrf) {
		
		int strint = 0;
		
		//String strintstr = "不符合";
		
		try
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			int ishijianss = shijianss(stimestr,etimestr);
			
			if(ishijianss > 0){
				
				strint = 1;
				
				//strintstr = "直接符合";
				
			}else{
				
				Date stimef = df.parse("2012-08-08 " + stimestrf.substring(0,2) + ":" + stimestrf.substring(2,4) + ":00"); //入住时间
				
				Date etimef = df.parse("2012-08-08 " + etimestrf.substring(0,2) + ":" + etimestrf.substring(2,4) + ":00"); //入住时间
		        
				if((stimef.getTime() - etimef.getTime())<=0){
					if((stimestrf.substring(0,2).equals("00")||stimestrf.substring(0,2).equals("01")) && !etimestrf.substring(0,1).equals("0")){
						stimef = df.parse("2012-08-09 " + stimestrf.substring(0,2) + ":" + stimestrf.substring(2,4) + ":00"); //入住时间
					}
			        
					if((etimestrf.substring(0,2).equals("00")||etimestrf.substring(0,2).equals("01")) && !stimestrf.substring(0,1).equals("0")){
						etimef = df.parse("2012-08-09 " + etimestrf.substring(0,2) + ":" + etimestrf.substring(2,4) + ":00"); //入住时间
					}
				}
				
				if((stimef.getTime() - etimef.getTime())>0){

					Date stime = df.parse("2012-08-08 " + stimestr.substring(0,2) + ":" + stimestr.substring(2,4) + ":00"); //入住时间
					
					Date etime = df.parse("2012-08-08 " + etimestr.substring(0,2) + ":" + etimestr.substring(2,4) + ":00"); //入住时间
			        
					if(Math.abs(stime.getTime() - etime.getTime())-(stimef.getTime() - etimef.getTime()) > 1800000){
						if((stimestr.substring(0,2).equals("00")||stimestr.substring(0,2).equals("01")) && !etimestr.substring(0,1).equals("0")){
							stime = df.parse("2012-08-09 " + stimestr.substring(0,2) + ":" + stimestr.substring(2,4) + ":00"); //入住时间
						}
				        
						if((etimestr.substring(0,2).equals("00")||etimestr.substring(0,1).equals("01")) && !stimestr.substring(0,1).equals("0")){
							etime = df.parse("2012-08-09 " + etimestr.substring(0,2) + ":" + etimestr.substring(2,4) + ":00"); //入住时间
						}

					}
					
					if(Math.abs((stime.getTime() - etime.getTime())-(stimef.getTime() - etimef.getTime())) <= 1800000){
						
						strint = 1;
						
						//strintstr = "差别符合";
						
					}
					
					//System.out.println(stime + "-" + etime + "-" +  (stime.getTime() - etime.getTime()));
					
					//System.out.println(stimef + "-" + etimef + "-" +  (stimef.getTime() - etimef.getTime()));
					
					//System.out.println(stime + "-" + etime + "-" +  stimef + "-" + etimef + "-" + strint);
					
				}

			}

		}
		
		catch (Exception e)
		{
			System.out.print(e.getMessage());

		}

		
		//System.out.println(stimestr + "-" + etimestr + "-" +  stimestrf + "-" + etimestrf + "-" + strintstr);
		
		return strint;
	}
}
