package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="Pur_return_note")
public class Pur_return_note extends CommonProperties {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteid;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoteno;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String supplierid;
	 
	 @Column(columnDefinition="varchar(100) default 0")
	 private String suppliername;  
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String ser_item_type;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String ser_item_subtype;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String referance_id;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String businessunit;
	 
	 @Column(columnDefinition="varchar(100) default 0")
	 private String businessunitname; 
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnotedate;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String purreturnnoterefno;
	 
	 @Column(columnDefinition="varchar(100) default 0")
	 private String remark;  
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String confirmedby;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String approval;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String reason; 
	 
	 @Column(columnDefinition = "Double(10,2) default 0.00")
	 private double grandtotal;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String cust_ref_doc_no;
	 
	 @Column(columnDefinition="varchar(50) default 0")
	 private String date2;
	
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_note",cascade = CascadeType.ALL)
	 private Set<Pur_return_note_item_dtls> pur_return_note_item_dtls;
	 
	 @OneToOne(fetch = FetchType.EAGER,mappedBy="pur_return_note",cascade = CascadeType.ALL)
	 private Pur_return_note_trans_info pur_return_note_trans_info;
	 
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_note",cascade = CascadeType.ALL)
	 private Set<Pur_return_note_broker_dtls> pur_return_note_broker_dtls;
	 
	 @OneToOne(fetch = FetchType.EAGER,mappedBy="pur_return_note",cascade = CascadeType.ALL)
	 private Pur_return_note_weight_dtl pur_return_note_weight_dtl;
	 
	 @OneToOne(fetch = FetchType.EAGER,mappedBy="pur_return_note",cascade = CascadeType.ALL)
	 private Pur_return_note_shipment_dtls pur_return_note_shipment_dtls;
	 
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_note",cascade = CascadeType.ALL)
	 private Set<Pur_return_note_supplier_dtls> pur_return_note_supplier_dtls;
	 
	 @OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_note",cascade = CascadeType.ALL)
	 private Set<Pur_return_note_docs> pur_return_note_docs;
	 

}
