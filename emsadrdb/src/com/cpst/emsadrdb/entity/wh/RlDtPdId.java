package com.cpst.emsadrdb.entity.wh;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * RlDtPdId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class RlDtPdId  implements java.io.Serializable {


    // Fields    

     private String dtPkCode;
     private String pdPkCode;


    // Constructors

    /** default constructor */
    public RlDtPdId() {
    }

    
    /** full constructor */
    public RlDtPdId(String dtPkCode, String pdPkCode) {
        this.dtPkCode = dtPkCode;
        this.pdPkCode = pdPkCode;
    }

   
    // Property accessors

    @Column(name="DT_PK_CODE", nullable=false, length=7)

    public String getDtPkCode() {
        return this.dtPkCode;
    }
    
    public void setDtPkCode(String dtPkCode) {
        this.dtPkCode = dtPkCode;
    }

    @Column(name="PD_PK_CODE", nullable=false, length=6)

    public String getPdPkCode() {
        return this.pdPkCode;
    }
    
    public void setPdPkCode(String pdPkCode) {
        this.pdPkCode = pdPkCode;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RlDtPdId) ) return false;
		 RlDtPdId castOther = ( RlDtPdId ) other; 
         
		 return ( (this.getDtPkCode()==castOther.getDtPkCode()) || ( this.getDtPkCode()!=null && castOther.getDtPkCode()!=null && this.getDtPkCode().equals(castOther.getDtPkCode()) ) )
 && ( (this.getPdPkCode()==castOther.getPdPkCode()) || ( this.getPdPkCode()!=null && castOther.getPdPkCode()!=null && this.getPdPkCode().equals(castOther.getPdPkCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getDtPkCode() == null ? 0 : this.getDtPkCode().hashCode() );
         result = 37 * result + ( getPdPkCode() == null ? 0 : this.getPdPkCode().hashCode() );
         return result;
   }   





}