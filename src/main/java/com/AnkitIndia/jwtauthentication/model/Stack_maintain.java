package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Stack_maintain")
public class Stack_maintain extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack_id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String b_unit;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String b_unitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String grn_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String supplier;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicle_no;
	
	@Column(columnDefinition = "Double(10,0) default 0")
	private double total_grn_pkt;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double total_grn_item;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="stack_maintain",cascade=CascadeType.ALL)
	private Set<Stack_maintain_details> stack_maintain_details;
}