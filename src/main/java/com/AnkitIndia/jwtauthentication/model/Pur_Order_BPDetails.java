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
@Table(name="Pur_Order_BPDetails")
public class Pur_Order_BPDetails extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_orderid;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pur_order_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supp_name;
	
	private Long supp_phone;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supp_fax;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supp_email;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String supp_address;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_name;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String cp_designation;
	
	private Long cp_phone;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_fax;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_email;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String cp_address;
	
	@OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "P_Id")
    private Pur_Order pur_Order;

}
