package com.cpst.emsadrdb.entity.pmn;
// default package

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="CP_PMN_USER")

public class User  implements java.io.Serializable {


    // Fields    

     private Long usPkId;
     private String usLoginId;
     private String usName;
     private String usPasswd;
     private String usMobile;
     private String usPhone;
     private String usAddress;
     private String usEmail;
     private Short rulLevel;
     private String usCountryOffice;
     private String usProvinceOffice;
     private String usCityOffice;
     private String usDistrictOffice;
     private Long usDepartmentOffice;
     private Long usPostsegOffice;
     private String usStatus;
     private List<Role> roles;
     private String transNames;//翻译
     
    @Transient
    public String getTransNames() {
		return transNames;
	}

	public void setTransNames(String transNames) {
		this.transNames = transNames;
	}

	@ManyToMany(targetEntity=Role.class,cascade={CascadeType.MERGE,CascadeType.PERSIST})   
    @JoinTable(name="CP_PMN_RL_USER_ROLE",joinColumns={@JoinColumn(name="us_pk_id")},inverseJoinColumns={@JoinColumn(name="re_pk_id")}) 
    public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String usLoginId, String usName, String usPasswd, Short rulLevel, String usStatus) {
        this.usLoginId = usLoginId;
        this.usName = usName;
        this.usPasswd = usPasswd;
        this.rulLevel = rulLevel;
        this.usStatus = usStatus;
    }
    
    /** full constructor */
    public User(String usLoginId, String usName, String usPasswd, String usMobile, String usPhone, String usAddress, String usEmail, Short rulLevel, String usCountryOffice, String usProvinceOffice, String usCityOffice, String usDistrictOffice, Long usDepartmentOffice, Long usPostsegOffice, String usStatus) {
        this.usLoginId = usLoginId;
        this.usName = usName;
        this.usPasswd = usPasswd;
        this.usMobile = usMobile;
        this.usPhone = usPhone;
        this.usAddress = usAddress;
        this.usEmail = usEmail;
        this.rulLevel = rulLevel;
        this.usCountryOffice = usCountryOffice;
        this.usProvinceOffice = usProvinceOffice;
        this.usCityOffice = usCityOffice;
        this.usDistrictOffice = usDistrictOffice;
        this.usDepartmentOffice = usDepartmentOffice;
        this.usPostsegOffice = usPostsegOffice;
        this.usStatus = usStatus;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator",sequenceName="SQ_CP_PMN_USER_ID",allocationSize=1)@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="US_PK_ID", unique=true, nullable=false, precision=10, scale=0)

    public Long getUsPkId() {
        return this.usPkId;
    }
    
    public void setUsPkId(Long usPkId) {
        this.usPkId = usPkId;
    }
    
    @Column(name="US_LOGIN_ID", nullable=false, length=50)

    public String getUsLoginId() {
        return this.usLoginId;
    }
    
    public void setUsLoginId(String usLoginId) {
        this.usLoginId = usLoginId;
    }
    
    @Column(name="US_NAME", nullable=false, length=50)

    public String getUsName() {
        return this.usName;
    }
    
    public void setUsName(String usName) {
        this.usName = usName;
    }
    
    @Column(name="US_PASSWD", nullable=false, length=50)

    public String getUsPasswd() {
        return this.usPasswd;
    }
    
    public void setUsPasswd(String usPasswd) {
        this.usPasswd = usPasswd;
    }
    
    @Column(name="US_MOBILE", length=15)

    public String getUsMobile() {
        return this.usMobile;
    }
    
    public void setUsMobile(String usMobile) {
        this.usMobile = usMobile;
    }
    
    @Column(name="US_PHONE", length=15)

    public String getUsPhone() {
        return this.usPhone;
    }
    
    public void setUsPhone(String usPhone) {
        this.usPhone = usPhone;
    }
    
    @Column(name="US_ADDRESS", length=100)

    public String getUsAddress() {
        return this.usAddress;
    }
    
    public void setUsAddress(String usAddress) {
        this.usAddress = usAddress;
    }
    
    @Column(name="US_EMAIL", length=100)

    public String getUsEmail() {
        return this.usEmail;
    }
    
    public void setUsEmail(String usEmail) {
        this.usEmail = usEmail;
    }
    
    @Column(name="RUL_LEVEL", nullable=false, precision=3, scale=0)

    public Short getRulLevel() {
        return this.rulLevel;
    }
    
    public void setRulLevel(Short rulLevel) {
        this.rulLevel = rulLevel;
    }
    
    @Column(name="US_COUNTRY_OFFICE", length=6)

    public String getUsCountryOffice() {
        return this.usCountryOffice;
    }
    
    public void setUsCountryOffice(String usCountryOffice) {
        this.usCountryOffice = usCountryOffice;
    }
    
    @Column(name="US_PROVINCE_OFFICE", length=6)

    public String getUsProvinceOffice() {
        return this.usProvinceOffice;
    }
    
    public void setUsProvinceOffice(String usProvinceOffice) {
        this.usProvinceOffice = usProvinceOffice;
    }
    
    @Column(name="US_CITY_OFFICE", length=6)

    public String getUsCityOffice() {
        return this.usCityOffice;
    }
    
    public void setUsCityOffice(String usCityOffice) {
        this.usCityOffice = usCityOffice;
    }
    
    @Column(name="US_DISTRICT_OFFICE", length=7)

    public String getUsDistrictOffice() {
        return this.usDistrictOffice;
    }
    
    public void setUsDistrictOffice(String usDistrictOffice) {
        this.usDistrictOffice = usDistrictOffice;
    }
    
    @Column(name="US_DEPARTMENT_OFFICE", length=10)

    public Long getUsDepartmentOffice() {
        return this.usDepartmentOffice;
    }
    
    public void setUsDepartmentOffice(Long usDepartmentOffice) {
        this.usDepartmentOffice = usDepartmentOffice;
    }
    
    @Column(name="US_POSTSEG_OFFICE", length=11)

    public Long getUsPostsegOffice() {
        return this.usPostsegOffice;
    }
    
    public void setUsPostsegOffice(Long usPostsegOffice) {
        this.usPostsegOffice = usPostsegOffice;
    }
    
    @Column(name="US_STATUS", nullable=false, length=1)

    public String getUsStatus() {
        return this.usStatus;
    }
    
    public void setUsStatus(String usStatus) {
        this.usStatus = usStatus;
    }
   








}