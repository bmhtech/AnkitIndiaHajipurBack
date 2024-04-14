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
@Table(name="Trans_bussiness_partner_statutory")
public class Trans_bussiness_partner_statutory extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_code;
	
	private boolean registered;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gst_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cin_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tan_no;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "TP_Id")
    private Trans_bussiness_partner trans_bussiness_partner3;

}
