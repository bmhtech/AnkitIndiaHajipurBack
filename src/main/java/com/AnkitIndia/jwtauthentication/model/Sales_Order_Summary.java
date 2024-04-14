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
@Table(name="Sales_Order_Summary")
public class Sales_Order_Summary extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String order_no;
	
	@Column(columnDefinition = "Double(12,2)")
	private double item_total;
	
	@Column(columnDefinition = "Double(12,2)")
	private double discount;
	
	@Column(columnDefinition = "Double(12,2)")
	private double tax_total;
	
	@Column(columnDefinition = "Double(12,2)")
	private double net_amount;
	
	@Column(columnDefinition = "Double(12,2)")
	private double app_brokerage;
    
	@Column(columnDefinition = "Double(12,2)")
	private double net_r_value;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "so_id")
    private Sales_Order sales_order;
}
