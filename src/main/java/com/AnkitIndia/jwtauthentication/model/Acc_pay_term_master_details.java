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
@Table(name="Acc_pay_term_master_details")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Acc_pay_term_master_details extends CommonProperties{

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String payterm_code;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String payterm_id;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String inst_no;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String inst_days;
	
	@Column(columnDefinition="varchar(20) default 'NA'")
	private String inst_percent;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "Pay_Id")
    private Acc_pay_term_master acc_pay_term_master;

	
}
