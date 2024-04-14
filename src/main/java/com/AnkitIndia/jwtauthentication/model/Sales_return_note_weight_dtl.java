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
@Table(name="Sales_return_note_weight_dtl")
public class Sales_return_note_weight_dtl extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String salesreturnnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesreturnnoteno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  ownuom;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double owngross;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double owntare;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double ownnet;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ownpermitno;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String owndate;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String ownslipno;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String partyuom;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double partygross;
	
	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double partytare;

	@Column(columnDefinition = "Decimal(10,3) default 0.000")
	private double partynet;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String partydate;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String partyslipno;
		
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "sr_id")
	private Sales_return_note sales_return_note;
}