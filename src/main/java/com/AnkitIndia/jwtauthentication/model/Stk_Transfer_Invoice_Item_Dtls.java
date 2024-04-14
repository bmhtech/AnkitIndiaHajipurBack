/*package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="Stk_Transfer_Invoice_Item_Dtls")
public class Stk_Transfer_Invoice_Item_Dtls {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Size(max = 50)
	private String stk_invoice_id;
	
	@Size(max = 50)
	private String stk_invoice_no;
	
	@Size(max = 50)
	private String  slno;
	
	@Size(max = 50)
	private String item_code;
	
	@Size(max = 200)
	private String item_name;
	
	@Size(max = 50)
	private String packing;
	
	@Size(max = 200)
	private String packing_name;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double quantity;
	
	@Size(max = 50)
	private String uom;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double squantity;
	
	@Size(max = 50)
	private String suom;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double mat_wt;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double price;
	
	@Size(max = 50)
	private String price_based_on;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double amount;
	
	@Size(max = 50)
	private String tax_code;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double tax_amt;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double total_amt;
	
	@Size(max = 50)
	private String acc_norms;
	
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
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "sti_id")
	private Stk_Transfer_Invoice stk_Transfer_Invoice;

	public Stk_Transfer_Invoice_Item_Dtls() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stk_Transfer_Invoice_Item_Dtls(Long id, @Size(max = 50) String stk_invoice_id,
			@Size(max = 50) String stk_invoice_no, @Size(max = 50) String slno, @Size(max = 50) String item_code,
			@Size(max = 200) String item_name, @Size(max = 50) String packing, @Size(max = 200) String packing_name,
			double quantity, @Size(max = 50) String uom, double squantity, @Size(max = 50) String suom, double mat_wt,
			double price, @Size(max = 50) String price_based_on, double amount, @Size(max = 50) String tax_code,
			double tax_rate, double tax_amt, double total_amt, @Size(max = 50) String acc_norms,
			@Size(max = 50) String company_id, @Size(max = 20) String fin_year, @Size(max = 50) String modified_type,
			LocalDateTime inserted_on, @Size(max = 50) String inserted_by, LocalDateTime updated_on,
			@Size(max = 50) String updated_by, LocalDateTime deleted_on, @Size(max = 50) String deleted_by,
			Stk_Transfer_Invoice stk_Transfer_Invoice) {
		super();
		this.id = id;
		this.stk_invoice_id = stk_invoice_id;
		this.stk_invoice_no = stk_invoice_no;
		this.slno = slno;
		this.item_code = item_code;
		this.item_name = item_name;
		this.packing = packing;
		this.packing_name = packing_name;
		this.quantity = quantity;
		this.uom = uom;
		this.squantity = squantity;
		this.suom = suom;
		this.mat_wt = mat_wt;
		this.price = price;
		this.price_based_on = price_based_on;
		this.amount = amount;
		this.tax_code = tax_code;
		this.tax_rate = tax_rate;
		this.tax_amt = tax_amt;
		this.total_amt = total_amt;
		this.acc_norms = acc_norms;
		this.company_id = company_id;
		this.fin_year = fin_year;
		this.modified_type = modified_type;
		this.inserted_on = inserted_on;
		this.inserted_by = inserted_by;
		this.updated_on = updated_on;
		this.updated_by = updated_by;
		this.deleted_on = deleted_on;
		this.deleted_by = deleted_by;
		this.stk_Transfer_Invoice = stk_Transfer_Invoice;
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

	public String getSlno() {
		return slno;
	}

	public void setSlno(String slno) {
		this.slno = slno;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public String getPacking_name() {
		return packing_name;
	}

	public void setPacking_name(String packing_name) {
		this.packing_name = packing_name;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public double getSquantity() {
		return squantity;
	}

	public void setSquantity(double squantity) {
		this.squantity = squantity;
	}

	public String getSuom() {
		return suom;
	}

	public void setSuom(String suom) {
		this.suom = suom;
	}

	public double getMat_wt() {
		return mat_wt;
	}

	public void setMat_wt(double mat_wt) {
		this.mat_wt = mat_wt;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPrice_based_on() {
		return price_based_on;
	}

	public void setPrice_based_on(String price_based_on) {
		this.price_based_on = price_based_on;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTax_code() {
		return tax_code;
	}

	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}

	public double getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(double tax_rate) {
		this.tax_rate = tax_rate;
	}

	public double getTax_amt() {
		return tax_amt;
	}

	public void setTax_amt(double tax_amt) {
		this.tax_amt = tax_amt;
	}

	public double getTotal_amt() {
		return total_amt;
	}

	public void setTotal_amt(double total_amt) {
		this.total_amt = total_amt;
	}

	public String getAcc_norms() {
		return acc_norms;
	}

	public void setAcc_norms(String acc_norms) {
		this.acc_norms = acc_norms;
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

	public Stk_Transfer_Invoice getStk_Transfer_Invoice() {
		return stk_Transfer_Invoice;
	}

	public void setStk_Transfer_Invoice(Stk_Transfer_Invoice stk_Transfer_Invoice) {
		this.stk_Transfer_Invoice = stk_Transfer_Invoice;
	}

	@Override
	public String toString() {
		return "Stk_Transfer_Invoice_Item_Dtls [id=" + id + ", stk_invoice_id=" + stk_invoice_id + ", stk_invoice_no="
				+ stk_invoice_no + ", slno=" + slno + ", item_code=" + item_code + ", item_name=" + item_name
				+ ", packing=" + packing + ", packing_name=" + packing_name + ", quantity=" + quantity + ", uom=" + uom
				+ ", squantity=" + squantity + ", suom=" + suom + ", mat_wt=" + mat_wt + ", price=" + price
				+ ", price_based_on=" + price_based_on + ", amount=" + amount + ", tax_code=" + tax_code + ", tax_rate="
				+ tax_rate + ", tax_amt=" + tax_amt + ", total_amt=" + total_amt + ", acc_norms=" + acc_norms
				+ ", company_id=" + company_id + ", fin_year=" + fin_year + ", modified_type=" + modified_type
				+ ", inserted_on=" + inserted_on + ", inserted_by=" + inserted_by + ", updated_on=" + updated_on
				+ ", updated_by=" + updated_by + ", deleted_on=" + deleted_on + ", deleted_by=" + deleted_by
				+ ", stk_Transfer_Invoice=" + stk_Transfer_Invoice + "]";
	}

	
	
}
*/