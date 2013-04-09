package com.cpst.emsadrdb.service.wh;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.ShareDistrict;

@Repository
public class B01r01Manager {
	@Autowired
	private Cache baseOrgDistrictCacheBean;
	
	public List<District> getFilterDistrictsAndShareDistrict(List<District> districts){
		String pkId="";
		List<District> ds=new ArrayList<District>();
		for(District d:districts){
			if(!d.getDtPkCode().equals(pkId)){
				pkId=d.getDtPkCode();
				if(d.getDtFlag().equals("1")){
					String shareCityNames="";
					for(ShareDistrict s:d.getShareDistricts()){
						Element o=baseOrgDistrictCacheBean.get(s.getSdCityId());
						if(o!=null){
							BaseOrgDistrict b=(BaseOrgDistrict)(o.getValue());
							shareCityNames+=b.getCityName();
						}
					}
					d.setShareCityNames(shareCityNames);
				}
				ds.add(d);
			}	
		}
		return ds;
	}

}
