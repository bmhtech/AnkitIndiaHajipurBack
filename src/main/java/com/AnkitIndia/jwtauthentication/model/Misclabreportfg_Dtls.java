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
@Table(name="Misclabreportfg_Dtls")
public class Misclabreportfg_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String misclabreportfgid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String slno;
	 
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String itemid;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
   	private String item_name;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String batchno;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String time;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String moisture;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String colour;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String psd;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String wet_gluten;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String dry_gluten;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String qty_gluten;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String sv;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String c2cl4;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String odour;
    
    @Column(columnDefinition="varchar(50) default 'NA'")
	private String infestation;
    
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "l_id")
    private Misclabreportfg misclabreportfg;
}
