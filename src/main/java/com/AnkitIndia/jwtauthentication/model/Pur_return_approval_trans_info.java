package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="Pur_return_approval_trans_info")
public class Pur_return_approval_trans_info extends CommonProperties {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transborneby;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String modeoftrans;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicleid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String vehicleno;

	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double freightamt;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double  advpaid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String chargecode;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transcode;
		
		@OneToOne(cascade= CascadeType.ALL)
		@JoinColumn(name = "pr_id")
		private Pur_return_approval_note pur_return_approval_note;


}
