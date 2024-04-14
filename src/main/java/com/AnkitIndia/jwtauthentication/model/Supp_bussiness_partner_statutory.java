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
@Table(name="Supp_bussiness_partner_statutory")
public class Supp_bussiness_partner_statutory extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	private boolean registered;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gst_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cin_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tan_no;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String supplier_type;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "BPart_Id")
    private Supp_bussiness_partner supp_bussiness_partner5;

}
