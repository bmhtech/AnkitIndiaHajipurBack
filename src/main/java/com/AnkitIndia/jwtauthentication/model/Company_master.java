package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
import lombok.EqualsAndHashCode;
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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=false)
@Table(name="Company_master")
public class Company_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String company_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String company_name;
	
	@Column(columnDefinition="varchar(2) default 'CM'")
	private String comp_prefix;
  
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String comp_mailing_name;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String company_address;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String company_type;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String parent_company;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String country_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String state_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String city_name;
	
	private int pin_code;
	
	private Long office_contactno;
	
	private Long work_phoneno;
	
	private Long mobile_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String email_id;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String website_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String company_scode;
	
	private Date fin_period_from;

	private Date fin_period_to;
	

	@Column(columnDefinition="varchar(250) default 'NA'")
	private String work_address;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean use_audit_feature;
	
	private Double decimal_place;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean incometax_required;
	

	/*@Column(columnDefinition="tinyint(1) default 0")
	private boolean servicetax_required;*/

	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean tds_required;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean maintain_licencedetails;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean maintain_businessunit;

	@Column(columnDefinition="varchar(20) default 'NA'")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pan_circle_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pan_wardno;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String pan_accessing_officer;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String outside_responsibleperson;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String circleno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String taxpayment_type;
	
	private Date taxpayment_date;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String returnfilling_type;
	
	private Date returnfilling_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String gstin_no;

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String tin_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String cst_no;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sales_inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String sales_outside_responsibleperson;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_circleno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_taxpayment_type;
	
	private Date sales_taxpayment_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String sales_returnfilling_type;

	private Date sales_returnfilling_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String servicetax_no;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_nature;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String service_inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String service_outside_responsibleperson;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_circleno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")

	private String service_taxpayment_type;
	
	private Date service_taxpayment_date;
	

	@Column(columnDefinition="varchar(50) default 'NA'")
	private String service_returnfilling_type;
	
	private Date service_returnfilling_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String roc_inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String roc_outside_responsibleperson;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String roc_remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String roc_circleno;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String roc_taxpayment_type;
	
	private Date roc_taxpayment_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String roc_returnfilling_type;
	
	private Date roc_returnfilling_date;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String otherdetails_remarks;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="company_master",cascade = CascadeType.ALL)
	private Set<Company_licence_details> company_licence_details;

}

