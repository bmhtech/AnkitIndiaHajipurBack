/*package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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
import javax.validation.constraints.Size;

@Entity
@Table(name="Stk_Transfer_Invoice")
public class Stk_Transfer_Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String stk_invoice_id;
	
	@Size(max = 50)
	private String stk_invoice_no;
	
	private Date stk_invoice_date;
	
	@Size(max = 50)
	private String business_unit;
	
	@Size(max = 50)
	private String payment_terms;
	
	@Size(max = 50)
	private String stk_invoice_order_no;
	
	@Size(max = 50)
	private String pay_due_days;
	
	@Size(max = 50)
	private String  trans_code;
	
	@Size(max = 50)
	private String narration;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double item_total;
	
	@Size(max = 50)
	private String item_total_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double tax_total;
	
	@Size(max = 50)
	private String tax_total_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double transporter_amt;
	
	@Size(max = 50)
	private String transporter_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double applicable_amt;
	
	@Size(max = 50)
	private String applicable_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double roundoff_amt;
	
	@Size(max = 50)
	private String roundoff_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double adj1_amt;
	
	@Size(max = 50)
	private String adj1_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double adj2_amt;
	
	@Size(max = 50)
	private String adj2_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double net_r_value;
	
	@Size(max = 50)
	private String net_gl_ac;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double grand_total;
	
	@Size(max = 50)
	private String company_id;
	
	@Size(max = 20)
	private String fin_year;
	
	@Size(max = 50)
	private String modified_type;
	
	private LocalDateTime inserted_on;
	
	@Size(max = 50)
	private String inserted_by;
	
	private LocalDateTime updated_on;
	
	@Size(max = 50)
	private String updated_by;
	
	private LocalDateTime deleted_on;
	
	@Size(max = 50)
	private String deleted_by;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Invoice",cascade = CascadeType.ALL)
	private Set<Stk_Transfer_Invoice_Item_Dtls> stk_Transfer_Invoice_Item_Dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Invoice",cascade = CascadeType.ALL)
	private Set<Stk_Transfer_Invoice_Docs> stk_Transfer_Invoice_Docs;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Invoice",cascade = CascadeType.ALL)
	private Stk_Transfer_Invoice_Bu_Dtls stk_Transfer_Invoice_Bu_Dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="stk_Transfer_Invoice",cascade = CascadeType.ALL)
	private Stk_Transfer_Invoice_Tax_Info stk_Transfer_Invoice_Tax_Info;

	public Stk_Transfer_Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stk_Transfer_Invoice(Long id, @Size(max = 50) String stk_invoice_id, @Size(max = 50) String stk_invoice_no,
			Date stk_invoice_date, @Size(max = 50) String business_unit, @Size(max = 50) String payment_terms,
			@Size(max = 50) String stk_invoice_order_no, @Size(max = 50) String pay_due_days,
			@Size(max = 50) String trans_code, @Size(max = 50) String narration, double item_total,
			@Size(max = 50) String item_total_gl_ac, double tax_total, @Size(max = 50) String tax_total_gl_ac,
			double transporter_amt, @Size(max = 50) String transporter_gl_ac, double applicable_amt,
			@Size(max = 50) String applicable_gl_ac, double roundoff_amt, @Size(max = 50) String roundoff_gl_ac,
			double adj1_amt, @Size(max = 50) String adj1_gl_ac, double adj2_amt, @Size(max = 50) String adj2_gl_ac,
			double net_r_value, @Size(max = 50) String net_gl_ac, double grand_total, @Size(max = 50) String company_id,
			@Size(max = 20) String fin_year, @Size(max = 50) String modified_type, LocalDateTime inserted_on,
			@Size(max = 50) String inserted_by, LocalDateTime updated_on, @Size(max = 50) String updated_by,
			LocalDateTime deleted_on, @Size(max = 50) String deleted_by,
			Set<Stk_Transfer_Invoice_Item_Dtls> stk_Transfer_Invoice_Item_Dtls,
			Set<Stk_Transfer_Invoice_Docs> stk_Transfer_Invoice_Docs,
			Stk_Transfer_Invoice_Bu_Dtls stk_Transfer_Invoice_Bu_Dtls,
			Stk_Transfer_Invoice_Tax_Info stk_Transfer_Invoice_Tax_Info) {
		super();
		this.id = id;
		this.stk_invoice_id = stk_invoice_id;
		this.stk_invoice_no = stk_invoice_no;
		this.stk_invoice_date = stk_invoice_date;
		this.business_unit = business_unit;
		this.payment_terms = payment_terms;
		this.stk_invoice_order_no = stk_invoice_order_no;
		this.pay_due_days = pay_due_days;
		this.trans_code = trans_code;
		this.narration = narration;
		this.item_total = item_total;
		this.item_total_gl_ac = item_total_gl_ac;
		this.tax_total = tax_total;
		this.tax_total_gl_ac = tax_total_gl_ac;
		this.transporter_amt = transporter_amt;
		this.transporter_gl_ac = transporter_gl_ac;
		this.applicable_amt = applicable_amt;
		this.applicable_gl_ac = applicable_gl_ac;
		this.roundoff_amt = roundoff_amt;
		this.roundoff_gl_ac = roundoff_gl_ac;
		this.adj1_amt = adj1_amt;
		this.adj1_gl_ac = adj1_gl_ac;
		this.adj2_amt = adj2_amt;
		this.adj2_gl_ac = adj2_gl_ac;
		this.net_r_value = net_r_value;
		this.net_gl_ac = net_gl_ac;
		this.grand_total = grand_total;
		this.company_id = company_id;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.stk_Transfer_Invoice_Item_Dtls = stk_Transfer_Invoice_Item_Dtls;
		this.stk_Transfer_Invoice_Docs = stk_Transfer_Invoice_Docs;
		this.stk_Transfer_Invoice_Bu_Dtls = stk_Transfer_Invoice_Bu_Dtls;
		this.stk_Transfer_Invoice_Tax_Info = stk_Transfer_Invoice_Tax_Info;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStk_invoice_id() {
		return stk_invoice_id;
	}

	public void setStk_invoice_id(String stk_invoice_id) {
		this.stk_invoice_id = stk_invoice_id;
	}

	public String getStk_invoice_no() {
		return stk_invoice_no;
	}

	public void setStk_invoice_no(String stk_invoice_no) {
		this.stk_invoice_no = stk_invoice_no;
	}

	public Date getStk_invoice_date() {
		return stk_invoice_date;
	}

	public void setStk_invoice_date(Date stk_invoice_date) {
		this.stk_invoice_date = stk_invoice_date;
	}

	public String getBusiness_unit() {
		return business_unit;
	}

	public void setBusiness_unit(String business_unit) {
		this.business_unit = business_unit;
	}

	public String getPayment_terms() {
		return payment_terms;
	}

	public void setPayment_terms(String payment_terms) {
		this.payment_terms = payment_terms;
	}

	public String getStk_invoice_order_no() {
		return stk_invoice_order_no;
	}

	public void setStk_invoice_order_no(String stk_invoice_order_no) {
		this.stk_invoice_order_no = stk_invoice_order_no;
	}

	public String getPay_due_days() {
		return pay_due_days;
	}

	public void setPay_due_days(String pay_due_days) {
		this.pay_due_days = pay_due_days;
	}

	public String getTrans_code() {
		return trans_code;
	}

	public void setTrans_code(String trans_code) {
		this.trans_code = trans_code;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public double getItem_total() {
		return item_total;
	}

	public void setItem_total(double item_total) {
		this.item_total = item_total;
	}

	public String getItem_total_gl_ac() {
		return item_total_gl_ac;
	}

	public void setItem_total_gl_ac(String item_total_gl_ac) {
		this.item_total_gl_ac = item_total_gl_ac;
	}

	public double getTax_total() {
		return tax_total;
	}

	public void setTax_total(double tax_total) {
		this.tax_total = tax_total;
	}

	public String getTax_total_gl_ac() {
		return tax_total_gl_ac;
	}

	public void setTax_total_gl_ac(String tax_total_gl_ac) {
		this.tax_total_gl_ac = tax_total_gl_ac;
	}

	public double getTransporter_amt() {
		return transporter_amt;
	}

	public void setTransporter_amt(double transporter_amt) {
		this.transporter_amt = transporter_amt;
	}

	public String getTransporter_gl_ac() {
		return transporter_gl_ac;
	}

	public void setTransporter_gl_ac(String transporter_gl_ac) {
		this.transporter_gl_ac = transporter_gl_ac;
	}

	public double getApplicable_amt() {
		return applicable_amt;
	}

	public void setApplicable_amt(double applicable_amt) {
		this.applicable_amt = applicable_amt;
	}

	public String getApplicable_gl_ac() {
		return applicable_gl_ac;
	}

	public void setApplicable_gl_ac(String applicable_gl_ac) {
		this.applicable_gl_ac = applicable_gl_ac;
	}

	public double getRoundoff_amt() {
		return roundoff_amt;
	}

	public void setRoundoff_amt(double roundoff_amt) {
		this.roundoff_amt = roundoff_amt;
	}

	public String getRoundoff_gl_ac() {
		return roundoff_gl_ac;
	}

	public void setRoundoff_gl_ac(String roundoff_gl_ac) {
		this.roundoff_gl_ac = roundoff_gl_ac;
	}

	public double getAdj1_amt() {
		return adj1_amt;
	}

	public void setAdj1_amt(double adj1_amt) {
		this.adj1_amt = adj1_amt;
	}

	public String getAdj1_gl_ac() {
		return adj1_gl_ac;
	}

	public void setAdj1_gl_ac(String adj1_gl_ac) {
		this.adj1_gl_ac = adj1_gl_ac;
	}

	public double getAdj2_amt() {
		return adj2_amt;
	}

	public void setAdj2_amt(double adj2_amt) {
		this.adj2_amt = adj2_amt;
	}

	public String getAdj2_gl_ac() {
		return adj2_gl_ac;
	}

	public void setAdj2_gl_ac(String adj2_gl_ac) {
		this.adj2_gl_ac = adj2_gl_ac;
	}

	public double getNet_r_value() {
		return net_r_value;
	}

	public void setNet_r_value(double net_r_value) {
		this.net_r_value = net_r_value;
	}

	public String getNet_gl_ac() {
		return net_gl_ac;
	}

	public void setNet_gl_ac(String net_gl_ac) {
		this.net_gl_ac = net_gl_ac;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getFin_year() {
		return fin_year;
	}

	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}

	public String getModified_type() {
		return modified_type;
	}

	public void setModified_type(String modified_type) {
		this.modified_type = modified_type;
	}

	public LocalDateTime getInserted_on() {
		return inserted_on;
	}

	public void setInserted_on(LocalDateTime inserted_on) {
		this.inserted_on = inserted_on;
	}

	public String getInserted_by() {
		return inserted_by;
	}

	public void setInserted_by(String inserted_by) {
		this.inserted_by = inserted_by;
	}

	public LocalDateTime getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(LocalDateTime updated_on) {
		this.updated_on = updated_on;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public LocalDateTime getDeleted_on() {
		return deleted_on;
	}

	public void setDeleted_on(LocalDateTime deleted_on) {
		this.deleted_on = deleted_on;
	}

	public String getDeleted_by() {
		return deleted_by;
	}

	public void setDeleted_by(String deleted_by) {
		this.deleted_by = deleted_by;
	}

	public Set<Stk_Transfer_Invoice_Item_Dtls> getStk_Transfer_Invoice_Item_Dtls() {
		return stk_Transfer_Invoice_Item_Dtls;
	}

	public void setStk_Transfer_Invoice_Item_Dtls(Set<Stk_Transfer_Invoice_Item_Dtls> stk_Transfer_Invoice_Item_Dtls) {
		this.stk_Transfer_Invoice_Item_Dtls = stk_Transfer_Invoice_Item_Dtls;
	}

	public Set<Stk_Transfer_Invoice_Docs> getStk_Transfer_Invoice_Docs() {
		return stk_Transfer_Invoice_Docs;
	}

	public void setStk_Transfer_Invoice_Docs(Set<Stk_Transfer_Invoice_Docs> stk_Transfer_Invoice_Docs) {
		this.stk_Transfer_Invoice_Docs = stk_Transfer_Invoice_Docs;
	}

	public Stk_Transfer_Invoice_Bu_Dtls getStk_Transfer_Invoice_Bu_Dtls() {
		return stk_Transfer_Invoice_Bu_Dtls;
	}

	public void setStk_Transfer_Invoice_Bu_Dtls(Stk_Transfer_Invoice_Bu_Dtls stk_Transfer_Invoice_Bu_Dtls) {
		this.stk_Transfer_Invoice_Bu_Dtls = stk_Transfer_Invoice_Bu_Dtls;
	}

	public Stk_Transfer_Invoice_Tax_Info getStk_Transfer_Invoice_Tax_Info() {
		return stk_Transfer_Invoice_Tax_Info;
	}

	public void setStk_Transfer_Invoice_Tax_Info(Stk_Transfer_Invoice_Tax_Info stk_Transfer_Invoice_Tax_Info) {
		this.stk_Transfer_Invoice_Tax_Info = stk_Transfer_Invoice_Tax_Info;
	}

	@Override
	public String toString() {
		return "Stk_Transfer_Invoice [id=" + id + ", stk_invoice_id=" + stk_invoice_id + ", stk_invoice_no="
				+ stk_invoice_no + ", stk_invoice_date=" + stk_invoice_date + ", business_unit=" + business_unit
				+ ", payment_terms=" + payment_terms + ", stk_invoice_order_no=" + stk_invoice_order_no
				+ ", pay_due_days=" + pay_due_days + ", trans_code=" + trans_code + ", narration=" + narration
				+ ", item_total=" + item_total + ", item_total_gl_ac=" + item_total_gl_ac + ", tax_total=" + tax_total
				+ ", tax_total_gl_ac=" + tax_total_gl_ac + ", transporter_amt=" + transporter_amt
				+ ", transporter_gl_ac=" + transporter_gl_ac + ", applicable_amt=" + applicable_amt
				+ ", applicable_gl_ac=" + applicable_gl_ac + ", roundoff_amt=" + roundoff_amt + ", roundoff_gl_ac="
				+ roundoff_gl_ac + ", adj1_amt=" + adj1_amt + ", adj1_gl_ac=" + adj1_gl_ac + ", adj2_amt=" + adj2_amt
				+ ", adj2_gl_ac=" + adj2_gl_ac + ", net_r_value=" + net_r_value + ", net_gl_ac=" + net_gl_ac
				+ ", grand_total=" + grand_total + ", company_id=" + company_id + ", fin_year=" + fin_year
				+ ", modified_type=" + modified_type + ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by
				+ ", updated_on=" + updated_on + ", updated_by=" + updated_by + ", deleted_on=" + deleted_on
				+ ", deleted_by=" + deleted_by + ", stk_Transfer_Invoice_Item_Dtls=" + stk_Transfer_Invoice_Item_Dtls
				+ ", stk_Transfer_Invoice_Docs=" + stk_Transfer_Invoice_Docs + ", stk_Transfer_Invoice_Bu_Dtls="
				+ stk_Transfer_Invoice_Bu_Dtls + ", stk_Transfer_Invoice_Tax_Info=" + stk_Transfer_Invoice_Tax_Info
				+ "]";
	}

	

}
*/