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
@Table(name="Pur_return_note_weight_dtl")
public class Pur_return_note_weight_dtl extends CommonProperties {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteid;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteno;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String ownuom;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double owngross;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double owntare;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double ownnet;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String ownpermitno;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String owndate;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String ownslipno;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String partyuom;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double partygross;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double partytare;
	 
	 @Column(columnDefinition = "Double(10,3) default 0.000")
	 private double partynet;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String partydate;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String partyslipno;

	 @OneToOne(cascade= CascadeType.ALL)
	 @JoinColumn(name = "prn_id")
	 private Pur_return_note pur_return_note;

}
