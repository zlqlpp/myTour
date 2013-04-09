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

import com.googlecode.jsonplugin.annotations.JSON;


@Entity
@Table(name="CP_WH_DEPARTMENT")

public class Department  implements java.io.Serializable {


    // Fields    

     private Long dmPkCode;
     //private String dtPkCode;
     //private Short dmSno;
     private String dmName;
     private String dmAliasName;
     private District district;



     @ManyToOne(fetch = FetchType.LAZY,targetEntity=District.class)
     @JoinColumn(name="DT_PK_CODE", nullable=false)
     @JSON(serialize=false)
    public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	/** default constructor */
    public Department() {
    }

	/** minimal constructor */
    public Department(Long dmPkCode,String dmName) {
        this.dmPkCode = dmPkCode;
        //this.dtPkCode = dtPkCode;
        this.dmName = dmName;
    }
    
    /** full constructor */
    public Department(Long dmPkCode, String dmName, String dmAliasName) {
        this.dmPkCode = dmPkCode;
        //this.dtPkCode = dtPkCode;
        this.dmName = dmName;
        this.dmAliasName = dmAliasName;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator",sequenceName="SQ_CP_WH_DEPARTMENT_ID",allocationSize=1)@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="DM_PK_CODE", unique=true, nullable=false, precision=10, scale=0)

    public Long getDmPkCode() {
        return this.dmPkCode;
    }
    
    public void setDmPkCode(Long dmPkCode) {
        this.dmPkCode = dmPkCode;
    }
    
    /*@Column(name="DT_PK_CODE", nullable=false, length=7)

    public String getDtPkCode() {
        return this.dtPkCode;
    }
    
    public void setDtPkCode(String dtPkCode) {
        this.dtPkCode = dtPkCode;
    }*/
    
    /*@Column(name="DM_SNO", nullable=false, precision=3, scale=0)

    public Short getDmSno() {
        return this.dmSno;
    }
    
    public void setDmSno(Short dmSno) {
        this.dmSno = dmSno;
    }*/
    
    @Column(name="DM_NAME", nullable=false, length=50)

    public String getDmName() {
        return this.dmName;
    }
    
    public void setDmName(String dmName) {
        this.dmName = dmName;
    }
    
    @Column(name="DM_ALIAS_NAME", length=50)

    public String getDmAliasName() {
        return this.dmAliasName;
    }
    
    public void setDmAliasName(String dmAliasName) {
        this.dmAliasName = dmAliasName;
    }
   








}