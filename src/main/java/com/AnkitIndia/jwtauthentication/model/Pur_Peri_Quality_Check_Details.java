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
@Table(name="Pur_Peri_Quality_Check_Details")
public class Pur_Peri_Quality_Check_Details extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;  
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String peri_qc_id;
	
	private int sl_no; 
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing;
	
	private Long quantity;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String uom;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bags;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String warehouse;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stack;
	
	private Long sample_size;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qc;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="p_qc_id")
	private Pur_Peri_Quality_Check pur_Peri_Quality_Check;

}
