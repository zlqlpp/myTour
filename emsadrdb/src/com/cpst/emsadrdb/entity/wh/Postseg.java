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
@Table(name = "CP_WH_POSTSEG")
public class Postseg implements java.io.Serializable {

	// Fields

	private Long pgPkCode;
	//private String dtPkCode;
	private Long dmPkCode;
	//private Short pgSno;
	private String pgName;
	private String pgAliasName;
	private String pgIsReceive;
	private String pgIsReceiveName;//翻译
	private String pgIsDelivery;
	private String pgIsDeliveryName;//翻译
	private District district;
	
	@Transient
	public String getPgIsReceiveName() {
		if(pgIsReceive!=null){
			if(pgIsReceive.equals("0")){
				pgIsReceiveName="否";
			}else{
				pgIsReceiveName="是";
			}
		}
		return pgIsReceiveName;
	}

	public void setPgIsReceiveName(String pgIsReceiveName) {
		this.pgIsReceiveName = pgIsReceiveName;
	}
	@Transient
	public String getPgIsDeliveryName() {
		if(pgIsDelivery!=null){
			if(pgIsDelivery.equals("0")){
				pgIsDeliveryName="否";
			}else{
				pgIsDeliveryName="是";
			}
		}
		return pgIsDeliveryName;
	}

	public void setPgIsDeliveryName(String pgIsDeliveryName) {
		this.pgIsDeliveryName = pgIsDeliveryName;
	}

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
	public Postseg() {
	}

	/** minimal constructor */
	public Postseg(Long pgPkCode, 
			String pgName, String pgIsReceive, String pgIsDelivery) {
		this.pgPkCode = pgPkCode;
		//this.pgSno = pgSno;
		this.pgName = pgName;
		this.pgIsReceive = pgIsReceive;
		this.pgIsDelivery = pgIsDelivery;
	}

	/** full constructor */
	public Postseg(Long pgPkCode, Long dmPkCode,
			String pgName, String pgAliasName, String pgIsReceive,
			String pgIsDelivery) {
		this.pgPkCode = pgPkCode;
		this.dmPkCode = dmPkCode;
		this.pgName = pgName;
		this.pgAliasName = pgAliasName;
		this.pgIsReceive = pgIsReceive;
		this.pgIsDelivery = pgIsDelivery;
	}

	// Property accessors
	@SequenceGenerator(name="generator",sequenceName="SQ_CP_WH_POSTSEG_ID",allocationSize=1)@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    @Column(name="PG_PK_CODE", unique=true, nullable=false, precision=10, scale=0)
	public Long getPgPkCode() {
		return this.pgPkCode;
	}

	public void setPgPkCode(Long pgPkCode) {
		this.pgPkCode = pgPkCode;
	}


	@Column(name = "DM_PK_CODE", nullable = true, precision = 10, scale = 0)
	public Long getDmPkCode() {
		return this.dmPkCode;
	}

	public void setDmPkCode(Long dmPkCode) {
		this.dmPkCode = dmPkCode;
	}

	/*@Column(name = "PG_SNO", nullable = false, precision = 4, scale = 0)
	public Short getPgSno() {
		return this.pgSno;
	}

	public void setPgSno(Short pgSno) {
		this.pgSno = pgSno;
	}*/

	@Column(name = "PG_NAME", nullable = false, length = 50)
	public String getPgName() {
		return this.pgName;
	}

	public void setPgName(String pgName) {
		this.pgName = pgName;
	}

	@Column(name = "PG_ALIAS_NAME", length = 50)
	public String getPgAliasName() {
		return this.pgAliasName;
	}

	public void setPgAliasName(String pgAliasName) {
		this.pgAliasName = pgAliasName;
	}

	@Column(name = "PG_IS_RECEIVE", nullable = false, length = 1)
	public String getPgIsReceive() {
		return this.pgIsReceive;
	}

	public void setPgIsReceive(String pgIsReceive) {
		this.pgIsReceive = pgIsReceive;
	}

	@Column(name = "PG_IS_DELIVERY", nullable = false, length = 1)
	public String getPgIsDelivery() {
		return this.pgIsDelivery;
	}

	public void setPgIsDelivery(String pgIsDelivery) {
		this.pgIsDelivery = pgIsDelivery;
	}

}