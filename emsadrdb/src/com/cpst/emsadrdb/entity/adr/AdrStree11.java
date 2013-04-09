package com.cpst.emsadrdb.entity.adr;
// default package


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="CP_MK_ADR_STREET_11")

public class AdrStree11  implements java.io.Serializable {


    // Fields    

     private AdrStree11Id id;
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


    // Constructors

    /** default constructor */
    public AdrStree11() {
    }

	/** minimal constructor */
    public AdrStree11(AdrStree11Id id) {
        this.id = id;
    }
    
    /** full constructor */
    public AdrStree11(AdrStree11Id id, Long strt1id, String strt1Name, Long strt2id, String strt2Name, Long strt3id, String strt3Name, Long strt4id, String strt4Name, Long strt5id, String strt5Name, String strtAbbrName, String strtAbbr, Long minBgnNbr, String statCd, Short segNum, Date updDate, String empNbr, String statFlag, Date dataDate, String dataFlag) {
        this.id = id;
        this.strt1id = strt1id;
        this.strt1Name = strt1Name;
        this.strt2id = strt2id;
        this.strt2Name = strt2Name;
        this.strt3id = strt3id;
        this.strt3Name = strt3Name;
        this.strt4id = strt4id;
        this.strt4Name = strt4Name;
        this.strt5id = strt5id;
        this.strt5Name = strt5Name;
        this.strtAbbrName = strtAbbrName;
        this.strtAbbr = strtAbbr;
        this.minBgnNbr = minBgnNbr;
        this.statCd = statCd;
        this.segNum = segNum;
        this.updDate = updDate;
        this.empNbr = empNbr;
        this.statFlag = statFlag;
        this.dataDate = dataDate;
        this.dataFlag = dataFlag;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="strtId", column=@Column(name="STRT_ID", nullable=false, precision=14, scale=0) ), 
        @AttributeOverride(name="distCd", column=@Column(name="DIST_CD", nullable=false, length=6) ) } )

    public AdrStree11Id getId() {
        return this.id;
    }
    
    public void setId(AdrStree11Id id) {
        this.id = id;
    }
    
    @Column(name="STRT1ID", precision=14, scale=0)

    public Long getStrt1id() {
        return this.strt1id;
    }
    
    public void setStrt1id(Long strt1id) {
        this.strt1id = strt1id;
    }
    
    @Column(name="STRT1_NAME", length=40)

    public String getStrt1Name() {
        return this.strt1Name;
    }
    
    public void setStrt1Name(String strt1Name) {
        this.strt1Name = strt1Name;
    }
    
    @Column(name="STRT2ID", precision=14, scale=0)

    public Long getStrt2id() {
        return this.strt2id;
    }
    
    public void setStrt2id(Long strt2id) {
        this.strt2id = strt2id;
    }
    
    @Column(name="STRT2_NAME", length=40)

    public String getStrt2Name() {
        return this.strt2Name;
    }
    
    public void setStrt2Name(String strt2Name) {
        this.strt2Name = strt2Name;
    }
    
    @Column(name="STRT3ID", precision=14, scale=0)

    public Long getStrt3id() {
        return this.strt3id;
    }
    
    public void setStrt3id(Long strt3id) {
        this.strt3id = strt3id;
    }
    
    @Column(name="STRT3_NAME", length=40)

    public String getStrt3Name() {
        return this.strt3Name;
    }
    
    public void setStrt3Name(String strt3Name) {
        this.strt3Name = strt3Name;
    }
    
    @Column(name="STRT4ID", precision=14, scale=0)

    public Long getStrt4id() {
        return this.strt4id;
    }
    
    public void setStrt4id(Long strt4id) {
        this.strt4id = strt4id;
    }
    
    @Column(name="STRT4_NAME", length=40)

    public String getStrt4Name() {
        return this.strt4Name;
    }
    
    public void setStrt4Name(String strt4Name) {
        this.strt4Name = strt4Name;
    }
    
    @Column(name="STRT5ID", precision=14, scale=0)

    public Long getStrt5id() {
        return this.strt5id;
    }
    
    public void setStrt5id(Long strt5id) {
        this.strt5id = strt5id;
    }
    
    @Column(name="STRT5_NAME", length=40)

    public String getStrt5Name() {
        return this.strt5Name;
    }
    
    public void setStrt5Name(String strt5Name) {
        this.strt5Name = strt5Name;
    }
    
    @Column(name="STRT_ABBR_NAME", length=40)

    public String getStrtAbbrName() {
        return this.strtAbbrName;
    }
    
    public void setStrtAbbrName(String strtAbbrName) {
        this.strtAbbrName = strtAbbrName;
    }
    
    @Column(name="STRT_ABBR", length=20)

    public String getStrtAbbr() {
        return this.strtAbbr;
    }
    
    public void setStrtAbbr(String strtAbbr) {
        this.strtAbbr = strtAbbr;
    }
    
    @Column(name="MIN_BGN_NBR", precision=14, scale=0)

    public Long getMinBgnNbr() {
        return this.minBgnNbr;
    }
    
    public void setMinBgnNbr(Long minBgnNbr) {
        this.minBgnNbr = minBgnNbr;
    }
    
    @Column(name="STAT_CD", length=1)

    public String getStatCd() {
        return this.statCd;
    }
    
    public void setStatCd(String statCd) {
        this.statCd = statCd;
    }
    
    @Column(name="SEG_NUM", precision=1, scale=0)

    public Short getSegNum() {
        return this.segNum;
    }
    
    public void setSegNum(Short segNum) {
        this.segNum = segNum;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="UPD_DATE", length=7)

    public Date getUpdDate() {
        return this.updDate;
    }
    
    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
    
    @Column(name="EMP_NBR", length=10)

    public String getEmpNbr() {
        return this.empNbr;
    }
    
    public void setEmpNbr(String empNbr) {
        this.empNbr = empNbr;
    }
    
    @Column(name="STAT_FLAG", length=1)

    public String getStatFlag() {
        return this.statFlag;
    }
    
    public void setStatFlag(String statFlag) {
        this.statFlag = statFlag;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DATA_DATE", length=7)

    public Date getDataDate() {
        return this.dataDate;
    }
    
    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }
    
    @Column(name="DATA_FLAG", length=1)

    public String getDataFlag() {
        return this.dataFlag;
    }
    
    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }
   








}