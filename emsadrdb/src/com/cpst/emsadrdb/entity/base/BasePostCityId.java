package com.cpst.emsadrdb.entity.base;
// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable

public class BasePostCityId  implements java.io.Serializable {


    // Fields    

     private String postCode;
     private String cityCode;


    // Constructors

    /** default constructor */
    public BasePostCityId() {
    }

    
    /** full constructor */
    public BasePostCityId(String postCode, String cityCode) {
        this.postCode = postCode;
        this.cityCode = cityCode;
    }

   
    // Property accessors

    @Column(name="POST_CODE", nullable=false, length=6)

    public String getPostCode() {
        return this.postCode;
    }
    
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Column(name="CITY_CODE", nullable=false, length=6)

    public String getCityCode() {
        return this.cityCode;
    }
    
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BasePostCityId) ) return false;
		 BasePostCityId castOther = ( BasePostCityId ) other; 
         
		 return ( (this.getPostCode()==castOther.getPostCode()) || ( this.getPostCode()!=null && castOther.getPostCode()!=null && this.getPostCode().equals(castOther.getPostCode()) ) )
 && ( (this.getCityCode()==castOther.getCityCode()) || ( this.getCityCode()!=null && castOther.getCityCode()!=null && this.getCityCode().equals(castOther.getCityCode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getPostCode() == null ? 0 : this.getPostCode().hashCode() );
         result = 37 * result + ( getCityCode() == null ? 0 : this.getCityCode().hashCode() );
         return result;
   }   




}