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

@Entity
@Table(name="Acc_pay_term_master")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Acc_pay_term_master  extends CommonProperties{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String payterm_id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String payterm_code;
	
	@Column(columnDefinition="varchar(250) default 'NA'")
	private String payterm_desc;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String ins_type;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String ins_period;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean  payterm_active;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="acc_pay_term_master",cascade = CascadeType.ALL)
	private Set<Acc_pay_term_master_details> acc_pay_term_master_details;
	
}
