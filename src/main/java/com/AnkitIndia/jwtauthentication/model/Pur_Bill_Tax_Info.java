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
@Table(name="Pur_Bill_Tax_Info")
public class Pur_Bill_Tax_Info extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String company_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pan;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String gst;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String cin;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String tan;

	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
	private Pur_Bill pur_Bill;

}
