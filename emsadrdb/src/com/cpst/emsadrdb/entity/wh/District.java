package com.cpst.emsadrdb.entity.wh;
// default package

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.googlecode.jsonplugin.annotations.JSON;


@Entity
@Table(name="CP_WH_DISTRICT")

public class District  implements java.io.Serializable {


    // Fields    

     private String dtPkCode;
     private String dtProvinceCode;
     private String dtCityCode;
     private Short dtSno;
     private String dtName;
     private String dtAliasName;
     private String dtCityId;
     private String dtHaveDepartment;
     private String dtHaveDepartmentName;//翻译
     private String dtFlag;
     private String shareCityNames;
     private List<ShareDistrict> shareDistricts=new ArrayList<ShareDistrict>();
     private List<Department> departments=new ArrayList<Department>();
     private List<Postseg> postsegs=new ArrayList<Postseg>();
     
    @JSON(serialize=false)
    @OneToMany(mappedBy="district",targetEntity=Postseg.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="DT_PK_CODE")
    public List<Postseg> getPostsegs() {
		return postsegs;
	}

	public void setPostsegs(List<Postseg> postsegs) {
		this.postsegs = postsegs;
	}

	@JSON(serialize=false)
   	@OneToMany(mappedBy="district",targetEntity=Department.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   	@JoinColumn(name="DT_PK_CODE")
     public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	@Transient
    public String getDtHaveDepartmentName() {
    	 if(dtHaveDepartment!=null){
    		 if(dtHaveDepartment.equals("0")){
        		 dtHaveDepartmentName="无";
        	 }else{
        		 dtHaveDepartmentName="有";
        	 } 
    	 }
		return dtHaveDepartmentName;
	}

	public void setDtHaveDepartmentName(String dtHaveDepartmentName) {
		this.dtHaveDepartmentName = dtHaveDepartmentName;
	}

	@Transient
    public String getShareCityNames() {
		if(shareCityNames==null){
			shareCityNames="";
		}
		return shareCityNames;
	}

	public void setShareCityNames(String shareCityNames) {
		this.shareCityNames = shareCityNames;
	}

	@JSON(serialize=false)
  	@OneToMany(mappedBy="district",targetEntity=ShareDistrict.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  	@JoinColumn(name="DT_PK_CODE")
    public List<ShareDistrict> getShareDistricts() {
		return shareDistricts;
	}

	public void setShareDistricts(List<ShareDistrict> shareDistricts) {
		this.shareDistricts = shareDistricts;
	}

	/** default constructor */
    public District() {
    }

	/** minimal constructor */
    public District(String dtProvinceCode, String dtCityCode, Short dtSno, String dtName, String dtCityId, String dtHaveDepartment, String dtFlag) {
        this.dtProvinceCode = dtProvinceCode;
        this.dtCityCode = dtCityCode;
        this.dtSno = dtSno;
        this.dtName = dtName;
        this.dtCityId = dtCityId;
        this.dtHaveDepartment = dtHaveDepartment;
        this.dtFlag = dtFlag;
    }
    
    /** full constructor */
    public District(String dtProvinceCode, String dtCityCode, Short dtSno, String dtName, String dtAliasName, String dtCityId, String dtHaveDepartment, String dtFlag) {
        this.dtProvinceCode = dtProvinceCode;
        this.dtCityCode = dtCityCode;
        this.dtSno = dtSno;
        this.dtName = dtName;
        this.dtAliasName = dtAliasName;
        this.dtCityId = dtCityId;
        this.dtHaveDepartment = dtHaveDepartment;
        this.dtFlag = dtFlag;
    }

   
    // Property accessors

    @Id 
    
    @Column(name="DT_PK_CODE", unique=true, nullable=false, length=7)
    public String getDtPkCode() {
        return this.dtPkCode;
    }
    
    public void setDtPkCode(String dtPkCode) {
        this.dtPkCode = dtPkCode;
    }
    
    @Column(name="DT_PROVINCE_CODE", nullable=false, length=2)

    public String getDtProvinceCode() {
        return this.dtProvinceCode;
    }
    
    public void setDtProvinceCode(String dtProvinceCode) {
        this.dtProvinceCode = dtProvinceCode;
    }
    
    @Column(name="DT_CITY_CODE", nullable=false, length=2)

    public String getDtCityCode() {
        return this.dtCityCode;
    }
    
    public void setDtCityCode(String dtCityCode) {
        this.dtCityCode = dtCityCode;
    }
    
    @Column(name="DT_SNO", nullable=false, precision=3, scale=0)

    public Short getDtSno() {
        return this.dtSno;
    }
    
    public void setDtSno(Short dtSno) {
        this.dtSno = dtSno;
    }
    
    @Column(name="DT_NAME", nullable=false, length=50)

    public String getDtName() {
        return this.dtName;
    }
    
    public void setDtName(String dtName) {
        this.dtName = dtName;
    }
    
    @Column(name="DT_ALIAS_NAME", length=50)

    public String getDtAliasName() {
        return this.dtAliasName;
    }
    
    public void setDtAliasName(String dtAliasName) {
        this.dtAliasName = dtAliasName;
    }
    
    @Column(name="DT_CITY_ID", nullable=false, length=6)

    public String getDtCityId() {
        return this.dtCityId;
    }
    
    public void setDtCityId(String dtCityId) {
        this.dtCityId = dtCityId;
    }
    
    @Column(name="DT_HAVE_DEPARTMENT", nullable=false, length=1)

    public String getDtHaveDepartment() {
        return this.dtHaveDepartment;
    }
    
    public void setDtHaveDepartment(String dtHaveDepartment) {
        this.dtHaveDepartment = dtHaveDepartment;
    }
    
    @Column(name="DT_FLAG", nullable=false, length=1)

    public String getDtFlag() {
        return this.dtFlag;
    }
    
    public void setDtFlag(String dtFlag) {
        this.dtFlag = dtFlag;
    }
   








}