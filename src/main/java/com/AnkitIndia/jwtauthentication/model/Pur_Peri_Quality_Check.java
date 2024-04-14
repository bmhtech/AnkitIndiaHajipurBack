package com.AnkitIndia.jwtauthentication.model;

import java.sql.Date;
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
@Table(name="Pur_Peri_Quality_Check")
public class Pur_Peri_Quality_Check extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String peri_qc_id;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String pqcno;
	
	private Date pqc_date;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String supplier_name;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String wments_dtls;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String item_sub_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String ref_type;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String qc_parameter;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String recieved_by;
	
	@Column(columnDefinition="varchar(50) default 'NA'")
	private String wghmnt_no;

	@OneToMany(fetch=FetchType.EAGER,mappedBy="pur_Peri_Quality_Check",cascade=CascadeType.ALL)
	private Set<Pur_Peri_Quality_Check_Details> pur_Peri_Quality_Check_Details;
 	
}
