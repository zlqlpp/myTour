package com.cpst.emsadrdb.web.wh;

import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;

import com.cpst.core.web.struts2.BaseActionSupport;
import com.cpst.emsadrdb.dao.base.BaseOrgDistrictDao;
import com.cpst.emsadrdb.dao.base.BasePostCityDao;
import com.cpst.emsadrdb.dao.wh.DistrictDao;
import com.cpst.emsadrdb.dao.wh.RlDtPdDao;
import com.cpst.emsadrdb.dao.wh.ShareDistrictDao;
import com.cpst.emsadrdb.entity.base.BaseOrgDistrict;
import com.cpst.emsadrdb.entity.base.BasePostCity;
import com.cpst.emsadrdb.entity.pmn.User;
import com.cpst.emsadrdb.entity.wh.District;
import com.cpst.emsadrdb.entity.wh.RlDtPd;
import com.cpst.emsadrdb.entity.wh.ShareDistrict;
import com.cpst.emsadrdb.service.base.BaseOrgDistrictManager;

/**
 * 投递区与邮编关系维护
 * @author PengYuLei
 *
 */
public class B05r01Action extends BaseActionSupport{
	
	private static final long serialVersionUID = -3824852803836665610L;
	@Autowired
	private BasePostCityDao basePostCityDao;
	private List<BasePostCity> basePostCitys;//邮编集合
	private String havedBasePostCitys;//已有邮编
	private List<District> districts;//区集合
	@Autowired
	private DistrictDao districtDao;
	@Autowired
	private BaseOrgDistrictDao baseOrgDistrictDao;
	@Autowired
	private BaseOrgDistrictManager baseOrgDistrictManager;
	private List<BaseOrgDistrict> citys;//市集合
	private BaseOrgDistrict province;//省
	private BaseOrgDistrict city;//市
	private District district;//区
	private String message;
	@Autowired
	private RlDtPdDao rlDtPdDao;
	private String provinceId;
	private String cityId;
	private String districtId;
	@Autowired
	private Cache districtCacheBean;
	@Autowired
	private ShareDistrictDao shareDistrictDao;
	

	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getHavedBasePostCitys() {
		return havedBasePostCitys;
	}
	public List<District> getDistricts() {
		return districts;
	}
	public District getDistrict() {
		return district;
	}
	public List<BasePostCity> getBasePostCitys() {
		return basePostCitys;
	}
	public List<BaseOrgDistrict> getCitys() {
		return citys;
	}
	public BaseOrgDistrict getProvince() {
		return province;
	}
	public BaseOrgDistrict getCity() {
		return city;
	}
	public String getMessage() {
		return message;
	}


