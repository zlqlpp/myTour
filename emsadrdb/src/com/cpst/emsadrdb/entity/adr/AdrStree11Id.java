package com.cpst.emsadrdb.entity.adr;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class AdrStree11Id  implements java.io.Serializable {


    // Fields    

     private Long strtId;
     private String distCd;


    // Constructors

    /** default constructor */
    public AdrStree11Id() {
    }

    
    /** full constructor */
    public AdrStree11Id(Long strtId, String distCd) {
        this.strtId = strtId;
        this.distCd = distCd;
    }

   
    // Property accessors

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
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AdrStree11Id) ) return false;
		 AdrStree11Id castOther = ( AdrStree11Id ) other; 
         
		 return ( (this.getStrtId()==castOther.getStrtId()) || ( this.getStrtId()!=null && castOther.getStrtId()!=null && this.getStrtId().equals(castOther.getStrtId()) ) )
 && ( (this.getDistCd()==castOther.getDistCd()) || ( this.getDistCd()!=null && castOther.getDistCd()!=null && this.getDistCd().equals(castOther.getDistCd()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStrtId() == null ? 0 : this.getStrtId().hashCode() );
         result = 37 * result + ( getDistCd() == null ? 0 : this.getDistCd().hashCode() );
         return result;
   }   





}