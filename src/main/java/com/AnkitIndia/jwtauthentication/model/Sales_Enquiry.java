package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name="Sales_Enquiry")
public class Sales_Enquiry extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enquiry_id;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String enquiry_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enq_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enq_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String mode_of_enq;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String enq_status;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sales_person;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pre_closing;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String app_deal_val;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String referred_by;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String businessunit;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_enquiry",cascade = CascadeType.ALL)
	private Set<Sales_Enquiry_Item_Dtls> sales_Enquiry_Item_Dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_enquiry",cascade =CascadeType.ALL)
	private Set<Sales_Enquiry_Party_Dtls> sales_Enquiry_Party_Dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_enquiry",cascade = CascadeType.ALL)
	private Set<Sales_Enquiry_Docs> sales_Enquiry_Docs;

}
