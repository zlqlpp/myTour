package com.cpst.emsadrdb.entity.pmn;
// default package

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="CP_PMN_ROLE")

public class Role implements java.io.Serializable {


    // Fields    

     private Long rePkId;
     private String reName;
     private String reViewName;
     private Short rulLevel;
     private List<Resource> resources;
     private List<User> users;
     
    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},mappedBy="roles",targetEntity=User.class)
    public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	@ManyToMany(targetEntity=Resource.class,cascade={CascadeType.MERGE,CascadeType.PERSIST})   
    @JoinTable(name="CP_PMN_RL_ROLE_RES",joinColumns={@JoinColumn(name="re_pk_id")},inverseJoinColumns={@JoinColumn(name="rs_pk_id")}) 
    public List<Resource> getResources() {
		return resources;
	}


	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}


	/** default constructor */
    public Role() {
    }

    
    /** full constructor */
    public Role(String reName, String reViewName, Short rulLevel) {
        this.reName = reName;
        this.reViewName = reViewName;
        this.rulLevel = rulLevel;
    }

   
    // Property accessors
    @SequenceGenerator(name="generator",sequenceName="SQ_CP_PMN_ROLE_ID",allocationSize=1)@Id @GeneratedValue(strategy=SEQUENCE, generator="generator")
    
    @Column(name="RE_PK_ID", unique=true, nullable=false, precision=10, scale=0)

    public Long getRePkId() {
        return this.rePkId;
    }
    
    public void setRePkId(Long rePkId) {
        this.rePkId = rePkId;
    }
    
    @Column(name="RE_NAME", nullable=false, length=50)

    public String getReName() {
        return this.reName;
    }
    
    public void setReName(String reName) {
        this.reName = reName;
    }
    
    @Column(name="RE_VIEW_NAME", nullable=false, length=50)

    public String getReViewName() {
        return this.reViewName;
    }
    
    public void setReViewName(String reViewName) {
        this.reViewName = reViewName;
    }

    @Column(name="RUL_LEVEL", nullable=false, precision=3, scale=0)
	public Short getRulLevel() {
		return rulLevel;
	}

	public void setRulLevel(Short rulLevel) {
		this.rulLevel = rulLevel;
	}
    
    

    
   








}