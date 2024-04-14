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

import lombok.Data;
import lombok.EqualsAndHashCode;
@Entity
@Table(name="Grnregisterreport_Dtls")
@Data
@EqualsAndHashCode(callSuper=false)
public class Grnregisterreport_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grnregisterid;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String grndate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String itemdesc;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String quantity;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String unit;

	@Column(columnDefinition="varchar(30) default 'NA'")
	private String rate;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "g_id")
    private Grnregisterreport grnregisterreport;
}
