package com.cpst.emsadrdb.entity.adr;

import java.util.Date;

/**
 * 封装sql查询结果
 * @author pengyulei
 *
 */
public class AdrStreeBean {
	 private Long strtId;
     private String distCd;
     private Long strt1id;
     private String strt1Name;
     private Long strt2id;
     private String strt2Name;
     private Long strt3id;
     private String strt3Name;
     private Long strt4id;
     private String strt4Name;
     private Long strt5id;
     private String strt5Name;
     private String strtAbbrName;
     private String strtAbbr;
     private Long minBgnNbr;
     private String statCd;
     private Short segNum;
     private Date updDate;
     private String empNbr;
     private String statFlag;
     private Date dataDate;
     private String dataFlag;
     private String statFlagName;//翻译分段
     
	public String getStatFlagName() {
		if(getStatFlag()==null){
			statFlagName="未分段";
		}else if(getStatFlag().equals("1")){
			statFlagName="已分投递段";
		}else if(getStatFlag().equals("2")){
			statFlagName="已分揽收段";
		}else if(getStatFlag().equals("3")){
			statFlagName="已分投递揽收段";
		}else{
			statFlagName="未分段";
		}
		return statFlagName;
	}
	public void setStatFlagName(String statFlagName) {
		this.statFlagName = statFlagName;
	}
	public Long getStrtId() {
		return strtId;
	}
	public void setStrtId(Long strtId) {
		this.strtId = strtId;
	}
	public String getDistCd() {
		return distCd;
	}
	public void setDistCd(String distCd) {
		this.distCd = distCd;
	}
	public Long getStrt1id() {
		return strt1id;
	}
	public void setStrt1id(Long strt1id) {
		this.strt1id = strt1id;
	}
	public String getStrt1Name() {
		return strt1Name;
	}
	public void setStrt1Name(String strt1Name) {
		this.strt1Name = strt1Name;
	}
	public Long getStrt2id() {
		return strt2id;
	}
	public void setStrt2id(Long strt2id) {
		this.strt2id = strt2id;
	}
	public String getStrt2Name() {
		return strt2Name;
	}
	public void setStrt2Name(String strt2Name) {
		this.strt2Name = strt2Name;
	}
	public Long getStrt3id() {
		return strt3id;
	}
	public void setStrt3id(Long strt3id) {
		this.strt3id = strt3id;
	}
	public String getStrt3Name() {
		return strt3Name;
	}
	public void setStrt3Name(String strt3Name) {
		this.strt3Name = strt3Name;
	}
	public Long getStrt4id() {
		return strt4id;
	}
	public void setStrt4id(Long strt4id) {
		this.strt4id = strt4id;
	}
	public String getStrt4Name() {
		return strt4Name;
	}
	public void setStrt4Name(String strt4Name) {
		this.strt4Name = strt4Name;
	}
	public Long getStrt5id() {
		return strt5id;
	}
	public void setStrt5id(Long strt5id) {
		this.strt5id = strt5id;
	}
	public String getStrt5Name() {
		return strt5Name;
	}
	public void setStrt5Name(String strt5Name) {
		this.strt5Name = strt5Name;
	}
	public String getStrtAbbrName() {
		return strtAbbrName;
	}
	public void setStrtAbbrName(String strtAbbrName) {
		this.strtAbbrName = strtAbbrName;
	}
	public String getStrtAbbr() {
		return strtAbbr;
	}
	public void setStrtAbbr(String strtAbbr) {
		this.strtAbbr = strtAbbr;
	}
	public Long getMinBgnNbr() {
		return minBgnNbr;
	}
	public void setMinBgnNbr(Long minBgnNbr) {
		this.minBgnNbr = minBgnNbr;
	}
	public String getStatCd() {
		return statCd;
	}
	public void setStatCd(String statCd) {
		this.statCd = statCd;
	}
	public Short getSegNum() {
		return segNum;
	}
	public void setSegNum(Short segNum) {
		this.segNum = segNum;
	}
	public Date getUpdDate() {
		return updDate;
	}
	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	public String getEmpNbr() {
		return empNbr;
	}
	public void setEmpNbr(String empNbr) {
		this.empNbr = empNbr;
	}
	public String getStatFlag() {
		return statFlag;
	}
	public void setStatFlag(String statFlag) {
		this.statFlag = statFlag;
	}
	public Date getDataDate() {
		return dataDate;
	}
	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}
	public String getDataFlag() {
		return dataFlag;
	}
	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}
     

}
