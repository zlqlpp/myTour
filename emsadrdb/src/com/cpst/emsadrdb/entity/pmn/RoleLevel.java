package com.cpst.emsadrdb.entity.pmn;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="CP_PMN_ROLE_LEVEL")

public class RoleLevel  implements java.io.Serializable {


    // Fields    

     private Short rulLevel;
     private String rulName;


    // Constructors

    /** default constructor */
    public RoleLevel() {
    }

    
    /** full constructor */
    public RoleLevel(Short rulLevel, String rulName) {
        this.rulLevel = rulLevel;
        this.rulName = rulName;
    }

   
    // Property accessors
    @Id 
    
    @Column(name="RUL_LEVEL", unique=true, nullable=false, precision=3, scale=0)

    public Short getRulLevel() {
        return this.rulLevel;
    }
    
    public void setRulLevel(Short rulLevel) {
        this.rulLevel = rulLevel;
    }
    
    @Column(name="RUL_NAME", nullable=false, length=50)

    public String getRulName() {
        return this.rulName;
    }
    
    public void setRulName(String rulName) {
        this.rulName = rulName;
    }
   








}