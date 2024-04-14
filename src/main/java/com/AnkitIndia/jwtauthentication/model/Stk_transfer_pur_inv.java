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
@Table(name="Stk_transfer_pur_inv")
public class Stk_transfer_pur_inv extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_trans_pur_inv_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_trans_pur_inv_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_trans_pur_inv_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String created_by;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String vehicle_no;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String remarks;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double item_total;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String item_total_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double discount;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String discount_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double qc_deduction;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String qc_deduction_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String net_amt_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double amt_after_deduction;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String amt_after_deduction_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double add_tax;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String add_tax_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double post_tax_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String post_tax_amt_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double other_charges;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String other_charges_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double payable_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String payable_amt_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double add1;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String add1_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double add2;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String add2_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double roundoff_amt;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String roundoff_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double payable_party; 
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String payable_party_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double already_paid;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String already_paid_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2) default 0.00")
	private double net_payable_party;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String net_payable_party_gl_ac;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reference_id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String send_business_unit;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_pur_inv",cascade = CascadeType.ALL)
	private Set<Stk_transfer_pur_inv_item_dtls> stk_transfer_pur_inv_item_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_pur_inv",cascade = CascadeType.ALL)
	private Set<Stk_transfer_pur_inv_musterroll_dtls> stk_transfer_pur_inv_musterroll_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_pur_inv",cascade = CascadeType.ALL)
	private Stk_transfer_pur_inv_tax_info stk_transfer_pur_inv_tax_info;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_pur_inv",cascade = CascadeType.ALL)
	private Stk_transfer_pur_inv_bu_dtls stk_transfer_pur_inv_bu_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_pur_inv",cascade = CascadeType.ALL)
	private Set<Stk_transfer_pur_inv_docs> stk_transfer_pur_inv_docs;
}
