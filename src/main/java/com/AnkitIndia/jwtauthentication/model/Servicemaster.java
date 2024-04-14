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
@Table(name="Servicemaster")

public class Servicemaster extends CommonProperties {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(30) default 'NA'")
	private String service_no;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean service_category;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_acc_code;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_group;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_groupname;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_subtype;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_subtypename;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_ac;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_acname;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String description;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tax_rate;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_item_type;
	
	@Column(columnDefinition="TEXT")
	private String remarks;
	
	
	//Dynamic
    @OneToMany(fetch = FetchType.EAGER,mappedBy="servicemaster",cascade =CascadeType.ALL) 
    private Set<Service_masterdtls> service_masterdtls;
	
}