	public String enter() throws Exception {
		User user=getSessionUser();//得到用户所在行政代码
		if(user.getRulLevel()==0 || user.getRulLevel()==2){//系统管理员或者是国家管理员
			province=baseOrgDistrictDao.get("110000");//默认是北京
			city=province;
			city.setCityName(city.getProvinceName());
			citys=new ArrayList<BaseOrgDistrict>();
			citys.add(city);

		}else if(user.getRulLevel()==5){//省级管理员
			province=baseOrgDistrictDao.get(user.getUsProvinceOffice());
			if(baseOrgDistrictManager.isMunicipalities(province.getProvinceName())){//如果是直辖市
				city=province;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
				city=citys.get(0);
			}
		}else if(user.getRulLevel()==10){//地市级管理员
			city=baseOrgDistrictDao.get(user.getUsCityOffice());
			if(baseOrgDistrictManager.isMunicipalities(city.getProvinceName())){//如果是直辖市
				province=city;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{
				province=baseOrgDistrictDao.getProvinceByProvinceName(city.getProvinceName());
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
			}
		}else{
			message="只有地市级以上用户可以查询维护";
			return "message";
		}
		districts=districtDao.getDistrictsByCity(city.getDistrictCode());
		if(districts!=null && !districts.isEmpty()){
			district=districts.get(0);
		}else{
			districts=new ArrayList<District>();
		}
		//添加共享区
		List<ShareDistrict> ds=shareDistrictDao.getShareDistrictsByCityId(city.getDistrictCode());
		if(ds!=null && !ds.isEmpty()){
			for (ShareDistrict s : ds) {
				Element o = districtCacheBean.get(s.getDistrict().getDtPkCode());
				if (o != null) {
					District b = (District) (o.getValue());
					districts.add(b);
				}
			}
		}
		havedBasePostCitys="";
		if(district!=null){
			List<RlDtPd> rds=rlDtPdDao.getRlDtPdsByDtPkCode(district.getDtPkCode());
			for(RlDtPd r:rds){
				havedBasePostCitys+=","+r.getId().getPdPkCode();
			}
		}
		if(havedBasePostCitys.length()>0){
			havedBasePostCitys=havedBasePostCitys.substring(1);
		}
		basePostCitys=basePostCityDao.getBasePostCityByCityId(city.getDistrictCode());
		return "view";
	}
	
	public String query() throws Exception {
		if(provinceId!=null && !provinceId.equals("")){//通过省代码查询
			province=baseOrgDistrictDao.get(provinceId);
			if(baseOrgDistrictManager.isMunicipalities(province.getProvinceName())){//如果是直辖市
				city=province;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
				city=citys.get(0);
			}
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			if(districts!=null && !districts.isEmpty()){
				district=districts.get(0);
				
			}
		}
		if(cityId!=null && !cityId.equals("")){
			if(baseOrgDistrictManager.isMunicipalitiesByCode(cityId)){//如果是直辖市
				province=baseOrgDistrictDao.get(cityId);
				city=province;
				city.setCityName(city.getProvinceName());
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省下面的市
				city=baseOrgDistrictDao.get(cityId);
				province=baseOrgDistrictDao.getProvinceByProvinceName(city.getProvinceName());
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
			}
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
			if(districts!=null && !districts.isEmpty()){
				district=districts.get(0);	
			}
		}
		if(districtId!=null && !districtId.equals("")){
			district=districtDao.get(districtId);
			city=baseOrgDistrictDao.get(district.getDtCityId());
			if(baseOrgDistrictManager.isMunicipalitiesByCode(city.getDistrictCode())){//如果是直辖市
				city.setCityName(city.getProvinceName());
				province=city;
				citys=new ArrayList<BaseOrgDistrict>();
				citys.add(city);
			}else{//如果是省下面的市
				province=baseOrgDistrictDao.getProvinceByProvinceName(city.getProvinceName());
				citys=baseOrgDistrictDao.getCitysByProvince("2",province.getProvinceName());
			}
			districts=districtDao.getDistrictsByCity(city.getDistrictCode());
		}
		//添加共享区
		if(districts==null && !districts.isEmpty()){
			districts=new ArrayList<District>();
		}
		List<ShareDistrict> ds=shareDistrictDao.getShareDistrictsByCityId(city.getDistrictCode());
		if(ds!=null && !ds.isEmpty()){
			for (ShareDistrict s : ds) {
				Element o = districtCacheBean.get(s.getDistrict().getDtPkCode());
				if (o != null) {
					District b = (District) (o.getValue());
					districts.add(b);
				}
			}
		}
		havedBasePostCitys="";
		if(district!=null){
			List<RlDtPd> rds=rlDtPdDao.getRlDtPdsByDtPkCode(district.getDtPkCode());
			for(RlDtPd r:rds){
				havedBasePostCitys+=","+r.getId().getPdPkCode();
			}
		}
		if(havedBasePostCitys.length()>0){
			havedBasePostCitys=havedBasePostCitys.substring(1);
		}
		basePostCitys=basePostCityDao.getBasePostCityByCityId(city.getDistrictCode());
		return "view";
	}

}
