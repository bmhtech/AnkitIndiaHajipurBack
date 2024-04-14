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
@Table(name="Qc_rules_setup")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Qc_rules_setup extends CommonProperties {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String qc_id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String qc_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String qc_name;
	
	@Column(columnDefinition="varchar(200) default 'NA'")
	private String remarks;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean qc_active;
	
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="qc_rules_setup",cascade=CascadeType.ALL)
	private Set<Qc_rules_setup_dtls> qc_rules_setup_dtls;

	
	
}
