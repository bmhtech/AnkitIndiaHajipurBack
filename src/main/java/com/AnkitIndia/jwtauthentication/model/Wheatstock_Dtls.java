
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
@Table(name="Wheatstock_Dtls")
public class Wheatstock_Dtls  extends CommonProperties{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheatreceiveid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	 
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String stack;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String wheat_grade;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String wheat_grade_name;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String openingbags;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
   	private String openingqty;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String openingloose;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receiptbags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receiptqty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String receiptloose;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String issuebags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String issueqty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String issueloose;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closingbags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closingqty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String closingloose;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "w_id")
    private Wheatreceiving wheatreceiving;
}