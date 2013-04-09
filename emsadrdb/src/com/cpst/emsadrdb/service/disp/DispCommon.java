package com.cpst.emsadrdb.service.disp;

import com.cpst.emsadrdb.entity.pmn.User;

public class DispCommon {
	
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
			RulUsPkId = user.getUsDistrictOffice().toString();
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
		
}
