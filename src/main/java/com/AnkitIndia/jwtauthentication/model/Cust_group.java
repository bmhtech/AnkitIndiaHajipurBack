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
@Table(name="Cust_group", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
        		"grp_name"
        })
})
public class Cust_group extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bus_part_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean bus_part_active;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grp_code;
	
	@Column(columnDefinition = "varchar(100) default '0'")
	private String grp_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parent_group;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ctrl_acc;
	
}
