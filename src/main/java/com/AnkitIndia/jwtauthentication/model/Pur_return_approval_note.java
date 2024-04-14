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
@Table(name="Pur_return_approval_note")
public class Pur_return_approval_note extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturntype;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ser_item_type;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ser_item_subtype;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String purchase_subtype;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String suppliername; 
	
	@Column(columnDefinition="varchar(50) default 0")
	private String supplier; 
	
	@Column(columnDefinition="varchar(30) default 0")
	private String purreturndate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String businessunit;  
	
	@Column(columnDefinition="varchar(100) default 0")
	private String businessunit_name;
   
	@Column(columnDefinition="varchar(50) default 0")
	private String returncriteria;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String returnbasedon;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String purreturnrefno;
	
	@Column(columnDefinition="varchar(200) default 0")
	private String remark; 
	
	@Column(columnDefinition="varchar(50) default 0")
	private String confirmedby;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String approval;
	
	@Column(columnDefinition="varchar(200) default 0")
	private String reason; 
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double  grandtotal;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String referance_id;
	
	@Column(columnDefinition = "varchar(50) default '0'")
	private String weighment_id;
	
	private int reapp_note_status;
	
	private int weighment_status;
	
	@Column(columnDefinition = "varchar(50) default 'Not Done'")
	private String prn_status;
	
	@Column(columnDefinition = "varchar(50) default 'Not Done'")
	private String loading_status;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String debit_note_id;
	
	@Column(columnDefinition="varchar(10) default 'NO'")
	private String debit_note_status;

	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String grnlist;
	
		@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_approval_note",cascade = CascadeType.ALL)
		private Set<Pur_return_approval_docs> pur_return_approval_docs;
		
		@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_return_approval_note",cascade = CascadeType.ALL)
		private Pur_return_approval_weight_dtl pur_return_approval_weight_dtl;
		
		@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_return_approval_note",cascade = CascadeType.ALL)
		private Pur_return_approval_trans_info pur_return_approval_trans_info;
		
		@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_return_approval_note",cascade = CascadeType.ALL)
		private Pur_return_approval_shipment_dtls pur_return_approval_shipment_dtls;
	 
	    @OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_approval_note",cascade = CascadeType.ALL)
		private Set<Pur_return_approval_item_dtls> pur_return_approval_item_dtls; 
		
		@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_approval_note",cascade = CascadeType.ALL)
		private Set<Pur_return_approval_broker_dtls> pur_return_approval_broker_dtls;
		
		@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_return_approval_note",cascade = CascadeType.ALL)
		private Set<Pur_return_approval_supplier_dtls> pur_return_approval_supplier_dtls;

}
