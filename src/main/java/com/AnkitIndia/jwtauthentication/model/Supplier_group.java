package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Supplier_group", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
        		"bp_grp_name"
        })
})
public class Supplier_group extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_group_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_group_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_grp_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean bp_grp_active;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String parent_group;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String main_group;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String control_acc;
	
}
