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
@Table(name="Sales_transport")
public class Sales_transport extends CommonProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_tranport_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String challandate;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String currentdate;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String jvdate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String challanno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String buname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String partyname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String vehicleno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_slip_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String own_slip_nonew;
	
	@Column(columnDefinition = "Double(10,3)")
	private double grosswt;
	
	@Column(columnDefinition = "Double(10,3)")
	private double grosswtnew;
	
	@Column(columnDefinition = "Double(10,3)")
	private double tarewt;
	
	@Column(columnDefinition = "Double(10,3)")
	private double tarewtnew;
	
	@Column(columnDefinition = "Double(10,3)")
	private double netwt;
	
	@Column(columnDefinition = "Double(10,3)")
	private double netwtnew;
	
	@Column(columnDefinition = "Double(10,3)")
	private double balancewt;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String deduction_basedon;
	
	@Column(columnDefinition="Double(10,2)")
	private double allowed_shortage;
	
	@Column(columnDefinition="Double(10,2)")
	private double mat_price;
	
	@Column(columnDefinition="Double(10,2)")
	private double mat_amt;
	
	@Column(columnDefinition= "Double(10,2)")
	private double price;
	
	@Column(columnDefinition= "Double(10,2)")
	private double actual_amt;
	
	@Column(columnDefinition= "Double(10,2)")
	private double adv_pay;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String transname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String app_chgs_id;
	
	/*@Column(columnDefinition="varchar(50) default 'NA'")
	private String remarks;*/
	@Column(columnDefinition="TEXT")
	private String remarks;
	
	@Column(columnDefinition= "Double(10,2)")
	private double chgs_dedu;
	
	@Column(columnDefinition= "Double(10,2)")
	private double balance_amt;
	
	@Column(columnDefinition= "Double(10,2)")
	private double tds_rate;
	
	@Column(columnDefinition= "Double(10,2)")
	private double tds_amt;
	
	@Column(columnDefinition= "Double(10,2)")
	private double tds_dedu_amt;
	
	@Column(columnDefinition= "Double(10,2)")
	private double round_off;
	
	@Column(columnDefinition= "Double(10,2)")
	private double pay_amt;
	
	@Column(columnDefinition= "Double(10,2)")
	private double final_pay;
	
	@Column(columnDefinition= "Double(10,3)")
	private double transportqty;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String loadingdate;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String detaintionfromdate;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String detaintiontodate;
	
	@Column(columnDefinition="varchar(30) default 0")
	private String trans_jv_no;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String referance_no;
	
	@Column(columnDefinition="varchar(50) default 0")
	private String transport_rate;
	
	@Column(columnDefinition="varchar(10) default 0")
	private String bags;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String ailreturnweight;
	
	@Column(columnDefinition="varchar(10) default 'NA'")
	private String bulksupply;
	
	@Column(columnDefinition="varchar(500) default 'NA'")
	private String del_remarks;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="sales_transport",cascade = CascadeType.ALL)
	private Set<Salestransport_app_chgs> salestransport_app_chgs;
}
