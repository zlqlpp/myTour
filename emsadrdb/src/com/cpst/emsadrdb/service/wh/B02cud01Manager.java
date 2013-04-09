package com.cpst.emsadrdb.service.wh;

import org.springframework.stereotype.Repository;

import com.cpst.emsadrdb.entity.pmn.User;

@Repository
public class B02cud01Manager {
	
	public int validateRole(User user,String cityId,String dtPkId){
		int passstat=1;//验证通过
		if(user.getRulLevel()==0 || user.getRulLevel()==2){//系统管理员和国家管理员
			
		}else if(user.getRulLevel()==5){//省管理员
			if(!(cityId.substring(0,2)+"0000").equals(user.getUsProvinceOffice())){
				passstat=2;//错误代码：跨省维护
			}
		}else if(user.getRulLevel()==10){//地市级管理员
			if(!cityId.equals(user.getUsCityOffice())){
				passstat=3;//错误代码：跨市维护
			}
		}else if(user.getRulLevel()==15){//区级管理员
			if(!dtPkId.equals(user.getUsDistrictOffice())){
				passstat=4;//错误代码：跨区维护
			}
		}else {
			passstat=9;//错误代码：其他错误
		}
		return passstat;
	}
	
	public String validateMessage(int passstat){
		String message="";
		if(passstat==1){
			message="通过验证";
		}else if(passstat==2){
			message="不能跨省维护";
		}else if(passstat==3){
			message="不能跨市维护";
		}else if(passstat==4){
			message="不能跨区维护";
		}else{
			message="其他错误";
		}
		return message;
	}

}
