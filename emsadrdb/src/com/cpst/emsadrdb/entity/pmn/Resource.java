package com.cpst.emsadrdb.entity.pmn;
// default package

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="CP_PMN_RESOURCE")

public class Resource  implements java.io.Serializable {


    // Fields    

     private Long rsPkId;
     private String rsName;
     private String rsStr;
     private String rsType;
     private List<Role> roles;
     
    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},mappedBy="resources",targetEntity=Role.class) 
    public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}


	/** default constructor */
    public Resource() {
    }

    
    /** full constructor */
    public Resource(String rsName, String rsStr, String rsType) {
        this.rsName = rsName;
        this.rsStr = rsStr;
        this.rsType = rsType;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator",sequenceName="SQ_CP_PMN_RESOURCE_ID",allocationSize=1)@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="RS_PK_ID", unique=true, nullable=false, precision=10, scale=0)

    public Long getRsPkId() {
        return this.rsPkId;
    }
    
    public void setRsPkId(Long rsPkId) {
        this.rsPkId = rsPkId;
    }
    
    @Column(name="RS_NAME", nullable=false, length=50)

    public String getRsName() {
        return this.rsName;
    }
    
    public void setRsName(String rsName) {
        this.rsName = rsName;
    }
    
    @Column(name="RS_STR", nullable=false, length=200)

    public String getRsStr() {
        return this.rsStr;
    }
    
    public void setRsStr(String rsStr) {
        this.rsStr = rsStr;
    }
    
    @Column(name="RS_TYPE", nullable=false, length=1)

    public String getRsType() {
        return this.rsType;
    }
    
    public void setRsType(String rsType) {
        this.rsType = rsType;
    }
   








}