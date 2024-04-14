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
@Table(name="Wm_unload_advice_bp_dtls")
public class Wm_unload_advice_bp_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String unadviceid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String unadviceno;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sp_name;
	
	private Long sp_phone;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sp_fax;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sp_email;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String sp_address;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_designation;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_name;
	
	private Long cp_phone;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_fax;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_email;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String cp_address;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wmula_id")
    private Wm_unload_advice wm_unload_advice;
}
