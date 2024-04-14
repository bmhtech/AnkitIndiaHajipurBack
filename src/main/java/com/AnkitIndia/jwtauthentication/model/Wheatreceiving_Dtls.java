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
@Table(name="Wheatreceiving_Dtls")
public class Wheatreceiving_Dtls extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wheatreceiveid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	 
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String truckno;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String hub;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String hub_name;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String grade;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
   	private String gradename;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixtykgbags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sixtykgqty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ninetyfivekgbags;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ninetyfivekgqty;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String total;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String stackno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String date;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "w_id")
    private Wheatreceiving wheatreceiving;

}
