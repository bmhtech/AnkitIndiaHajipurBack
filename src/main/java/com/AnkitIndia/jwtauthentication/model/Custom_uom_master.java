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
@Table(name="Custom_uom_master")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Custom_uom_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String customuom_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String customuom_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String customuom_catg;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String uom_conv_fac;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cal_method;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String description;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String type_term;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean uom_active; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;
	
	
	
	@Column(columnDefinition="int(1) default '0'")
	private int decimalv;
	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
}
