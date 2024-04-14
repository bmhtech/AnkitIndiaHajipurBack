package com.AnkitIndia.jwtauthentication.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Pur_Quality_Check_Details")
public class Pur_Quality_Check_Details extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quality_check_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qcno;
	
	private int sl_no; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String packing_name;
	
	private Long quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	private Long s_qty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String s_uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bags;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String warehouse;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qc_status;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="p_q_id")
	private Pur_Quality_Check pur_Quality_Check;
	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="pur_Quality_Check_Details",cascade=CascadeType.ALL)
	private Set<Pur_Quality_Check_Details_QcDetails> pur_Quality_Check_Details_QcDetails;
	
}
