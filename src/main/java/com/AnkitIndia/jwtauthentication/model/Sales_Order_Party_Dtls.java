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
@Table(name="Sales_Order_Party_Dtls")
public class Sales_Order_Party_Dtls extends CommonProperties{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String order_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String order_no;
	
	private int sl_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String p_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String p_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_name;
	
	private Long cp_contact;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_city;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean city_approved;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String send_via;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tcs_applicable;
	
	@Column(columnDefinition = "Double(10,2)")
	private double tcs_rate;
	
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "so_id")
    private Sales_Order sales_order;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "so_id_cust")
    private Channel_customer_master channel_customer_master;
}
