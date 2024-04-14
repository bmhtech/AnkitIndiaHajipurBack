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
@Table(name="Jw_grn_partywitem_details")
public class Jw_grn_partywitem_details extends CommonProperties {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String jw_grn_tag;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String party;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String party_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String job_item;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String job_item_name;
	
	@Column(columnDefinition = "Double(10,3) default 0.000")
	private double qty;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "jpid")
	private Jw_grn_partytag_details jw_grn_partytag_details;
}
