package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
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

import com.AnkitIndia.jwtauthentication.security.services.Pur_Bill_app_chgs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Pur_Bill")
public class Pur_Bill extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_id;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String pur_bill_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String purbillno;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String bill_date;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String billdate;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String supplier;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean item_type;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String purchase_type;
	
	@Column(columnDefinition = "varchar(100) default 'NA'")
	private String purchase_typename;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String purchase_subtype;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String created_by;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String truck_no;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String vehicleno;
	
	@Column(columnDefinition = "varchar(500) default 'NA'")
	private String remarks;

	@Column(columnDefinition = "Double(10,2)")
	private double item_total;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String item_total_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double discount;
    
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String discount_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double qc_deduction;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String qc_deduction_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double net_amt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String net_amt_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double amt_after_deduction;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String amt_after_deduction_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double add_tax;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String add_tax_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double post_tax_amt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String post_tax_amt_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double other_charges;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String other_charges_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double payable_amt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String payable_amt_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double add1;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String add1_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double add2;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String add2_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double roundoff_amt;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String roundoff_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double payable_party;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String payable_party_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double already_paid;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String already_paid_gl_ac;
	
	@Column(columnDefinition = "Double(12,2)")
	private double net_payable_party;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String net_payable_party_gl_ac;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String referance_id;
	
	@Column(columnDefinition = "varchar(20) default 'NA'")
	private String payment_date;
	
	@Column(columnDefinition = "Double(10,2)")
	private double upfrontbrokerage;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String upfrontbrokerage_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double claim1;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String claim1_gl_ac;
	
	@Column(columnDefinition = "Double(10,2)")
	private double claim2;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String claim2_gl_ac;
	
	@Column(columnDefinition = "varchar(50) default 'NA'")
	private String app_chgs_id;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tot_amt;
	
	@Column(columnDefinition = "varchar(100)")
	private String add1_remarks;
	
	@Column(columnDefinition = "varchar(100) default")
	private String add2_remarks;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String referance_type;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String supp_ref_doc;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String supp_ref_docno;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String supp_ref_doc_date;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String adviceno;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String state;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String billstatus;
	
	@Column(columnDefinition="varchar(10) default 'No'")
	private String return_apv_status;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String purreturnid;
	
	@Column(columnDefinition = "Double(10,2)")
	private double store_taxamt;
	
	@Column(columnDefinition = "Double(10,2)")
	private double store_frieghtcharges;
	
	@Column(columnDefinition = "Double(10,2)")
	private double allstorecharges;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String store_charges;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String store_charges_name;
	
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String store_frieghtcharges_gl_ac;
	
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Pur_Bill_Item_Details> pur_Bill_Item_Details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Pur_Bill_Musterroll_Details> pur_Bill_Musterroll_Details;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade =CascadeType.ALL)
	private Pur_Bill_Tax_Info pur_Bill_Tax_Info;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Pur_Bill_Broker_Details> pur_Bill_Broker_Details;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade =CascadeType.ALL)
	private Pur_Bill_Bp_Details pur_Bill_Bp_Details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Pur_Bill_Docs> pur_Bill_Docs;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade =CascadeType.ALL)
	private Pur_Bill_Account_Info pur_Bill_Account_Info;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Pur_Bill_app_chgs> pur_Bill_app_chgs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Pur_bill_store_chgs> pur_bill_store_chgs;
	
	
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Purchase_item_groupwise_summ> purchase_item_groupwise_summ;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Purchase_item_groupwise_taxsumm> purchase_item_groupwise_taxsumm;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Bill",cascade = CascadeType.ALL)
	private Set<Purchase_item_groupwise_hsnsumm> purchase_item_groupwise_hsnsumm;

}
