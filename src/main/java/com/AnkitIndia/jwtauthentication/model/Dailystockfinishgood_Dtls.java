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
@Table(name="Dailystockfinishgood_Dtls")
public class Dailystockfinishgood_Dtls extends CommonProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String dailystockid;
	
	private int slno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String itemname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String openingstock;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String production;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sale;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String conversion;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closingstock;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "ds_id")
    private Dailystockfinishgood dailystockfinishgood;
}
