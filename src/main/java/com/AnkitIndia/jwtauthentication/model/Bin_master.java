package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Bin_master")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Bin_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bin_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bin_code;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String bin_description;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String warehouse_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String warehouse_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String businessunit_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  bin_active;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bin_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bin_typename;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String empty_bin_height;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String empty_bin_length;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bin_volume;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String bin_capacity_kg;
	
}
