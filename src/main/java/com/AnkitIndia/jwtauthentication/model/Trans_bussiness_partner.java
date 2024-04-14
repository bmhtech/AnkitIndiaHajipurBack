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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Trans_bussiness_partner", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
        		"bp_name"
        })
})
public class Trans_bussiness_partner extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_Id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String bp_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String bp_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String alt_name;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean bp_active;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String group_type;
	
	@Column(columnDefinition="varchar(100) default 0")
	private String group_type_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sub_group_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String trans_currency;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean block_active;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean pak_mat_replc;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean broker_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String constitution;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_app;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_regno;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;

	@OneToOne(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner",cascade = CascadeType.ALL)
	private Trans_bussiness_partner_address trans_bussiness_partner_address;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner1",cascade = CascadeType.ALL)
	private Set<Trans_bussiness_partner_address_dtls> trans_bussiness_partner_address_dtls;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner2",cascade = CascadeType.ALL)
	private Trans_bussiness_partner_accont trans_bussiness_partner_accont;

	@OneToOne(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner3",cascade = CascadeType.ALL)
	private Trans_bussiness_partner_statutory trans_bussiness_partner_statutory;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner4",cascade = CascadeType.ALL)
	private Set<Trans_bussiness_partner_broker> trans_bussiness_partner_broker;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner5",cascade = CascadeType.ALL)
	private Set<Trans_bussiness_partner_vehicle_dtls> trans_bussiness_partner_vehicle_dtls;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner",cascade = CascadeType.ALL)
	private Set<Trans_bussiness_partner_doc> trans_bussiness_partner_doc;
	
	
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="trans_bussiness_partner",cascade = CascadeType.ALL)
	private Trans_bussiness_partner_tds trans_bussiness_partner_tds;


}
