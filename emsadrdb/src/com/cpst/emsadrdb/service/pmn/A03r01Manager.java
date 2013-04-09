package com.cpst.emsadrdb.service.pmn;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.Department;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.Postseg;

@Repository
public class A03r01Manager {
	@Autowired
	private Cache baseOrgDistrictCacheBean;
	@Autowired
	private Cache districtCacheBean;
	@Autowired
	private Cache departmentCacheBean;
	@Autowired
	private Cache postsegCacheBean;
	public Short managerRoleLevel(Short userLevel,boolean isMunicipalities){
		Short rl=userLevel;
		if(userLevel==0){
			rl=2;
		}else if(userLevel==2){
			rl=5;
		}else if(userLevel==5){
			if(isMunicipalities){
				rl=15;
			}else{
				rl=10;
			}
		}else if(userLevel==10){
			rl=15;
		}else if(userLevel==15){
			rl=20;
		}else if(userLevel==20){
			rl=25;
		}
		return rl;
	}
	public List<User> transUserList(List<User> users){
		for(User u:users){
			String names="";
			if(u.getUsProvinceOffice()!=null && !u.getUsProvinceOffice().equals("")){
				Element o=baseOrgDistrictCacheBean.get(u.getUsProvinceOffice());
				if(o!=null){
					BaseOrgDistrict b=(BaseOrgDistrict)(o.getValue());
					names+=b.getProvinceName();
				}
			}
			if(u.getUsCityOffice()!=null && !u.getUsCityOffice().equals("")){
				if(!u.getUsProvinceOffice().equals(u.getUsCityOffice())){
					Element o=baseOrgDistrictCacheBean.get(u.getUsCityOffice());
					if(o!=null){
						BaseOrgDistrict b=(BaseOrgDistrict)(o.getValue());
						names+="-"+b.getCityName();
					}
				}
			}
			if(u.getUsDistrictOffice()!=null && !u.getUsDistrictOffice().equals("")){
				Element o=districtCacheBean.get(u.getUsDistrictOffice());
				if(o!=null){
					District b=(District)(o.getValue());
					names+="-"+b.getDtName();
				}
			}
			if(u.getUsDepartmentOffice()!=null && !u.getUsDepartmentOffice().equals("")){
				Element o=departmentCacheBean.get(u.getUsDepartmentOffice());
				if(o!=null){
					Department b=(Department)(o.getValue());
					names+="-"+b.getDmName();
				}
			}
			if(u.getUsPostsegOffice()!=null && !u.getUsPostsegOffice().equals("")){
				Element o=postsegCacheBean.get(u.getUsPostsegOffice());
				if(o!=null){
					Postseg b=(Postseg)(o.getValue());
					names+="-"+b.getPgName();
				}
			}
			u.setTransNames(names);
		}
		return users;
	}
}
