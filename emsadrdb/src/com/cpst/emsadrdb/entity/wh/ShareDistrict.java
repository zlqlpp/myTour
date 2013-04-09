package com.cpst.emsadrdb.entity.wh;
// default package

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.googlecode.jsonplugin.annotations.JSON;


@Entity
@Table(name="CP_WH_SHARE_DISTRICT")

public class ShareDistrict  implements java.io.Serializable {


    // Fields    

     private Long sdPkId;
     //private String dtPkCode;
     private String sdCityId;
     private String cityName;//翻译
     private District district;
     
     
     @Transient
     public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	@ManyToOne(fetch = FetchType.LAZY,targetEntity=District.class)
     @JoinColumn(name="DT_PK_CODE", nullable=true)
     @JSON(serialize=false)
    public District getDistrict() {
		return district;
	}


	public void setDistrict(District district) {
		this.district = district;
	}


	/** default constructor */
    public ShareDistrict() {
    }

    
    /** full constructor */
    public ShareDistrict(String sdCityId) {
        this.sdCityId = sdCityId;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator",sequenceName="SQ_CP_WH_SHARE_DISTRICT_ID",allocationSize=1)@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="SD_PK_ID", unique=true, nullable=false, precision=10, scale=0)

    public Long getSdPkId() {
        return this.sdPkId;
    }
    
    public void setSdPkId(Long sdPkId) {
        this.sdPkId = sdPkId;
    }
    
    /*@Column(name="DT_PK_CODE", nullable=false, length=7)

    public String getDtPkCode() {
        return this.dtPkCode;
    }
    
    public void setDtPkCode(String dtPkCode) {
        this.dtPkCode = dtPkCode;
    }*/
    
    @Column(name="SD_CITY_ID", nullable=false, length=6)

    public String getSdCityId() {
        return this.sdCityId;
    }
    
    public void setSdCityId(String sdCityId) {
        this.sdCityId = sdCityId;
    }
   








}