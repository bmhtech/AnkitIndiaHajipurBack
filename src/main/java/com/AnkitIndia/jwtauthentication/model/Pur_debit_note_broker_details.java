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
@Table(name="Pur_debit_note_broker_details")
public class Pur_debit_note_broker_details extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String debitnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  debitnoteno;
	
	private Long sl_no;
    
	@Column(columnDefinition="varchar(100) default 0")
	private String broker_name;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double brokerage_amt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String broker_other_info;
    
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double up_brokerage_amt;
    
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double total_brokerage;
	
	
		@ManyToOne(cascade= CascadeType.ALL)
		@JoinColumn(name = "scn_id")
		private Pur_debit_note pur_debit_note;

}
