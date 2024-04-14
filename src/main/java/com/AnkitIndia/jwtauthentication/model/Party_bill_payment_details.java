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
@Table(name="Party_bill_payment_details")
public class Party_bill_payment_details extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pbill_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String invoice_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String invoice_date;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double bill_amt;
    
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double adj_amt;
    
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double due_amt;
	
	@Column(columnDefinition="Double(20,2) default 0.00")
	private double payable_amt;
    
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String remarks;

	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "pbillpay_id")
    private Party_bill_payment bill_payment;
}
