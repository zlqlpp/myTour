package com.cpst.emsadrdb.entity.base;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * OrgDistrict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="CP_BASE_ORG_DISTRICT"
)

public class BaseOrgDistrict  implements java.io.Serializable {


    // Fields    

     private String districtCode;
     private String provinceName;
     private String cityName;
     private String countyName;
     private String provinceSim;
     private String citySim;
     private String countySim;
     private String provinceSimName;
     private String citySimName;
     private String countySimName;
     private String degree;
     private String provinceSimname;


    // Constructors

    /** default constructor */
    public BaseOrgDistrict() {
    }

	/** minimal constructor */
    public BaseOrgDistrict(String districtCode) {
        this.districtCode = districtCode;
    }
    
    /** full constructor */
    public BaseOrgDistrict(String districtCode, String provinceName, String cityName, String countyName, String provinceSim, String citySim, String countySim, String provinceSimName, String citySimName, String countySimName, String degree, String provinceSimname) {
        this.districtCode = districtCode;
        this.provinceName = provinceName;
        this.cityName = cityName;
        this.countyName = countyName;
        this.provinceSim = provinceSim;
        this.citySim = citySim;
        this.countySim = countySim;
        this.provinceSimName = provinceSimName;
        this.citySimName = citySimName;
        this.countySimName = countySimName;
        this.degree = degree;
        this.provinceSimname = provinceSimname;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="DISTRICT_CODE", unique=true, nullable=false, length=6)

    public String getDistrictCode() {
        return this.districtCode;
    }
    
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
    
    @Column(name="PROVINCE_NAME", length=22)

    public String getProvinceName() {
        return this.provinceName;
    }
    
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
    @Column(name="CITY_NAME", length=22)

    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    @Column(name="COUNTY_NAME", length=40)

    public String getCountyName() {
        return this.countyName;
    }
    
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    
    @Column(name="PROVINCE_SIM", length=12)

    public String getProvinceSim() {
        return this.provinceSim;
    }
    
    public void setProvinceSim(String provinceSim) {
        this.provinceSim = provinceSim;
    }
    
    @Column(name="CITY_SIM", length=12)

    public String getCitySim() {
        return this.citySim;
    }
    
    public void setCitySim(String citySim) {
        this.citySim = citySim;
    }
    
    @Column(name="COUNTY_SIM", length=12)

    public String getCountySim() {
        return this.countySim;
    }
    
    public void setCountySim(String countySim) {
        this.countySim = countySim;
    }
    
    @Column(name="PROVINCE_SIM_NAME", length=6)

    public String getProvinceSimName() {
        return this.provinceSimName;
    }
    
    public void setProvinceSimName(String provinceSimName) {
        this.provinceSimName = provinceSimName;
    }
    
    @Column(name="CITY_SIM_NAME", length=10)

    public String getCitySimName() {
        return this.citySimName;
    }
    
    public void setCitySimName(String citySimName) {
        this.citySimName = citySimName;
    }
    
    @Column(name="COUNTY_SIM_NAME", length=20)

    public String getCountySimName() {
        return this.countySimName;
    }
    
    public void setCountySimName(String countySimName) {
        this.countySimName = countySimName;
    }
    
    @Column(name="DEGREE", length=1)

    public String getDegree() {
        return this.degree;
    }
    
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    @Column(name="PROVINCE_SIMNAME", length=10)

    public String getProvinceSimname() {
        return this.provinceSimname;
    }
    
    public void setProvinceSimname(String provinceSimname) {
        this.provinceSimname = provinceSimname;
    }
   








}