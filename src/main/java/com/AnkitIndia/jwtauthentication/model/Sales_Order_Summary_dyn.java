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
@Table(name="Sales_Order_Summary_dyn")
public class Sales_Order_Summary_dyn extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String order_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String charge_name;
    
	@Column(columnDefinition="varchar(50) default 0")
	private String rate_cal_method;
	
	@Column(columnDefinition = "Double(10,2)")
	private double amount;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tax_rate;
	
	@Column(columnDefinition = "Double(10,2)")
	private double app_rate; /* New */
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String add_less; /* New */
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "so_id")
    private Sales_Order sales_order;
}
