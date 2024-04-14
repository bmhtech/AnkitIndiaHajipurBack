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
@Table(name="Stk_transfer_sales_inv")
public class Stk_transfer_sales_inv extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String stk_sales_inv_id;
	
	@Column(columnDefinition="varchar(30) default '0'")
	private String stk_sales_inv_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_sales_inv_date;
	
	@Column(columnDefinition="varchar(1000) default '0'")
	private String stk_sales_inv_order_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String stk_sales_inv_order_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String challan;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String e_invoice_no;
	
	@Column(columnDefinition="varchar(1000) default '0'")
	private String refchallanno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String refchallandate;
	
	@Column(columnDefinition = "Double(10,2) default 0.00")
	private double item_total;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String item_total_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double discount;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String discount_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double net_total;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String net_total_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double tax_total;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String tax_total_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double total_bill_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String total_bill_amt_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double applicable_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String applicable_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double adj1_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String adj1_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double adj2_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String adj2_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double roundoff_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String roundoff_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double final_bill_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String final_bill_amt_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double payable_amt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String payable_amt_gl_ac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double tcsamt;
    
    @Column(columnDefinition="varchar(50) default '0'")
	private String tcsglac;
    
    @Column(columnDefinition = "Double(10,2) default 0.00")
	private double grand_total;
    
    @Column(columnDefinition="varchar(200) default '0'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String reference_id;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String stk_sales_mutlipledates;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String multiplechallandate;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Set<Stocksaleitem_groupwise_hsnsumm> stocksaleitem_groupwise_hsnsumm;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Set<Stocksaleitem_groupwise_summ> stocksaleitem_groupwise_summ;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Set<Stocksaleitem_groupwise_taxsumm> stocksaleitem_groupwise_taxsumm;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Set<Stk_transfer_sales_inv_item_dtls> stk_transfer_sales_inv_item_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Set<Stk_transfer_sales_inv_docs>stk_transfer_sales_inv_docs;
    
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Stk_transfer_sales_inv_tax_info stk_transfer_sales_inv_tax_info;
    
	@OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Stk_transfer_sales_inv_bu_dtls stk_transfer_sales_inv_bu_dtls;
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Set<Stk_transfer_sales_inv_trans_dtls>stk_transfer_sales_inv_trans_dtls;
    
    @OneToOne(fetch = FetchType.EAGER,mappedBy="sTransfer_sales_inv",cascade = CascadeType.ALL)
	private Stk_transfer_sales_inv_shipment_dtls stk_transfer_sales_inv_shipment_dtls;
}
