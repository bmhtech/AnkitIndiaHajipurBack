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
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
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
@Table(name="Sales_Invoice")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Sales_Invoice.saveReceiptAccount", 
	  procedureName = "saveReceiptAccount", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucher", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Sales_Invoice.callRcptAccInsertPlus", 
	  procedureName = "callRcptAccInsertPlus", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gstcal", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "percentage", type = double.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
})
public class Sales_Invoice extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoiceno;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String invoice_type;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_date;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoicedate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String party;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String partyname;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String challan;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String e_invoice_no;
	
	@Column(columnDefinition = "Double(20,2) default 0.0")
	private double grand_total;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean brokage_app;

	@Column(columnDefinition="varchar(150) default 0")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesorderno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesorderdate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String refchallanno;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String refchallandate;
	
	@Column(columnDefinition = "Double(20,2) default 0.0")
	private double item_total;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_total_gl_ac;
    
	@Column(columnDefinition = "Double(20,2) default 0.0")
	private double discount;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String discount_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double net_total;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String net_total_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double tax_total;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String tax_total_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double total_bill_amt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String total_bill_amt_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double applicable_amt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String applicable_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double adj1_amt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String adj1_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double adj2_amt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String adj2_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double roundoff_amt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String roundoff_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double final_bill_amt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String final_bill_amt_gl_ac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double tcsamt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String tcsglac;
    
    @Column(columnDefinition = "Double(20,2) default 0.0")
	private double payable_amt;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String payable_amt_gl_ac;
    
    @Column(columnDefinition="varchar(50) default 0")
	private String reference_id;
    
    @Column(columnDefinition="tinyint(1) default 0")
	private boolean payment_status;
    
     @Column(columnDefinition="varchar(50) default 'NA'")
	 private String salereturn_id;
	 
	 @Column(columnDefinition="varchar(10) default 'NO'")
	 private String sales_returnstatus;
	 
	 @Column(columnDefinition="varchar(10) default 'NO'")
	 private String return_approval_status;
	 
	 @Column(columnDefinition="varchar(20)")
	 private String app_chgs_id;
	 
	 @Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String adviceno;
	
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String invoice_type_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String businessunitname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
    private String  cust_refdocno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cust_ref_doc_date;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String state;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String waybill;
	
	 @Column(columnDefinition="tinyint(1) default 0")
	 private boolean jobwork;
	 
	 @Column(columnDefinition="tinyint(1) default 0")
	 private boolean create_einvoice;
	 
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean cencel_einvoice;
	
	@Column(columnDefinition="tinyint(1) default 0")
	 private boolean create_ewaybill;
	 
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean cencel_ewaybill;
	
	@Column(columnDefinition="tinyint(1) default 0")
	 private boolean create_ewaybill_wo_invoice;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String policyno;
	
	@Column(columnDefinition="varchar(5) default 'NA'")
	private String gststatus;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String asn_no;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Sales_Invoice_Item_Dtls> sales_Invoice_Item_Dtls;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Sales_Invoice_Item_Dtls_for_jobwork> sales_Invoice_Item_Dtls_for_jobwork;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Sales_Invoice_Item_Dtls_for_jobwork_price> sales_Invoice_Item_Dtls_for_jobwork_price;
	
	
	
	
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Sales_Invoice_Docs> sales_Invoice_Docs;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Sales_Invoice_Broker_Dtls> sales_Invoice_Broker_Dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Sales_Invoice_BP_Dtls sales_Invoice_BP_Dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Sales_Invoice_Tax_Info sales_Invoice_Tax_Info;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Sales_Invoice_Trans_Dtls> sales_Invoice_Trans_Dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Sales_Invoice_Shipment_Dtls sales_Invoice_Shipment_Dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Sales_Invoice_Payment_Dtls sales_Invoice_Payment_Dtls;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Item_groupwise_summ> item_groupwise_summ;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Item_groupwise_taxsumm> item_groupwise_taxsumm;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_Invoice",cascade = CascadeType.ALL)
	private Set<Item_groupwise_hsnsumm> item_groupwise_hsnsumm;
	
	
}
