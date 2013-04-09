package com.cpst.emsadrdb.service.pmn;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cpst.emsadrdb.entity.pmn.RoleLevel;
@Repository
public class RoleLevelManager {

	public boolean validatedRoleLevels(List<RoleLevel> roleLevels){
		boolean b=false;
		if(roleLevels==null || roleLevels.isEmpty()){
			return b;
		}else{
			if(roleLevels.size()!=7){
				return b;
			}else{
				if(roleLevels.get(0).getRulLevel()!=0){
					return b;
				}
				if(roleLevels.get(1).getRulLevel()!=2){
					return b;
				}
				if(roleLevels.get(2).getRulLevel()!=5){
					return b;
				}
				if(roleLevels.get(3).getRulLevel()!=10){
					return b;
				}
				if(roleLevels.get(4).getRulLevel()!=15){
					return b;
				}
				if(roleLevels.get(5).getRulLevel()!=20){
					return b;
				}
				if(roleLevels.get(6).getRulLevel()!=25){
					return b;
				}
			}
		}
		b=true;
		return b;
	}
	
	

}
