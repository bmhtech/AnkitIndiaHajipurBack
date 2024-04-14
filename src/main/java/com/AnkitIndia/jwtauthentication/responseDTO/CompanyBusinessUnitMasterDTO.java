package com.AnkitIndia.jwtauthentication.responseDTO;

import java.sql.Date;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CompanyBusinessUnitMasterDTO {
	
	private Long id;
	
	private String businessunit_id;
	
	private String businessunit_code;
	
	private String businessunit_name;
	
	private String company_code;
	
	private String mailing_address;
	
	private String country_name;
	
	private String state_code;
	
	private String state_name;
	
	private String dist_code;
	
	private String dist_name;
	
	private String city_code;
	
	private String city_name;
	
	private String postid;
	
	private String post_office;
	
	private int pin_code;
	
	private Long office_contactno;
	
	private Long work_phoneno;
	
	private Long mobile_no;
	
	private String email_id;
	
	private String website_name;
	
	private String company_scode;
	
	private String fin_period_from;
	
	private String fin_period_to;
	
	private String work_address;
	
	private boolean use_audit_feature;
	
	private Double decimal_place;
	
	private String businessunit_active;
	
	private String maintain_warehouse;
	
	private boolean maintain_department;
	
	private boolean incometax_required;
	
	private boolean salestax_required;
	
	private boolean servicetax_required;
	
	private boolean roc_required;
	
	private boolean tds_required;
	
	private boolean maintain_licencedetails;
	
	private String pan_no;
	
	private String pan_circle_no;
	
	private String pan_wardno;
	
	private String pan_accessing_officer;
	
	private String inhouse_responsibleperson;
	
	private String outside_responsibleperson;
	
	private String remarks_otherinfo;
	
	private String circleno;
	
	private String taxpayment_type;
	
	private String taxpayment_date;
	
	private String returnfilling_type;
	
	private String returnfilling_date;
	
	private String gstin_no;
	
	private String tin_no;

	private String cst_no;
	
	private String sales_inhouse_responsibleperson;
	
	private String sales_outside_responsibleperson;
	
	private String sales_remarks_otherinfo;
	
	private String sales_circleno;
	
	private String sales_taxpayment_type;
	
	private String sales_taxpayment_date;
	
	private String sales_returnfilling_type;
	
	private String sales_returnfilling_date;
	
	private String servicetax_no;
	
	private String service_nature;
	
	private String service_inhouse_responsibleperson;
	
	private String service_outside_responsibleperson;
	
	private String service_remarks_otherinfo;
	
	private String service_circleno;
	
	private String service_taxpayment_type;
	
	private String service_taxpayment_date;
	
	private String service_returnfilling_type;
	
	private String service_returnfilling_date;
	
	private String roc_inhouse_responsibleperson;
	
	private String roc_outside_responsibleperson;
	
	private String roc_remarks_otherinfo;
	
	private String roc_circleno;
	
	private String roc_taxpayment_type;
	
	private String roc_taxpayment_date;
	
	private String roc_returnfilling_type;
	
	private String roc_returnfilling_date;
	
	private String otherdetails_remarks;
	
	private String Add;

	public CompanyBusinessUnitMasterDTO(Long id, String businessunit_id, String businessunit_code,
			String businessunit_name) {
		super();
		this.id = id;
		this.businessunit_id = businessunit_id;
		this.businessunit_code = businessunit_code;
		this.businessunit_name = businessunit_name;
	}
	
	
}
