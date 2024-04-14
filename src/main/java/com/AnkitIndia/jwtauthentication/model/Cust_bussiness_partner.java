package com.AnkitIndia.jwtauthentication.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Cust_bussiness_partner", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
        		"cp_name"
        })
})
public class Cust_bussiness_partner extends CommonProperties{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_Id;
	
	
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String print_to_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cp_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cp_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String alt_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean cp_active;  
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String group_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String group_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sub_group_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String trans_currency;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean block_active; 
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean copy_bp_addr;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String business_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String constitution;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_app;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_regno;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean broker_status;
	
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean saleclosed;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	
	
	
	
	
	
	
	
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Cust_bussiness_partner_accont cust_bussiness_partner_accont;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Cust_bussiness_partner_address cust_bussiness_partner_address;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Cust_bussiness_partner_bill_addr cust_bussiness_partner_bill_addr;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Cust_bussiness_partner_statutory cust_bussiness_partner_statutory;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Set<Cust_bussiness_partner_broker> cust_bussiness_partner_broker;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Set<Cust_bussiness_partner_delv_to> cust_bussiness_partner_delv_to;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Set<Cust_bussiness_partner_address_dtls> cust_bussiness_partner_addr_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Set<Cust_bussiness_partner_bill_addr_dtls> cust_bussiness_partner_bill_addr_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Set<Cust_bussiness_partner_doc> cust_bussiness_partner_docs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cust_bussiness_partner",cascade = CascadeType.ALL)
	private Set<Cust_bussiness_partner_shipping_addr_dtls> cust_bussiness_partner_shipping_addr_dtls;

}
