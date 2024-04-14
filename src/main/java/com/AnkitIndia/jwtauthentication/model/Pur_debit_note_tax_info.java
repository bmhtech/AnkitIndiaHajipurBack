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
@Table(name="Pur_debit_note_tax_info")
public class Pur_debit_note_tax_info extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String debitnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  debitnoteno;
	
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  pan;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String  gst;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  cin;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String  tan;
	
		@OneToOne(cascade= CascadeType.ALL)
	    @JoinColumn(name = "scn_Id")
		private Pur_debit_note pur_debit_note;

}
