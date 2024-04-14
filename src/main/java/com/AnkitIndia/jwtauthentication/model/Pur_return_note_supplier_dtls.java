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
@Table(name="Pur_return_note_supplier_dtls")
public class Pur_return_note_supplier_dtls extends CommonProperties {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Long id;
	
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteid;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteno;
	 
	 private int slno;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String spcode;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String spname;
	 
	 private long spcontact;

	 @ManyToOne(cascade= CascadeType.ALL)
	 @JoinColumn(name = "prn_id")
	 private Pur_return_note pur_return_note;

}
