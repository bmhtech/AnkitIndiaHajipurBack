package com.AnkitIndia.jwtauthentication.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
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
@Table(name="Company_business_unit_master")
@NamedStoredProcedureQuery(name = "Company_business_unit_master.saveGrpSubParent", 
procedureName = "saveGrpSubParent", parameters = {
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "bunitid", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.IN, name = "grpsdata", type = String.class),
  @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result_out", type = Integer.class)})
public class Company_business_unit_master extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String businessunit_id;

	@Column(columnDefinition="varchar(50) default '0'")
	private String businessunit_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String businessunit_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String mailing_address;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String country_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String state_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String state_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String dist_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String dist_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String city_code;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String city_name;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String postid;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String post_office;
	
	private int pin_code;
	
	private Long office_contactno;
	
	private Long work_phoneno;
	
	private Long mobile_no;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String email_id;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String website_name;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String company_scode;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fin_period_from;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String fin_period_to;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String work_address;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean use_audit_feature;
	
	private Double decimal_place;
	
	@Column(columnDefinition="varchar(50) default '0'")	
	private String businessunit_active;
	
	@Column(columnDefinition="varchar(150) default '0'")
	private String maintain_warehouse;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean maintain_department;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean incometax_required;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean tds_required;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean maintain_licencedetails;

	@Column(columnDefinition="varchar(50) default '0'")
	private String pan_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pan_circle_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String pan_wardno;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String pan_accessing_officer;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String outside_responsibleperson;
	
	@Column(columnDefinition="varchar(200) default '0'")
	private String remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String circleno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String taxpayment_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String taxpayment_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String returnfilling_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String returnfilling_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String gstin_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String tin_no;

	@Column(columnDefinition="varchar(50) default '0'")
	private String cst_no;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String sales_inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String sales_outside_responsibleperson;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String sales_remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sales_circleno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sales_taxpayment_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sales_taxpayment_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sales_returnfilling_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String sales_returnfilling_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String servicetax_no;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_nature;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String service_inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String service_outside_responsibleperson;

	@Column(columnDefinition="varchar(250) default '0'")
	private String service_remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_circleno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_taxpayment_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_taxpayment_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_returnfilling_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String service_returnfilling_date;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String roc_inhouse_responsibleperson;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String roc_outside_responsibleperson;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String roc_remarks_otherinfo;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String roc_circleno;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String roc_taxpayment_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String roc_taxpayment_date;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String roc_returnfilling_type;
	
	@Column(columnDefinition="varchar(50) default '0'")
	private String roc_returnfilling_date;
	
	@Column(columnDefinition="varchar(250) default '0'")
	private String otherdetails_remarks;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="company_business_unit_master",cascade = CascadeType.ALL)
	private Set<Company_business_unit_details> companyBusinessUnitDetails;

}
