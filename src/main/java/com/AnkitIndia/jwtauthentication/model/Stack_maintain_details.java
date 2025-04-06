package com.AnkitIndia.jwtauthentication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Stack_maintain_details")
public class Stack_maintain_details extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack_id;
	
	private int slno;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String packing;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String packing_name;

	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String warehouse;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String warehouse_name;	
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String stack;
	
	@Column(columnDefinition = "Double(10,0) default 0")
	private double stack_pack_qty;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double stack_item_qty;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="st_id")
	Stack_maintain stack_maintain;
}