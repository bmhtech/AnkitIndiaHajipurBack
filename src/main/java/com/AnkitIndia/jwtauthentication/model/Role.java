package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Enumerated(EnumType.STRING)
    //@NaturalId
    @Column(length = 60)
    private String name;
    
    @Column(columnDefinition = "varchar(50) default '0'")
	private String role_id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String parent_role_id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String parent_role;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String description;
	
	@Lob
	private String roleaccessjson;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "user_role")
    private User_Role_Access user_Role_Access;
	
    public Role() {
		// TODO Auto-generated constructor stub
	}
    
    
    
    public Role(Long id, String name, String role_id, String parent_role_id, String parent_role, String description) {
		super();
		this.id = id;
		this.name = name;
		this.role_id = role_id;
		this.parent_role_id = parent_role_id;
		this.parent_role = parent_role;
		this.description = description;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getParent_role_id() {
		return parent_role_id;
	}

	public void setParent_role_id(String parent_role_id) {
		this.parent_role_id = parent_role_id;
	}

	public String getParent_role() {
		return parent_role;
	}

	public void setParent_role(String parent_role) {
		this.parent_role = parent_role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleaccessjson() {
		return roleaccessjson;
	}
	
	public void setRoleaccessjson(String roleaccessjson) {
		this.roleaccessjson = roleaccessjson;
	}
	
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", role_id=" + role_id + ", parent_role_id=" + parent_role_id
				+ ", parent_role=" + parent_role + ", description=" + description + ", getId()=" + getId()
				+ ", getName()=" + getName() + ", getRole_id()=" + getRole_id() + ", getParent_role_id()="
				+ getParent_role_id() + ", getParent_role()=" + getParent_role() + ", getDescription()="
				+ getDescription() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
    
}