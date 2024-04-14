package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="Pur_L1_Selection")
public class Pur_L1_Selection extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String lsel_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String l1_doc_no;
	
	private Date date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String supplier_id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_L1_Selection",cascade = CascadeType.ALL)
	private Set<Pur_L1_Selection_Dtls> pur_L1_Selection_Dtls;

}
