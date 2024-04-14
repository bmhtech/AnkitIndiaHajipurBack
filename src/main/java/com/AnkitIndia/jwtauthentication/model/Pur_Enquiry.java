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
@Table(name="Pur_Enquiry")
public class Pur_Enquiry extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	//@Column(name="enquiry_id" , unique = true, nullable = false)
	private String enquiry_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enquiry_no;
	
	private String enquiry_date;
	
	@Column(columnDefinition="varchar(20) default '0'")
	private String valid_until;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String enquiry_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String mode_of_enq;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String enquiry_status;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String service_type;
	
	@Column(columnDefinition="varchar(100) default '0'")
	private String service_type_name;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String referance_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String dept;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="varchar(150) default 'NA'")
	private String document;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String packing_req;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String fullfillment_by;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String fullfillment_type;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String payment_term;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String trans_born_by;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String loc_of_delivery;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String security_doc;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String confirmed_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String approved;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String reason;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean quotation_status;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean order_status;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String referance_id;
	
	//@OneToMany(targetEntity = Pur_Enquiry_Service_Details.class,cascade = CascadeType.ALL)
	//@JoinColumn(name ="enquiry_id",referencedColumnName = "enquiry_id")
	//private List<Pur_Enquiry_Service_Details> pur_Enquiry_Service_Details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Enquiry",cascade = CascadeType.ALL)
	private Set<Pur_Enquiry_Service_Details> pur_Enquiry_Service_Details;
	
	//@OneToMany(targetEntity = Pur_Enquiry_BPartner_Details.class,cascade = CascadeType.ALL)
	//@JoinColumn(name ="enquiry_id",referencedColumnName = "enquiry_id")
	//private List<Pur_Enquiry_BPartner_Details> pur_Enquiry_BPartner_Details;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Enquiry",cascade = CascadeType.ALL)
	private Set<Pur_Enquiry_BPartner_Details> pur_Enquiry_BPartner_Details;
	
	//@OneToMany(targetEntity = Pur_Enquiry_Doc.class,cascade = CascadeType.ALL)
	//@JoinColumn(name ="enquiry_id",referencedColumnName = "enquiry_id")
	//private List<Pur_Enquiry_Doc> pur_Enquiry_docs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="pur_Enquiry",cascade = CascadeType.ALL)
	private Set<Pur_Enquiry_Doc> pur_Enquiry_docs;
	
}
