package com.AnkitIndia.jwtauthentication.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="Sales_Quotation_Summary_dyn")
public class Sales_Quotation_Summary_dyn extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String quotation_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String quotation_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String charge_name;
    
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String rate_cal_method;
	
	@Column(columnDefinition = "Double(10,2)")
	private double amount;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_rate;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "sq_id")
    private Sales_Quotation sQuotation;
}
