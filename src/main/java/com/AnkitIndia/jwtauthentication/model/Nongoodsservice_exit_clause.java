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
@Table(name="Nongoodsservice_exit_clause")
public class Nongoodsservice_exit_clause extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	
 	@Column(columnDefinition="varchar(50) default 0")
	private String nongoodsserviceid;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String term_nongoods_service;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String order_by;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String charges_descpt;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String reason;
 	
 	@Column(columnDefinition="TEXT")
	private String remarks;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String tot_term_chg;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String term_add;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String term_deduct;
 	
 	@Column(columnDefinition="varchar(50) default 0")
	private String net_term_chg;
 	
 	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "nid")
    private Nongoodsservice nongoodsservice;
 	

}
