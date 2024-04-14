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
@Table(name="Granulation_Dtls")
public class Granulation_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String granulationreportid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	 
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String seivesid;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String itemid;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String eight;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String nine;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String ten;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String eleven;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String twelve;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String one;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String two;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String three;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String four;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String five;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String six;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String seven;
    
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "g_id")
    private Granulation granulation;
}
