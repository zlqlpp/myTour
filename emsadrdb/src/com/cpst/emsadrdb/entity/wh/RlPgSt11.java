package com.cpst.emsadrdb.entity.wh;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="CP_WH_RL_PG_ST_11")
public class RlPgSt11  implements java.io.Serializable {


    // Fields    

     private Long rlPgStId;
     private Long strtId;
     private String distCd;
     private String dtPkCode;
     private Long dmPkCode;
     private Long pgPkCode;
     private Long startNum;
     private String prefix;
     private Long endNum;
     private String suffix;
     private Long rsdnbldgId;
     private Long orgId;
     private String flag;


    // Constructors

    /** default constructor */
    public RlPgSt11() {
    }

	/** minimal constructor */
    public RlPgSt11(Long strtId, String distCd, String dtPkCode, Long startNum, String prefix, Long endNum, String suffix, String flag) {
        this.strtId = strtId;
        this.distCd = distCd;
        this.dtPkCode = dtPkCode;
        this.startNum = startNum;
        this.prefix = prefix;
        this.endNum = endNum;
        this.suffix = suffix;
        this.flag = flag;
    }
    
    /** full constructor */
    public RlPgSt11(Long strtId, String distCd, String dtPkCode, Long dmPkCode, Long pgPkCode, Long startNum, String prefix, Long endNum, String suffix, Long rsdnbldgId, Long orgId, String flag) {
        this.strtId = strtId;
        this.distCd = distCd;
        this.dtPkCode = dtPkCode;
        this.dmPkCode = dmPkCode;
        this.pgPkCode = pgPkCode;
        this.startNum = startNum;
        this.prefix = prefix;
        this.endNum = endNum;
        this.suffix = suffix;
        this.rsdnbldgId = rsdnbldgId;
        this.orgId = orgId;
        this.flag = flag;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator")@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="RL_PG_ST_ID", unique=true, nullable=false, precision=10, scale=0)

    public Long getRlPgStId() {
        return this.rlPgStId;
    }
    
    public void setRlPgStId(Long rlPgStId) {
        this.rlPgStId = rlPgStId;
    }
    
    @Column(name="STRT_ID", nullable=false, precision=14, scale=0)

    public Long getStrtId() {
        return this.strtId;
    }
    
    public void setStrtId(Long strtId) {
        this.strtId = strtId;
    }
    
    @Column(name="DIST_CD", nullable=false, length=6)

    public String getDistCd() {
        return this.distCd;
    }
    
    public void setDistCd(String distCd) {
        this.distCd = distCd;
    }
    
    @Column(name="DT_PK_CODE", nullable=false, length=7)

    public String getDtPkCode() {
        return this.dtPkCode;
    }
    
    public void setDtPkCode(String dtPkCode) {
        this.dtPkCode = dtPkCode;
    }
    
    @Column(name="DM_PK_CODE", precision=10, scale=0)

    public Long getDmPkCode() {
        return this.dmPkCode;
    }
    
    public void setDmPkCode(Long dmPkCode) {
        this.dmPkCode = dmPkCode;
    }
    
    @Column(name="PG_PK_CODE", precision=10, scale=0)

    public Long getPgPkCode() {
        return this.pgPkCode;
    }
    
    public void setPgPkCode(Long pgPkCode) {
        this.pgPkCode = pgPkCode;
    }
    
    @Column(name="START_NUM", nullable=false, precision=10, scale=0)

    public Long getStartNum() {
        return this.startNum;
    }
    
    public void setStartNum(Long startNum) {
        this.startNum = startNum;
    }
    
    @Column(name="PREFIX", nullable=false, length=20)

    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    @Column(name="END_NUM", nullable=false, precision=10, scale=0)

    public Long getEndNum() {
        return this.endNum;
    }
    
    public void setEndNum(Long endNum) {
        this.endNum = endNum;
    }
    
    @Column(name="SUFFIX", nullable=false, length=20)

    public String getSuffix() {
        return this.suffix;
    }
    
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
    @Column(name="RSDNBLDG_ID", precision=14, scale=0)

    public Long getRsdnbldgId() {
        return this.rsdnbldgId;
    }
    
    public void setRsdnbldgId(Long rsdnbldgId) {
        this.rsdnbldgId = rsdnbldgId;
    }
    
    @Column(name="ORG_ID", precision=18, scale=0)

    public Long getOrgId() {
        return this.orgId;
    }
    
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    
    @Column(name="FLAG", nullable=false, length=1)

    public String getFlag() {
        return this.flag;
    }
    
    public void setFlag(String flag) {
        this.flag = flag;
    }
   








}