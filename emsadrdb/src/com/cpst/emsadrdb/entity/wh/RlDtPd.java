package com.cpst.emsadrdb.entity.wh;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 * RlDtPd entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="CP_WH_RL_DT_PD"
    ,schema="EMSMZWH"
)

public class RlDtPd  implements java.io.Serializable {


    // Fields    

     private RlDtPdId id;


    // Constructors

    /** default constructor */
    public RlDtPd() {
    }

    
    /** full constructor */
    public RlDtPd(RlDtPdId id) {
        this.id = id;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="dtPkCode", column=@Column(name="DT_PK_CODE", nullable=false, length=7) ), 
        @AttributeOverride(name="pdPkCode", column=@Column(name="PD_PK_CODE", nullable=false, length=6) ) } )

    public RlDtPdId getId() {
        return this.id;
    }
    
    public void setId(RlDtPdId id) {
        this.id = id;
    }
   








}