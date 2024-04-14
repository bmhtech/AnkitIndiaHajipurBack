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
@Table(name="Wm_loading_advice_bp_dtls")
public class Wm_loading_advice_bp_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String advice_id;
	
	@Column(columnDefinition = "varchar(50) default 0")
	private String advice_no;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String cust_name;
	
	private Long cust_ph;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String cust_fax;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String cust_mail;
	
	@Column(columnDefinition = "varchar(1000) default 'NA'")
	private String cust_add;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String cp_desg;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String cp_name;
	
	private Long cp_ph;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String cp_fax;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String cp_mail;
	
	@Column(columnDefinition = "varchar(1000) default 'NA'")
	private String cp_add;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String supp_name;
	
	@Column(columnDefinition = "varchar(100) default 0")
	private String supp_phone;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String supp_fax;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String supp_email;
	
	@Column(columnDefinition = "varchar(500) default 'NA'")
	private String supp_address;
           
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "wl_id")
    private  Wm_loading_advice wm_loading_advice;
}
