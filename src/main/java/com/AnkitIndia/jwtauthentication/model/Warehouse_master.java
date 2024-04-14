package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="Warehouse_master")
@EntityListeners(AuditingEntityListener.class)
public class Warehouse_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String warehouse_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String warehouse_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String warehouse_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean warehouse_active;
	
	@Column(columnDefinition="varchar(150) default '0'")
	private String warehouse_remarks;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String businessunit_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean maintain_stack;
	
	@Column(columnDefinition="varchar(150) default '0'")
	private String warehouse_address;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String city_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String city_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dist_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String dist_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String state_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String state_name;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String country_name;
	
	private int pin_code;
	

	//Dynamic
    @OneToMany(fetch = FetchType.EAGER,mappedBy="warehouse_master",cascade =CascadeType.ALL) 
    private Set<Warehouse_stack_dtls> warehouse_stack_dtls;
	
}
