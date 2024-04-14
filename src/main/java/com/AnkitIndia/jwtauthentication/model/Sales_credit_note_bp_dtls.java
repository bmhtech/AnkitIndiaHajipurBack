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
@Table(name="Sales_credit_note_bp_dtls")
public class Sales_credit_note_bp_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String creditnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String creditnoteno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sp_name;
	
	private Long sp_phone;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sp_fax;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sp_email;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String sp_address;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_designation;
	
	private Long cp_phone;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_fax;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_email;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_address;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "scn_id")
	private Sales_credit_note sales_credit_note;
}
