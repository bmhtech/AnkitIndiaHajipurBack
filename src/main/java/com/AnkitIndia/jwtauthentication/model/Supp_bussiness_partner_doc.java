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
@Table(name="Supp_bussiness_partner_doc")
public class Supp_bussiness_partner_doc extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String doc_name;
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String doc_pdf;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "BPart_Id")
    private Supp_bussiness_partner supp_bussiness_partner9;

}
