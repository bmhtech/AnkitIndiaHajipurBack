package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

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
@Table(name="Nongoodsservice_party_dtls")
public class Nongoodsservice_party_dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	private int sl_no;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String party_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String cp_name;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String cp_contact;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String send_via;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tcs_applicable;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tcs_rate;
 	
 	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="nid")
 	private Nongoodsservice nongoodsservice;
}
