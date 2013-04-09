package com.cpst.emsadrdb.entity.base;
// default package

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="CP_BASE_POST_CITY"
)

public class BasePostCity  implements java.io.Serializable {


    // Fields    

     private BasePostCityId id;
     private String cityName;


    // Constructors

    /** default constructor */
    public BasePostCity() {
    }

	/** minimal constructor */
    public BasePostCity(BasePostCityId id) {
        this.id = id;
    }
    
    /** full constructor */
    public BasePostCity(BasePostCityId id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="postCode", column=@Column(name="POST_CODE", nullable=false, length=6) ), 
        @AttributeOverride(name="cityCode", column=@Column(name="CITY_CODE", nullable=false, length=6) ) } )

    public BasePostCityId getId() {
        return this.id;
    }
    
    public void setId(BasePostCityId id) {
        this.id = id;
    }
    
    @Column(name="CITY_NAME", length=40)

    public String getCityName() {
        return this.cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
   








}