package com.AnkitIndia.jwtauthentication.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="Sales_Quotation_Terms_Con")
public class Sales_Quotation_Terms_Con extends CommonProperties {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String quotation_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_mode;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String payment_term;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bank_name;
	
	private Long account_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String ifsc_code;

	@Column(columnDefinition = "Double(10,2)")
	private double cash_limit;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String account_name;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String branch;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String iban;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String bic_swift_code;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "sq_id")
    private Sales_Quotation sQuotation;
}
