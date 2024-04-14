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
@Table(name="Pur_good_receipt_doc")
public class Pur_good_receipt_doc extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String grn_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'",nullable=false)
	private String grn_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String doc_name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="P_Ord_Rcv_Id")
	Pur_good_receipt pur_good_receipt;

}
