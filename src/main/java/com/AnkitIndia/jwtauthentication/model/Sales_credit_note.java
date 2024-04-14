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
@Table(name="Sales_credit_note")
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "Sales_credit_note.saveCreditnoteAccount", 
	  procedureName = "saveCreditnoteAccount", parameters = {
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "referencedate", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "drawnon", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "modified_type", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "narration_dtls", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucher", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteid", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteno", type = String.class),
	    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)}),
	@NamedStoredProcedureQuery(name = "Sales_credit_note.callCreditnoteAccInsertPlus", 
	  procedureName = "callCreditnoteAccInsertPlus", parameters = {
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "branch", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "entrydate", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "voucherno", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "uniquevoucher", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "ledgerid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "amount", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "narration", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_on", type = LocalDateTime.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "inserted_by", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "fin_year", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "subgrps", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "groupcode", type = String.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "gstcal", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "percentage", type = double.class),@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteid", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.IN, name = "creditnoteno", type = String.class),
			  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
})
public class Sales_credit_note extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String creditnoteid;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String creditnoteno;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String creditnotetype;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String invoice_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String invoice_typename;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String business_unitname;
	
	@Column(columnDefinition="varchar(20) default 0")
	private String creditnotedate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String party;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String partyname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String challan;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String e_invoice_no;

	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double item_total;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String item_total_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double discount;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String discount_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double net_total;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String net_total_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double tax_total;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String tax_total_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double total_bill_amt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String total_bill_amt_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double applicable_amt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String applicable_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double adj1_amt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String adj1_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double adj2_amt;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String adj2_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double roundoff_amt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String roundoff_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double final_bill_amt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String final_bill_amt_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double payable_amt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String payable_amt_gl_ac;
	
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double tcsamt;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String tcsglac;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String salesorderno;
    
	@Column(columnDefinition="varchar(20) default 0")
	private String salesorderdate;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String allsalesorderdate;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String refchallanno;
    
	@Column(columnDefinition="varchar(20) default 0")
	private String refchallandate;
    
	@Column(columnDefinition = "Double(10,2) default 0.0")
	private double grand_total;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_id;
    
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String waybill;
	
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
	
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Sales_credit_note_bp_dtls sales_credit_note_bp_dtls;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Sales_credit_note_item_dtls_for_jobwork>  sales_credit_note_item_dtls_for_jobwork;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Sales_credit_note_item_dtls_for_jobwork_price>  sales_credit_note_item_dtls_for_jobwork_price;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Sales_credit_note_broker_dtls>  sales_credit_note_broker_dtls;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Sales_credit_note_docs>  sales_credit_note_docs;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Sales_credit_note_item_dtls>  sales_credit_note_item_dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Sales_credit_note_payment_dtls sales_credit_note_payment_dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Sales_credit_note_shipment_dtls sales_credit_note_shipment_dtls;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Sales_credit_note_tax_info sales_credit_note_tax_info;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)	
	private Set<Sales_credit_note_trans_dtls> sales_credit_note_trans_dtls ;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Credit_item_groupwise_summ> credit_item_groupwise_summ;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Credit_item_groupwise_taxsumm> credit_item_groupwise_taxsumm;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy="sales_credit_note",cascade = CascadeType.ALL)
	private Set<Credit_item_groupwise_hsnsumm> credit_item_groupwise_hsnsumm;
}
