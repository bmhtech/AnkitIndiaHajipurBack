package com.AnkitIndia.jwtauthentication.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Qc_rules_setup_dtls")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Qc_rules_setup_dtls extends CommonProperties {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String qc_id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String qc_code;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String qc_param;
	
	@Column(columnDefinition="varchar(100) default 'NA'")
	private String cal_basis;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean qty_chkbox;
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double basis_amt_UoM; 
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double min; 
	
	@Column(columnDefinition = "Decimal(10,2)")
	private double max; 
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="qc_rules_Id")
	Qc_rules_setup qc_rules_setup;
}
