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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Supp_bussiness_partner", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
        		"bp_name"
        })
})
public class Supp_bussiness_partner extends CommonProperties {
	
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
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  copy_bp_addr;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String party_nature;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String def_tds_nature;
	
	@Column(columnDefinition="varchar(500) default '0'")
	private String business_unit;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean all_unit;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String constitution;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_app;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ssi_regno;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean broker_status;
	
	@Column(columnDefinition="int(1) default '0'")
	private int export;	
	
	@Column(columnDefinition="varchar(1000) default 'NA'")
	private String response;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String undo_by;
	
	private LocalDateTime undo_on;
	
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner",cascade = CascadeType.ALL)
	private Supp_bussiness_partner_address supp_bussiness_partner_address;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner1",cascade = CascadeType.ALL)
	private Supp_bussiness_partner_bill_addr supp_bussiness_partner_bill_addr;

	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner2",cascade = CascadeType.ALL)
	private Set<Supp_bussiness_partner_delv_from> supp_bussiness_partner_delv_from;

	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner3",cascade = CascadeType.ALL)
	private Supp_bussiness_partner_accont supp_bussiness_partner_accont;
	
	//Static
	@OneToOne(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner5",cascade = CascadeType.ALL)
	private Supp_bussiness_partner_statutory supp_bussiness_partner_statutory;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner6",cascade = CascadeType.ALL)
	private Set<Supp_bussiness_partner_broker> supp_bussiness_partner_broker;

	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner7",cascade = CascadeType.ALL)
	private Set<Supp_bussiness_partner_addr_dtls> supp_bussiness_partner_addr_dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner8",cascade = CascadeType.ALL)
	private Set<Supp_bussiness_partner_bill_addr_dtls> supp_bussiness_partner_bill_addr_dtls;
	
	//Dynamic
	@OneToMany(fetch = FetchType.EAGER,mappedBy="supp_bussiness_partner9",cascade = CascadeType.ALL)
	private Set<Supp_bussiness_partner_doc> supp_bussiness_partner_docs;
	
}
