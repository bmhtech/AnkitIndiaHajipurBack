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
@Table(name="Otherparameterreport_Dtls")
public class Otherparameterreport_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String otherparameterid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	 
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String itemid;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
   	private String item_name;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String ash;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String aia;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String alcohol;
    
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "o_id")
    private Otherparameterreport otherparameterreport;
}
