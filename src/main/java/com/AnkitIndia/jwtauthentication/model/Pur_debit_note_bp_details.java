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
@Table(name="Pur_debit_note_bp_details")
public class Pur_debit_note_bp_details extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String debitnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  debitnoteno;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String  supp_name;
	
	private Long supp_phone;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String  supp_fax;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String   supp_email;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String  supp_address;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String  cp_designation;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String  cp_name;
    
	private Long cp_phone;
    
	@Column(columnDefinition="varchar(100) default 0")
	private String  cp_fax;
    
	@Column(columnDefinition="varchar(100) default 0")
	private String  cp_email;
    
	@Column(columnDefinition="varchar(100) default 0")
	private String  cp_address;
	
		@OneToOne(cascade= CascadeType.ALL)
	    @JoinColumn(name = "scn_Id")
		private Pur_debit_note pur_debit_note;

}
